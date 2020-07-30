package ru.job4j.inout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4;
        float flt = 5.0f;
        double dbl = 6.0;
        char chr = 't';
        boolean bln = true;

        LOG.debug("Byte = {}, short = {}, int = {}, long = {}, float = {}, double = {}, char = {}, boolean = {}",
                    b, s, i, l, flt, dbl, chr, bln);
    }
}
