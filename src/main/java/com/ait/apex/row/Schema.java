package com.ait.apex.row;


import java.util.ArrayList;
import java.util.List;

public class Schema
{
	List<RowMeta> rowMetaList = new ArrayList<>();
	
	void addField(String name, DataType dataType)
	{
		RowMeta rowMeta = new RowMeta();
		rowMeta.setName(name);
		rowMeta.setDataType(dataType);
		
		rowMetaList.add(rowMeta);
	}
}
