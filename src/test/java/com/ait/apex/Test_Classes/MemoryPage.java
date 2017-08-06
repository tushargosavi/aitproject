package com.ait.apex.Test_Classes;

import com.ait.apex.platform.Platform;
import com.ait.apex.row.Row;

public class MemoryPage {

    private byte[] page;
    private int index;

    public MemoryPage() {
        page = new byte[1024];
    }

    public byte[] getPage() {
        return page;
    }

    public void setRowAtIndex(Row row, int index){
        Platform.putByte(page, Platform.BYTE_ARRAY_OFFSET + index, row);
    }

    public byte getPageDetailsAtIndex(int index) {
        return Platform.getByte(page, Platform.BYTE_ARRAY_OFFSET + index);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
