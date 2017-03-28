package com.ait.apex.TestApp;


import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.platform.Platform;
import com.ait.apex.row.FieldInfo;
import com.ait.apex.row.Row;
import com.ait.apex.row.VariableFunctions;

public class TestPojoBasedCoder {
	
	VariableFunctions variableFunctions = new VariableFunctions();
	TestByteLength byteLength = new TestByteLength();
	
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
		
		int keyVarOffset = byteLength.getKeyVarOffset(schema.keySchema);
		
		for (FieldInfo fieldInfo : schema.keySchema.getFieldInfoList())
		{
			switch (fieldInfo.getDataType())
			{
				case STRING:
					String str = (String) object.getClass().getField(fieldInfo.getName()).get(object);
					variableFunctions.putString(row.dataBytes, Platform.INT_ARRAY_OFFSET + offset, keyVarOffset, str);
					offset += 8;
					offset += str.getBytes().length;
					keyVarOffset += str.getBytes().length;
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
	
	public Row extractKeyRow(Row row, int startIndex, int length)
	{
		Row resultRow = new Row();
		resultRow.dataBytes = new byte[length];
		for(int i = 0; i < length; i++)
		{
			resultRow.dataBytes[i] = Platform.getByte(row.dataBytes, Platform.BYTE_ARRAY_OFFSET + startIndex + i);
		}
		return resultRow;
	}
	
	public Row extractValRow(Row row, int startIndex, int length)
	{
		Row resultRow = new Row();
		resultRow.dataBytes = new byte[length];
		for(int i = 0; i < length; i++)
		{
			resultRow.dataBytes[i] = Platform.getByte(row.dataBytes, Platform.BYTE_ARRAY_OFFSET + startIndex + i);
		}
		return resultRow;
	}
}



