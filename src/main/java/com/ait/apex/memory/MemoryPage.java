package com.ait.apex.memory;

public class MemoryPage
{
  // mostly byte[]
  final Object base;
  final int size;

  public MemoryPage(Object base, int size)
  {
    this.base = base;
    this.size = size;
  }

  public Object getBase() {
    return base;
  }
}
