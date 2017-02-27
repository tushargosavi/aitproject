package com.ait.apex;

import java.util.Arrays;

import com.ait.apex.AdInfoTest.AdInfo;
import org.junit.Test;

import com.ait.apex.platform.Platform;

/**
 * Unit test for simple App.
 */
public class AppTest
{
  @Test
  public void stringTest()
  {
    String str = "akshay";
    byte[] bytestr = str.getBytes();

    for(int i=0;i<bytestr.length;i++)
    {
      Platform.putByte(bytestr, Platform.BYTE_ARRAY_OFFSET + i, bytestr[i]);
    }
    System.out.println(Arrays.toString(bytestr));

    System.out.println(Platform.getInt(bytestr, Platform.INT_ARRAY_OFFSET));

    StringBuilder stringBuilder = new StringBuilder("");
    String s = " ";
    for(int i=0;i<bytestr.length;i++)
    {
      stringBuilder.append((char)Platform.getByte(bytestr, Platform.BYTE_ARRAY_OFFSET + i));
    }
    System.out.println(stringBuilder);

  }

  @Test
  public void intTest()
  {
    byte[] byteInt = new byte[1];

    Platform.putInt(byteInt, Platform.INT_ARRAY_OFFSET, 7);
    System.out.println(Arrays.toString(byteInt));

    System.out.println(Platform.getInt(byteInt, Platform.INT_ARRAY_OFFSET));
  }
  
  @Test
  public void test()
  {
    AdInfo adInfo = new AdInfo("LA", null, "LA", 123,0L,false);
    System.out.println(adInfo.toString());
  }
  
}
