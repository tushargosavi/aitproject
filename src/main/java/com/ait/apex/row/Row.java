package com.ait.apex.row;

import java.util.Arrays;

public class Row {
	public byte[] dataBytes;
	
	public byte[] getDataBytes() {
		return dataBytes;
	}
	
	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}

	public void addDataBytes(byte[] dataBytes, int offset)
	{
		
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		
		Row row = (Row) object;
		
		return Arrays.equals(dataBytes, row.dataBytes);
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(dataBytes);
	}
}
