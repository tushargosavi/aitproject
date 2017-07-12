package com.ait.apex.Test_Classes;

import com.ait.apex.row.Row;

public class RowHashMap {

    private int BUCKET_ARRAY_SIZE = 1024;
    private RowMap bucketArray[] = new RowMap[BUCKET_ARRAY_SIZE];

    public RowHashMap() {
    }

    public RowHashMap(int initializeSize) {

        this.BUCKET_ARRAY_SIZE = initializeSize;

    }

    public void put(int key, Row value){
        int hash = Math.abs(value.hashCode() % BUCKET_ARRAY_SIZE);
        RowMap entry = new RowMap(key, value);

        if(bucketArray[hash] == null){
            bucketArray[hash] = entry;
        }
        else{
            RowMap current = bucketArray[hash];
            while(current.getNext() != null){
                if(current.getKey() == entry.getKey()){
                    current.setValue(entry.getValue());
                    return;
                }
                current = current.getNext();
            }
            current.next = entry;
        }
    }

//    public Row get(int key){
//
//    }
}
