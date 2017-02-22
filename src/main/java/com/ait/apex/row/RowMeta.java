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
	
	public boolean isPresent(RowMeta rowMeta, String key) throws NoSuchFieldException, IllegalAccessException {
		boolean flag = false;
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList()) {
			if(key.equals(fieldInfo.getName()))
			{
				flag = true;
			}
		}
		return flag;
	}
	
	public RowMeta removeField(RowMeta rowMeta ,String name) {
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			if(name.equals(fieldInfo.getName()))
			{
				rowMeta.fieldInfoList.remove(fieldInfo);
				break;
			}
		}
		return rowMeta;
	}
	
	public DataType getDataType(RowMeta rowMeta, String key) {
		DataType dataType = null;
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList()) {
			if (key.equals(fieldInfo.getName())) {
				dataType = fieldInfo.getDataType();
			}
		}
		return dataType;
	}
	
	public List<FieldInfo> getFieldInfoList() {
		return fieldInfoList;
	}
	
	@Override
	public String toString() {
		return "RowMeta{" +
				"fieldInfoList=" + fieldInfoList +
				'}';
	}
}
