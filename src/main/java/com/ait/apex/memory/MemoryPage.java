package com.ait.apex.memory;

public class MemoryPage
{
  // mostly byte[]
  final Object base;
  final int size;

  MemoryPage(Object base, int size)
  {
    this.base = base;
    this.size = size;
  }

  Object getBase() {
    return base;
  }
}
