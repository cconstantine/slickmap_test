package com.example.glmapexample;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

class MapView extends ViewGroup {

  private Point prev_touch;
  private GridView layer;
  private Point offset = new Point(0, 0);
  
  public MapView(Context context) {
    super(context);
    
    layer = new GridView(context);
    this.addView(layer);
    
    ImageView iv = new ImageView(context);
    
    layer.addView(iv);
  }

  private void addView(GridView layer2) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean onTouchEvent(MotionEvent me) {
    
    int x = (int) me.getX();
    int y = (int) me.getY();

    Logger.debug(String.format("x: %d, y: %d", x, y));
    if (prev_touch != null) {

      Logger.debug(String.format("prev_touch.x: %d, prev_touch.y: %d", prev_touch.x, prev_touch.y));

      layer.setTranslationX(x - prev_touch.x);
      layer.setTranslationY(y - prev_touch.y);
      prev_touch.x = x;
      prev_touch.y = y;
       
    } else {
      prev_touch = new Point(x, y);
    }
    return true;
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    // TODO Auto-generated method stub
    
  }

}