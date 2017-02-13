package com.ait.apex;

import com.ait.apex.platform.Platform;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PlatformTest
{
	@Test
	public void platformTest()
	{
		byte[] data = new byte[25];
		System.out.println(Arrays.toString(data));
		Platform.putLong(data,Platform.LONG_ARRAY_OFFSET, 9890712L);
		System.out.println(Arrays.toString(data));
		Platform.putDouble(data, Platform.DOUBLE_ARRAY_OFFSET + 8, Double.MAX_VALUE);
		System.out.println(Arrays.toString(data));
		Platform.putInt(data, Platform.INT_ARRAY_OFFSET + 16, 1234);
		System.out.println(Arrays.toString(data));
		System.out.println(Platform.getLong(data, Platform.LONG_ARRAY_OFFSET));
		System.out.println(Long.MAX_VALUE);
		System.out.println(Platform.getDouble(data, Platform.DOUBLE_ARRAY_OFFSET + 8));
		System.out.println(Double.MAX_VALUE);
		System.out.println(Platform.getInt(data, Platform.INT_ARRAY_OFFSET + 16));
		System.out.println(Character.MAX_VALUE);
	}
	
	/*
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	[-12, -32, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	[-12, -102, -103, -103, -103, -103, -79, 117, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	[-12, -102, -46, 4, 0, 0, -79, 117, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	8480559573396265716
	347.0625000179658
	1234
	 */
	
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
