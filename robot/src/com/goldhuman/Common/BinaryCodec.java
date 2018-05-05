package com.goldhuman.Common;

public class BinaryCodec {

    /** Empty char array. */
    private static final char[] EMPTY_CHAR_ARRAY = new char[0];

    /** Empty byte array. */
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /** Mask for bit 0 of a byte. */
    private static final int BIT_0 = 1;

    /** Mask for bit 1 of a byte. */
    private static final int BIT_1 = 0x02;

    /** Mask for bit 2 of a byte. */
    private static final int BIT_2 = 0x04;

    /** Mask for bit 3 of a byte. */
    private static final int BIT_3 = 0x08;

    /** Mask for bit 4 of a byte. */
    private static final int BIT_4 = 0x10;

    /** Mask for bit 5 of a byte. */
    private static final int BIT_5 = 0x20;

    /** Mask for bit 6 of a byte. */
    private static final int BIT_6 = 0x40;

    /** Mask for bit 7 of a byte. */
    private static final int BIT_7 = 0x80;

    private static final int[] BITS = {BIT_0, BIT_1, BIT_2, BIT_3, BIT_4, BIT_5, BIT_6, BIT_7};

 
    /**
     * Decodes a String where each char of the String represents an ascii '0' or '1'.
     * 
     * @param ascii
     *                  String of '0' and '1' characters
     * @return the raw encoded binary where each bit corresponds to a byte in the byte array argument
     * @see org.apache.commons.codec.Decoder#decode(Object)
     */
    public byte[] toByteArray(String ascii) {
        if (ascii == null) {
            return EMPTY_BYTE_ARRAY;
        }
        return fromAscii(ascii.toCharArray());
    }

    // ------------------------------------------------------------------------
    //
    // static codec operations
    //
    // ------------------------------------------------------------------------
    /**
     * Decodes a byte array where each char represents an ascii '0' or '1'.
     * 
     * @param ascii
     *                  each char represents an ascii '0' or '1'
     * @return the raw encoded binary where each bit corresponds to a char in the char array argument
     */
    public static byte[] fromAscii(char[] ascii) {
        if (ascii == null || ascii.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        // get length/8 times bytes with 3 bit shifts to the right of the length
        byte[] l_raw = new byte[ascii.length >> 3];
        /*
         * We decr index jj by 8 as we go along to not recompute indices using multiplication every time inside the
         * loop.
         */
        for (int ii = 0, jj = ascii.length - 1; ii < l_raw.length; ii++, jj -= 8) {
            for (int bits = 0; bits < BITS.length; ++bits) {
                if (ascii[jj - bits] == '1') {
                    l_raw[ii] |= BITS[bits];
                }
            }
        }
        return l_raw;
    }

    /**
     * Decodes a byte array where each byte represents an ascii '0' or '1'.
     * 
     * @param ascii
     *                  each byte represents an ascii '0' or '1'
     * @return the raw encoded binary where each bit corresponds to a byte in the byte array argument
     */
    public static byte[] fromAscii(byte[] ascii) {
        if (ascii == null || ascii.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        // get length/8 times bytes with 3 bit shifts to the right of the length
        byte[] l_raw = new byte[ascii.length >> 3];
        /*
         * We decr index jj by 8 as we go along to not recompute indices using multiplication every time inside the
         * loop.
         */
        for (int ii = 0, jj = ascii.length - 1; ii < l_raw.length; ii++, jj -= 8) {
            for (int bits = 0; bits < BITS.length; ++bits) {
                if (ascii[jj - bits] == '1') {
                    l_raw[ii] |= BITS[bits];
                }
            }
        }
        return l_raw;
    }

    /**
     * Converts an array of raw binary data into an array of ascii 0 and 1 character bytes - each byte is a truncated
     * char.
     * 
     * @param raw
     *                  the raw binary data to convert
     * @return an array of 0 and 1 character bytes for each bit of the argument
     * @see org.apache.commons.codec.BinaryEncoder#encode(byte[])
     */
    public static byte[] toAsciiBytes(byte[] raw) {
        if (raw == null || raw.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        // get 8 times the bytes with 3 bit shifts to the left of the length
        byte[] l_ascii = new byte[raw.length << 3];
        /*
         * We decr index jj by 8 as we go along to not recompute indices using multiplication every time inside the
         * loop.
         */
        for (int ii = 0, jj = l_ascii.length - 1; ii < raw.length; ii++, jj -= 8) {
            for (int bits = 0; bits < BITS.length; ++bits) {
                if ((raw[ii] & BITS[bits]) == 0) {
                    l_ascii[jj - bits] = '0';
                } else {
                    l_ascii[jj - bits] = '1';
                }
            }
        }
        return l_ascii;
    }
    /**
     * Converts an array of raw binary data into an array of ascii 0 and 1 characters.
     * 
     * @param raw
     *                  the raw binary data to convert
     * @return an array of 0 and 1 characters for each bit of the argument
     * @see org.apache.commons.codec.BinaryEncoder#encode(byte[])
     */
    public static char[] toAsciiChars(byte[] raw) {
        if (raw == null || raw.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        // get 8 times the bytes with 3 bit shifts to the left of the length
        char[] l_ascii = new char[raw.length << 3];
        /*
         * We decr index jj by 8 as we go along to not recompute indices using multiplication every time inside the
         * loop.
         */
        for (int ii = 0, jj = l_ascii.length - 1; ii < raw.length; ii++, jj -= 8) {
            for (int bits = 0; bits < BITS.length; ++bits) {
                if ((raw[ii] & BITS[bits]) == 0) {
                    l_ascii[jj - bits] = '0';
                } else {
                    l_ascii[jj - bits] = '1';
                }
            }
        }
        return l_ascii;
    }

    /**
     * Converts an array of raw binary data into a String of ascii 0 and 1 characters.
     * 
     * @param raw
     *                  the raw binary data to convert
     * @return a String of 0 and 1 characters representing the binary data
     * @see org.apache.commons.codec.BinaryEncoder#encode(byte[])
     */
    public static String toAsciiString(byte[] raw) {
        return new String(toAsciiChars(raw));
    }
}
