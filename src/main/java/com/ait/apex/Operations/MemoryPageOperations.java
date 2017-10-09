package com.ait.apex.Operations;

import com.ait.apex.memory.Address;
import com.ait.apex.memory.MemoryPage;
import com.ait.apex.row.PojoBasedCoder;
import com.ait.apex.row.Row;

public class MemoryPageOperations {

    static PojoBasedCoder coder = new PojoBasedCoder();

    public MemoryPage insert(Row row, MemoryPage memoryPage){
        Address address = new Address();
        System.arraycopy(row.getDataBytes(), 0, memoryPage.getPage(), memoryPage.getOffset(), row.dataBytes[0]);
        memoryPage.setPageAddress(memoryPage.getOffset());
        address.setAddress(0, memoryPage.getPageAddress());
        memoryPage.setOffset(memoryPage.getOffset() + row.dataBytes[0]);
        memoryPage.addressMap.put(rowHashCode(coder.extractKeyRow(row)), address);

        return memoryPage;
    }

    public static int rowHashCode(Row row){
        Row keyRow = coder.extractKeyRow(row);
        int hashCode = ((keyRow.getDataBytes()).hashCode());
        return hashCode % 1024;
    }

}
