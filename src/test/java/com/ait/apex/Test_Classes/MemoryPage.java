package com.ait.apex.Test_Classes;

public class MemoryPage {

    public byte[] page;

    public MemoryPage(byte[] page, int size) {
        page = new byte[1024];
    }

    public byte[] getPage() {
        return page;
    }

    public void setPage(byte[] page) {
        this.page = page;
    }


}
