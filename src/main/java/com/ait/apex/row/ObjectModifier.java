package com.ait.apex.row;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectModifier {
	
	public boolean checkFieldName(RowMeta rowMeta, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		boolean flag = false;
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			String name = (String) fieldInfo.getClass().getField("name").get(fieldInfo);
			
			if(fieldName.equals(name))
			{
				flag = true;
			}
		}
		return flag;
	}
	
	public RowMeta addMetaField(RowMeta rowMeta, String fieldName, DataType dataType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method method = rowMeta.getClass().getMethod("addField", String.class, DataType.class);
		method.invoke(rowMeta, fieldName, dataType);
		
		return rowMeta;
	}
}
