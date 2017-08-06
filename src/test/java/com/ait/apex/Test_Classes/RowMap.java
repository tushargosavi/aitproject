package com.ait.apex.Test_Classes;
import com.ait.apex.row.Row;

class RowMap {


    Row key;
    int value;
    RowMap next;

    public RowMap() {
    }
    
    public RowMap(Row key, int value) {
        this.key = key;
        this.value = value;
    }
    
    public Row getKey() {
        return key;
    }
    
    public void setKey(Row key) {
        this.key = key;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
}
