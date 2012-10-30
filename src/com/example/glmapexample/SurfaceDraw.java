package com.example.glmapexample;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.view.SurfaceHolder.Callback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class SurfaceDraw extends SurfaceView implements Runnable {

  Thread thread = null;
  SurfaceHolder surfaceHolder;
  volatile boolean running = false;
  private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

  
  Random random;
  private int myCanvas_w;
  private int myCanvas_h;
  private Bitmap myCanvasBitmap;
  private Canvas myCanvas;
  private Matrix matrix;
  private Point prev_touch;
  private Point offset;
  private boolean handleTouch;
  
  public SurfaceDraw(Context context, boolean handleTouch) {
    super(context);
    this.handleTouch = handleTouch;
    surfaceHolder = getHolder();
    random = new Random();
    matrix = new Matrix();
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(3);

    offset = new Point(0, 0);
    
    myCanvas_w = 256;
    myCanvas_h = 256;

    myCanvasBitmap = Bitmap.createBitmap(myCanvas_w, myCanvas_h, Bitmap.Config.ARGB_8888);
    myCanvas = new Canvas();
    myCanvas.setBitmap(myCanvasBitmap);
    Drawable d = getContext().getResources().getDrawable(R.drawable.ic_launcher);

    d.draw(myCanvas);
    
    onResume();
  }
  
  @Override
  protected void onMeasure(int w, int h) {
    super.onMeasure(w,  h);
    this.setMeasuredDimension(256,  256);

  }

  public void onResume() {
    running = true;
    
    thread = new Thread(this);
    thread.setPriority(Thread.MIN_PRIORITY);
    thread.start();
  }

  public void onPause() {
    boolean retry = true;
    running = false;
    while (retry) {
      try {
        thread.join();
        retry = false;
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    for (int i = 0;i < 500 && running;i++) {
      if (surfaceHolder.getSurface().isValid() && myCanvas != null) {
        Rect rect = surfaceHolder.getSurfaceFrame();
        
        int w = rect.left + rect.right;
        int h = rect.bottom  - rect.top;
        

        int x = random.nextInt(w-1); 
        int y = random.nextInt(h-1);
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);

        myCanvas.drawPoint(x, y, paint);
        
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawBitmap(myCanvasBitmap, matrix, paint);
        
        surfaceHolder.unlockCanvasAndPost(canvas);
      }
    }
  }
}