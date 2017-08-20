package com.ait.apex.memory;

import java.util.HashMap;

public class MemoryPage
{
  // mostly byte[]
  public byte[] page;
  public int offset;
  public int pageAddress;
  public HashMap<Integer, Address> addressMap;

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

  public HashMap<Integer, Address> getAddressMap() {
    return addressMap;
  }

  public void setAddressMap(HashMap<Integer, Address> addressMap) {
    this.addressMap = addressMap;
  }
}
