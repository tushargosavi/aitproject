package com.ait.apex.Test_Classes;

import com.ait.apex.row.PojoBasedCoder;
import com.ait.apex.row.Row;

public class RowHashMap {
	
	private int BUCKET_ARRAY_SIZE = 1024;
	private RowMap bucketArray[] = new RowMap[BUCKET_ARRAY_SIZE];
	private static PojoBasedCoder pojoBasedCoder = new PojoBasedCoder();
	
	public RowHashMap() {
	}
	
	public RowHashMap(int initializeSize) {
		
		this.BUCKET_ARRAY_SIZE = initializeSize;
		
	}
	
	public void put(Row key, int value) {
		Row keyRow = pojoBasedCoder.extractKeyRow(key);
		int hash = Math.abs(keyRow.hashCode() % BUCKET_ARRAY_SIZE);
		RowMap entry = new RowMap(keyRow, value);
		
		if(bucketArray[hash] == null){
			bucketArray[hash] = entry;
		}
		else{
			RowMap current =    bucketArray[hash];
			while(current.next != null){
				if(current.getKey().equals(entry.getKey())){
					current.setValue(entry.getValue());
					return;
				}
				current = current.next;
			}
			current.next = entry;
		}
	}
	
	public int get(Row key){
		Row keyRow = pojoBasedCoder.extractKeyRow(key);
		int hash = Math.abs(keyRow.hashCode() % BUCKET_ARRAY_SIZE);
		
		RowMap node = bucketArray[hash];
		while(node != null){
			if(node.getKey().equals(keyRow)){
				return node.getValue();
			}
			node = node.next;
		}
		return 0;
	}
	
}
