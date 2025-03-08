package com.code.common.utils.encode;


public interface IByteEncoder {
    String encode(byte[] bytes);

    byte[] decode(String str);
}
