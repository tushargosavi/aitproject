package com.ait.apex;

import java.util.Arrays;

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

    StringBuilder stringBuilder = new StringBuilder("");
    String s = " ";
    for(int i=0;i<bytestr.length;i++)
    {
      stringBuilder.append((char)Platform.getByte(bytestr, Platform.BYTE_ARRAY_OFFSET + i));
    }
    System.out.println(stringBuilder);

  }
}
