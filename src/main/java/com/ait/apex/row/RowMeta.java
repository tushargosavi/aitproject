package com.ait.apex.row;

import java.util.ArrayList;
import java.util.List;

public class RowMeta {
	public List<FieldInfo> fieldInfoList = new ArrayList<>();
	
	public void addField(String name, DataType dataType) {
		FieldInfo fieldInfo = new FieldInfo();
		fieldInfo.setName(name);
		fieldInfo.setDataType(dataType);
		
		fieldInfoList.add(fieldInfo);
	}
	
	public void removeField(String name)
	{
		int index = fieldInfoList.indexOf(name);
		fieldInfoList.remove(index);
	}
	
	public List<FieldInfo> getFieldInfoList()
	{
		return fieldInfoList;
	}
}
