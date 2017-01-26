package com.ait.apex.row;

import com.ait.apex.platform.Platform;

public class Encoder {

	public void encodeInt(byte[] data, int intNum, long offset) {
		Platform.putInt(data, Platform.INT_ARRAY_OFFSET+offset, intNum);
	}
	
	public void encodeLong(byte[] data, long longNum, long offset) {
		Platform.putLong(data, Platform.LONG_ARRAY_OFFSET+offset, longNum);
	}
	
	public void encodeString(byte[] data, String string, long offset) {
		
	}
	
	public void encodeBoolean(byte[] data, boolean bool, long offset) {
		Platform.putBoolean(data, Platform.BOOLEAN_ARRAY_OFFSET+offset, bool);
	}
	
	public void encodeFloat(byte[] data, float floatNum, long offset) {
		Platform.putFloat(data, Platform.FLOAT_ARRAY_OFFSET+offset, floatNum);
	}
	
	public void encodeDouble(byte[] data, double doubleNum, long offset) {
		Platform.putDouble(data, Platform.DOUBLE_ARRAY_OFFSET+offset, doubleNum);
	}
}
