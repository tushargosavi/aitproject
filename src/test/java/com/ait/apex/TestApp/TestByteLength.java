package com.ait.apex.TestApp;


import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.FieldInfo;
import com.ait.apex.row.Row;
import com.ait.apex.row.RowMeta;

public class TestByteLength {
	
	final int INITIAL_VALUE = 12; //Initial value : totalLength + rowLength + valLength = 4 + 4 + 4 = 12
	
	public int getKeyLength(RowMeta rowMeta, Object object) throws NoSuchFieldException, IllegalAccessException {
		int keyLength = 0;
		
		for(FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			switch (fieldInfo.getDataType())
			{
				case BOOLEAN :
					keyLength += 1;
					break;
				case INTEGER:
					keyLength += 4;
					break;
				case DOUBLE:
					keyLength +=8;
					break;
				case FLOAT:
					keyLength +=4;
					break;
				case LONG:
					keyLength +=8;
					break;
				case CHARACTER:
					keyLength += 2;
					break;
				case STRING:
					keyLength += 4;
					String strValue = (String) object.getClass().getField(fieldInfo.getName()).get(object);
					keyLength += strValue.getBytes().length;
					keyLength += 4;
			}
		}
		return keyLength;
	}
	
	public int getValLength(RowMeta rowMeta, Object object) throws NoSuchFieldException, IllegalAccessException {
		int valLength = 0;
		for(FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			switch (fieldInfo.getDataType())
			{
				case BOOLEAN :
					valLength += 1;
					break;
				case INTEGER:
					valLength += 4;
					break;
				case DOUBLE:
					valLength +=8;
					break;
				case FLOAT:
					valLength +=4;
					break;
				case LONG:
					valLength +=8;
					break;
				case CHARACTER:
					valLength += 2;
					break;
				case STRING:
					valLength += 4;
					String strValue = (String) object.getClass().getField(fieldInfo.getName()).get(object);
					valLength += strValue.getBytes().length;
					valLength += 4;
			}
		}
		return valLength;
	}
	
	public int getRowLength(AggregationSchema schema, Object object) throws NoSuchFieldException, IllegalAccessException {
		int rowLength = INITIAL_VALUE + getKeyLength(schema.keySchema, object) + getValLength(schema.valueSchema, object);
		return rowLength;
	}
	
	public int getKeyVarOffset(RowMeta rowMeta)
	{
		//space taken by initial values
		int len = INITIAL_VALUE;
		
		for(FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			switch (fieldInfo.getDataType())
			{
				case BOOLEAN :
					len += 1;
					break;
				case INTEGER:
					len += 4;
					break;
				case DOUBLE:
					len +=8;
					break;
				case FLOAT:
					len +=4;
					break;
				case LONG:
					len +=8;
					break;
				case CHARACTER:
					len += 2;
					break;
				case STRING:
					len += 4;
					len += 4;
			}
		}
		return len;
	}
	
	
}
