package com.example.glmapexample;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;

import com.gamuphi.slickmap.Logger;
import com.gamuphi.slickmap.MBTileSource;
import com.gamuphi.slickmap.MapboxTileSource;
import com.gamuphi.slickmap.SlickMap;

public class MainActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Logger.debug("onCreate(Bundle savedInstanceState)");
 
    try {
      File httpCacheDir = new File(getCacheDir(), "http");
      long httpCacheSize = 300 * 1024 * 1024; // 100 MiB
      HttpResponseCache.install(httpCacheDir, httpCacheSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    setContentView(R.layout.activity_main);
    
    final SlickMap mfv = (SlickMap) this.findViewById(R.id.map);
    AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() {

        @Override
        public void run() {
          try {
            File f = new File(Environment.getExternalStorageDirectory(), "map.mbtiles");
            mfv.setTileSource(new MBTileSource(MainActivity.this, f));
//            mfv.setTileSource(new MapboxTileSource("http://a.tiles.mapbox.com/v1/cconstantine.map-gyhx1cl1.json"));
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

}
