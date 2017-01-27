package com.ait.apex.row;

public class Row {
	private int offset;
	private int basicByte = 8;
	private byte[] keyBytes[];
	private byte[] valueBytes[];
	
	RowMeta schema = new RowMeta();
	
	public void setSchema(RowMeta schema) {
		this.schema = schema;
	}
	public RowMeta createSchema()
	{
		RowMeta rowMeta = new RowMeta();
		
		//rowMeta.addField("Field1");
		return null;
	}
}
