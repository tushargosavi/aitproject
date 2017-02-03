package com.ait.apex.row;

public interface Coder {
	Row encoder(RowMeta rowMeta, Object o) throws NoSuchFieldException, IllegalAccessException;
	Object decoder(RowMeta rowMeta, Row row);
}
                                    