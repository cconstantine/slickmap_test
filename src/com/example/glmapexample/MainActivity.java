package com.example.glmapexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

 
public class MainActivity extends Activity {

  private MapFrameView map;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      map = new MapFrameView(this, 3);
      Logger.debug(String.format("isHardwareAccellerated: %b", map.isHardwareAccelerated()));
      setContentView(map);
  }
  
  @Override
  public void onResume() {
    super.onResume();
    Logger.debug("onResume");
  }
  
  @Override
  public void onPause() { 
    super.onPause();
    Logger.debug("onPause");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  
}
