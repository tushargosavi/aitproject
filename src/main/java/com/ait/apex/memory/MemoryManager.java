package com.ait.apex.memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryManager {

  int pageSize;

  List<MemoryPage> allocatedPages = new ArrayList<>();

  public MemoryPage allocate() {
    byte[] newBytes = new byte[pageSize];
    return new MemoryPage(newBytes, pageSize);
  }

  public void freeAll()
  {
    allocatedPages.clear();
  }

}