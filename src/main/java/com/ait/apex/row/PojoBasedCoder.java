package com.ait.apex.row;


import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.platform.Platform;

public class PojoBasedCoder{
	
	VariableFunctions variableFunctions = new VariableFunctions();
	ByteLength byteLength = new ByteLength();
	
	public Row encoder(AggregationSchema schema, Object object) throws NoSuchFieldException, IllegalAccessException
	{
		
		Row row = new Row();
		row.dataBytes = new byte[byteLength.getRowLength(schema, object)];
		int offset = 0;
		
		//Total length
		Platform.putInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, byteLength.getRowLength(schema, object));
		offset += 4;
		
		//Key length
		Platform.putInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, byteLength.getKeyLength(schema.keySchema, object));
		offset += 4;
		
		//Value length
		Platform.putInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, byteLength.getValLength(schema.valueSchema, object));
		offset += 4;
<<<<<<< HEAD
		/*
		Problem lies here. The keyvaroffset is being calculated for the entire schema instead of being
		calculated for the current variable.
		This needs to be fixed.
		FIX IT.
		 */
		//int keyVarOffset = byteLength.getKeyVarOffset(schema.keySchema);
=======
		
		int keyVarOffset = byteLength.getKeyVarOffset(schema.keySchema);
>>>>>>> 248b3ac67de6bb5fa32f960d18f27e44fe91965c
		
		for (FieldInfo fieldInfo : schema.keySchema.getFieldInfoList())
		{
			switch (fieldInfo.getDataType())
			{
				case STRING:
<<<<<<< HEAD
					int keyVarOffset = offset + 8;
					String str = (String) object.getClass().getField(fieldInfo.getName()).get(object);
					variableFunctions.putString(row.dataBytes, offset, keyVarOffset, str);
					offset += 8;
					offset += str.getBytes().length;
					keyVarOffset += str.getBytes().length + 8;
=======
					String str = (String) object.getClass().getField(fieldInfo.getName()).get(object);
					variableFunctions.putString(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, keyVarOffset, str);
					offset += 8;
					offset += str.getBytes().length;
					keyVarOffset += str.getBytes().length;
>>>>>>> 248b3ac67de6bb5fa32f960d18f27e44fe91965c
					break;
				
				case INTEGER:
					int intVal = (int) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, intVal);
					offset += 4;
					break;
				
				case LONG:
					long longVal = (long) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putLong(row.dataBytes, Platform.LONG_ARRAY_OFFSET + offset, longVal);
					offset += 8;
					break;
				
				case CHARACTER:
					char charVal = (char) object.getClass().getField(fieldInfo.getName()).get(object);
					variableFunctions.putChar(row.dataBytes, Platform.BYTE_ARRAY_OFFSET + offset, charVal);
					offset += 2;
					break;
				
				case DOUBLE:
					double doubleVal = (double) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putDouble(row.dataBytes, Platform.DOUBLE_ARRAY_OFFSET + offset, doubleVal);
					offset += 8;
					break;
				
				case BOOLEAN:
					boolean boolval = (boolean) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putBoolean(row.dataBytes, Platform.BOOLEAN_ARRAY_OFFSET + offset, boolval);
					offset += 1;
					break;
				
				case FLOAT:
					float floatVal = (float) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putFloat(row.dataBytes, Platform.FLOAT_ARRAY_OFFSET + offset, floatVal);
					offset += 4;
					break;
			}
		}
		
		
		for (FieldInfo fieldInfo : schema.valueSchema.getFieldInfoList())
		{
			switch (fieldInfo.getDataType()) {
				case INTEGER:
					int intVal = (int) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, intVal);
					offset += 4;
					break;
				
				case LONG:
					long longVal = (long) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putLong(row.dataBytes, Platform.LONG_ARRAY_OFFSET + offset, longVal);
					offset += 8;
					break;
				
				case CHARACTER:
					char charVal = (char) object.getClass().getField(fieldInfo.getName()).get(object);
					variableFunctions.putChar(row.dataBytes, Platform.BYTE_ARRAY_OFFSET + offset, charVal);
					offset += 2;
					break;
				
				case DOUBLE:
					double doubleVal = (double) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putDouble(row.dataBytes, Platform.DOUBLE_ARRAY_OFFSET + offset, doubleVal);
					offset += 8;
					break;
				
				case BOOLEAN:
					boolean boolval = (boolean) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putBoolean(row.dataBytes, Platform.BOOLEAN_ARRAY_OFFSET + offset, boolval);
					offset += 1;
					break;
				
				case FLOAT:
					float floatVal = (float) object.getClass().getField(fieldInfo.getName()).get(object);
					Platform.putFloat(row.dataBytes, Platform.FLOAT_ARRAY_OFFSET + offset, floatVal);
					offset += 4;
					break;
			}
		}
		return row;
	}
	
	public Row extractKeyRow(Row row)
	{
		
		int length = Platform.getInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + 4);
		Row resultRow = new Row();
		resultRow.dataBytes = new byte[length];
		for(int i = 0; i < length; i++)
		{
			resultRow.dataBytes[i] = Platform.getByte(row.dataBytes, Platform.BYTE_ARRAY_OFFSET + 12 + i);
		}
		return resultRow;
	}
	
	public Row extractValRow(Row row)
	{
		int length = Platform.getInt(row.dataBytes, Platform.INT_ARRAY_OFFSET + 8);
		int startIndex = Platform.getInt(row.dataBytes, Platform.INT_ARRAY_OFFSET) - length;
		Row resultRow = new Row();
		resultRow.dataBytes = new byte[length];
		for(int i = 0; i < length; i++)
		{
			resultRow.dataBytes[i] = Platform.getByte(row.dataBytes, Platform.BYTE_ARRAY_OFFSET + startIndex + i);
		}
		return resultRow;
	}
	
	public void updateRow(Row oldRow, byte[] updatedData, int length)
	{
		int startIndex = Platform.getInt(oldRow.dataBytes, Platform.INT_ARRAY_OFFSET) - length;
		for(int i = 0; i < length ;i++)
		{
			Platform.putInt(oldRow.dataBytes, Platform.BYTE_ARRAY_OFFSET + startIndex + i, updatedData[i]);
		}
	}
}
