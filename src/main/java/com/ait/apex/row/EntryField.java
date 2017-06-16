
package com.ait.apex.row;

public class EntryField {
	Row completeRow;
	Row valRow;
	
	public EntryField(Row completeRow, Row valRow) {
		this.completeRow = completeRow;
		this.valRow = valRow;
	}
	
	public Row getCompleteRow() {
		return completeRow;
	}
	
	public Row getValRow() {
		return valRow;
	}
}
