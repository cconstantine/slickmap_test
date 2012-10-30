package com.example.glmapexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;

class ScrollMapView extends GridView {

  private static int GRID_SIZE=100;
  private GestureDetector mGD;

  public ScrollMapView(final Context context) {
    super(context);
    //GridView gv = new GridView(context);
    this.setAdapter(new BaseAdapter() {
      private int i = 0;
      @Override
      public int getCount() {
        // TODO Auto-generated method stub
        return GRID_SIZE*GRID_SIZE;
      }

      @Override
      public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        Logger.debug(String.format("asdfi: %d", ++i));
        if (convertView != null)
          return convertView;
        
        ImageView iv = new ImageView(context);
        Drawable d = context.getResources().getDrawable(R.drawable.ic_launcher);
        iv.setImageDrawable(d);
        iv.setLayoutParams(new GridView.LayoutParams(100, 100));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // TODO Auto-generated method stub
        return iv;
      }
      
    });

//    this.addView(gv);
    this.setNumColumns(GRID_SIZE);

    BitmapFactory.Options o = new BitmapFactory.Options();
    o.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;
    Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher, o);
    int w = bmp.getWidth();

    Logger.debug(String.format("width: %d", w));
    this.setColumnWidth(200);
    this.setStretchMode(this.STRETCH_SPACING_UNIFORM);
    this.
    mGD = new GestureDetector(getContext(),
        new SimpleOnGestureListener() {

          @Override
          public boolean onScroll(MotionEvent e1, MotionEvent e2,
              float distanceX, float distanceY) {
            // beware, it can scroll to infinity
            scrollBy((int) distanceX, (int) distanceY);
            return true;
          }

          @Override
          public boolean onFling(MotionEvent e1, MotionEvent e2, float vX,
              float vY) {/*
            ScrollMapView.this.fling(getScrollX(), getScrollY(), -(int) vX, -(int) vY,
                0, (int) 1000, 0, (int) 1000);
            invalidate(); // don't remember if it's needed
*/            return true;
          }

          @Override
          public boolean onDown(MotionEvent e) {
            /*if (!ScrollMapView.this.isFinished()) { // is flinging
              ScrollMapView.this.forceFinished(true); // to stop flinging on touch
            }*/
            return true; // else won't work
          }
        });

  }
//
//  @Override
//  public boolean onTouchEvent(MotionEvent me) {
//    mGD.onTouchEvent(me);
//    return true;
//  }
}