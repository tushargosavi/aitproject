package com.ait.apex.memory;

import com.ait.apex.platform.BytePlatform;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MemoryManager {

  int pageSize;


  List<MemoryPage> allocatedPages = new ArrayList<>();

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public MemoryPage allocate() {

    ByteBuffer buf= BytePlatform.memoryAllocate(pageSize);

    return new MemoryPage(buf, pageSize);
  }

  public int getPageSize() {
    return pageSize;
  }

  public void freeAll()
  {
    allocatedPages.clear();
  }

}