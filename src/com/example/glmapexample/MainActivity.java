package com.example.glmapexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.gamuphi.slickmap.MapFrameView;

public class MainActivity extends Activity {

  private MapFrameView map;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      map = new MapFrameView(this, 3);
      setContentView(map);
  }
  

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  
}
