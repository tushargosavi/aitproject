package com.ait.apex.Test_Classes;

import java.util.ArrayList;
import java.util.List;

public class MemoryBlock {

    List<MemoryPage> pageList = new ArrayList<>();

    public MemoryBlock() {
    }

    public MemoryBlock(List<MemoryPage> pageList) {
        this.pageList = pageList;
    }

    public List<MemoryPage> getPageList() {
        return pageList;
    }

    public void setPageList(List<MemoryPage> pageList) {
        this.pageList = pageList;
    }
}
