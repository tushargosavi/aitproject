package com.ait.apex.Test_Classes;

public class MemoryPage {

    public byte[] page;
    public int offset;
    public int pageAddress;

    public MemoryPage() {
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
}
