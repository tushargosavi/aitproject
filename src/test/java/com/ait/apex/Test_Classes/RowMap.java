package com.ait.apex.Test_Classes;
import com.ait.apex.row.Row;

class RowMap {


    Row value;
    int key;
    RowMap next;

    public RowMap() {
    }

    public RowMap(int key, Row value) {
        this.value = value;
        this.key = key;
    }

    public Row getValue() {
        return value;
    }

    public void setValue(Row value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public RowMap getNext() {
        return next;
    }

    public void setNext(RowMap next) {
        this.next = next;
    }
}
