package com.ait.apex.row;


public class FieldInfo {
	String name;
	DataType dataType;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public DataType getDataType() {
		return dataType;
	}
	
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	
	public boolean isNull(Object o) {
		if (o == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "RowMeta{" +
				"name='" + name + '\'' +
				", dataType=" + dataType +
				'}';
	}
}
