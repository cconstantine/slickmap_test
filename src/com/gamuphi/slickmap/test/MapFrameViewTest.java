package com.gamuphi.slickmap.test;


import com.gamuphi.slickmap.MapFrameView;

import junit.framework.Assert;

import android.content.Context;
import android.test.AndroidTestCase;


class MapFrameViewMock extends MapFrameView {

  public MapFrameViewMock(Context context) {
    super(context);
  }
  
  public int getZoom() {
    return zoom;
  }
  
}
public class MapFrameViewTest extends AndroidTestCase {

  public void test_createMapFrameView() throws Throwable {
    MapFrameViewMock mfv = new MapFrameViewMock(getContext());

    Assert.assertEquals(0, mfv.getZoom());
  }
}
