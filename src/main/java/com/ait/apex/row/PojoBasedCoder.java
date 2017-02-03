package com.ait.apex.row;


import com.ait.apex.platform.Platform;

public class PojoBasedCoder implements Coder{
	
	@Override
	public Row encoder(RowMeta rowMeta, Object o) throws NoSuchFieldException, IllegalAccessException {
		Row row = new Row();
		long offset = 0;
		ByteLength length = new ByteLength();
		int varoffset = length.getVarOffset(rowMeta);
		int size = length.getByteLength(rowMeta, o);
		row.dataBytes = new byte[size];
		
		for(FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			switch (fieldInfo.getDataType())
			{
				case STRING:
					String str = (String) o.getClass().getField(fieldInfo.getName()).get(o);
					Platform.putString(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, varoffset, str);
					offset += 4;
					break;
				
				case INTEGER:
					int intVal = (int) o.getClass().getField(fieldInfo.getName()).get(o);
					Platform.putInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, intVal);
					offset += 4;
					break;
					
				case LONG:
					long longVal = (long) o.getClass().getField(fieldInfo.getName()).get(o);
					Platform.putLong(row.dataBytes, Platform.LONG_ARRAY_OFFSET + offset, longVal);
					offset += 8;
					break;
					
				case CHARACTER:
					break;
			}
		}
		return row;
	}
	
	
	@Override
	public Object decoder(RowMeta rowMeta, Row row) {
		return null;
	}
}
