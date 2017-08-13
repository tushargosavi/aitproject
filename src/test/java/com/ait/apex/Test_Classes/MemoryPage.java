package com.ait.apex.Test_Classes;

import com.ait.apex.row.PojoBasedCoder;
import com.ait.apex.row.Row;

import java.util.HashMap;

public class MemoryPage {

    public byte[] page;
    public int offset;
    public int pageAddress;
    public HashMap<Integer, Integer> addressMap;

    public MemoryPage() {
        addressMap = new HashMap<>();
        page = new byte[4096];
        offset = 0;
        pageAddress = 0;
    }

    public byte[] getPage() {
        return page;
    }

    public void setPage(byte[] page) {
        this.page = page;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageAddress() {
        return pageAddress;
    }

    public void setPageAddress(int pageAddress) {
        this.pageAddress = pageAddress;
    }

    private void insert(Row row){
        PojoBasedCoder coder = new PojoBasedCoder();
        Row keyRow = coder.extractKeyRow(row);
        int totalLen = row.dataBytes[0];


    }
}
