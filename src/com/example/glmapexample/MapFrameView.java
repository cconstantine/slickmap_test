package com.example.glmapexample;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

class ViewHolder {

  public View view;
  public Point offset;
  
  public Point loc;

}


class MapFrameView extends FrameLayout {

  private GestureDetector mGD;
  
  ViewHolder[] views = null;
  static int subview_dim = 256;

  private boolean resumed = false;

  private int layout_width;

  private int layout_height;

  private int rows;

  private int cols;
  
  public MapFrameView(Context context) {
    super(context);
    
    mGD = new GestureDetector(getContext(),
        new SimpleOnGestureListener() {

          @Override
          public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            int delta_x = (int) distanceX;
            int delta_y = (int) distanceY;
            if (views != null) {
             // Logger.debug(String.format("Time delta: %d", e2.getEventTime() - e1.getEventTime()));

              for (ViewHolder vh : MapFrameView.this.views) {
                vh.offset.x -= delta_x;
                vh.offset.y -= delta_y;
                
                if (vh.offset.x < - (subview_dim + (subview_dim >> 1))) {
                  vh.offset.x += rows * subview_dim;
                } else if (vh.offset.x + subview_dim > layout_width + (subview_dim + (subview_dim >> 1))) {
                  vh.offset.x -= rows * subview_dim;
                }

                if (vh.offset.y < - (subview_dim + (subview_dim >> 1))) {
                  vh.offset.y += cols * subview_dim;
                } else if (vh.offset.y + subview_dim > layout_height + (subview_dim + (subview_dim >> 1))) {
                  vh.offset.y -= cols * subview_dim;
                }
                
                vh.view.setTranslationX(vh.offset.x);
                vh.view.setTranslationY(vh.offset.y);
              }
            }
            return true;
          }

          @Override
          public boolean onFling(MotionEvent e1, MotionEvent e2, float vX,
              float vY) {
            
            return true;
          }

          @Override
          public boolean onDown(MotionEvent e) {
            return true;
          }
        });
  
  }

  @SuppressLint({ "DrawAllocation", "ParserError" })
  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b)
  {
    super.onLayout(changed, l, t, r, b);
    Logger.debug(String.format("changed: %b, l: %d, t: %d, r: %d, b: %d", changed, l, t, r, b));

    if (changed) {

      layout_width = r-l;
      layout_height = b-t;
      
      rows = (int) (2 + Math.ceil((float)layout_width / subview_dim));
      cols = (int) (2 + Math.ceil((float)layout_height / subview_dim));

      ViewHolder[] newViews = new ViewHolder[rows*cols];
      this.removeAllViews();
      for(int y = 0;y < cols;y++) {
        for(int x = 0;x < rows;x++) {
          ViewHolder vh = new ViewHolder();
          Drawable d = getContext().getResources().getDrawable(R.drawable.kitty);
          ImageView iv = new ImageView(getContext());

          iv.setLayoutParams(new GridView.LayoutParams(subview_dim, subview_dim));
          iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
          iv.setImageDrawable(d);
          
          vh.view = iv;
          vh.offset = new Point(x*subview_dim, y*subview_dim);
          vh.loc = new Point(x, y);
          newViews[x + y*rows] = vh;
          addView(vh.view);
          
          Logger.debug(String.format("(%d, %d) offset.x: %d, offset.y: %d", x, y, vh.offset.x, vh.offset.y));
          
          vh.view.setTranslationX(vh.offset.x);
          vh.view.setTranslationY(vh.offset.y);
        }
      }

      views = newViews;
    }
  }
  

  @Override
  public boolean onTouchEvent(MotionEvent me) {
    return mGD.onTouchEvent(me);
  }
}