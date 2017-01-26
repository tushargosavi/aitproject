package com.ait.apex;

import com.ait.apex.platform.Platform;
import org.junit.Test;

import java.util.Arrays;

public class PlatformTest
{
	@Test
	public void platformTest()
	{
		byte[] data = new byte[25];
		System.out.println(Arrays.toString(data));
		Platform.putLong(data,Platform.LONG_ARRAY_OFFSET, 123124);
		System.out.println(Arrays.toString(data));
		Platform.putDouble(data, Platform.DOUBLE_ARRAY_OFFSET+1, 347.1);
		System.out.println(Arrays.toString(data));
		Platform.putInt(data, Platform.INT_ARRAY_OFFSET+2, 1234);
		System.out.println(Arrays.toString(data));
		System.out.println(Platform.getLong(data, Platform.LONG_ARRAY_OFFSET));
		System.out.println(Platform.getDouble(data, Platform.DOUBLE_ARRAY_OFFSET+1));
		System.out.println(Platform.getInt(data, Platform.INT_ARRAY_OFFSET+2));
	}
	
	@Test
	public void testPlatFormBytes()
	{
		byte[] data;
		
		System.out.println(Platform.FLOAT_ARRAY_OFFSET);
		System.out.println(Platform.INT_ARRAY_OFFSET);
		System.out.println(Platform.BOOLEAN_ARRAY_OFFSET);
		System.out.println(Platform.LONG_ARRAY_OFFSET);
		System.out.println(Platform.DOUBLE_ARRAY_OFFSET);
	}
}
