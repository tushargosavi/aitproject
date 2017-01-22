package com.ait.apex.row;

import java.util.ArrayList;
import java.util.List;

public class RowMeta {
	List<FieldInfo> fieldInfoList = new ArrayList<>();
	
	void addField(String name, DataType dataType) {
		FieldInfo fieldInfo = new FieldInfo();
		fieldInfo.setName(name);
		fieldInfo.setDataType(dataType);
		
		fieldInfoList.add(fieldInfo);
	}
}
