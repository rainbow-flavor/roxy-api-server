package com.rainbowflavor.roxyapiserver.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringConvertUtilsTest {

    @Test
    @DisplayName("byteToStringTest")
    public void byteToStringTest() {
        //ip address set ipv4 ipv6
        String _1 = " ";
        String _2 = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String _3 = ".";
        String _4 = ":";

        byte[] newBytes1 = _1.getBytes();
        byte[] newBytes2 = _2.getBytes();
        byte[] newBytes3 = _3.getBytes();
        byte[] newBytes4 = _4.getBytes();

        String s1 = StringConvertUtils.bytesToHex(newBytes1);
        String s2 = StringConvertUtils.bytesToHex(newBytes2);
        String s3 = StringConvertUtils.bytesToHex(newBytes3);
        String s4 = StringConvertUtils.bytesToHex(newBytes4);

        assertEquals("20",s1);
        assertEquals("313233343536373839306162636465666768696A6B6C6D6E6F707172737475767778797A4142434445464748494A4B4C4D4E4F505152535455565758595A",s2);
        assertEquals("2E",s3);
        assertEquals("3A",s4);
    }
}