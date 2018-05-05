package com.goldhuman.Common.Security;

import com.goldhuman.Common.Octets;

import java.nio.ByteOrder;

/**
 * Created by HuangQiang on 2016/6/8.
 */
public class Decompress implements Cloneable {
    private final static int CtrlOffEob = 0;
    private final static int MppcHistLen = 8192;

    private final byte[] _history = new byte[MppcHistLen];
    private int _histptr;
    private int _l, _adjustL;
    private int _blen, _blenTotol;
    private int _rptr, _adjustRptr;
    private final Octets _legacyIn = new Octets();

    @Override
    public Decompress clone() {
        return new Decompress();
    }

    private boolean Passbits(int n)
    {
        _l += n;
        _blen += n;
        if (_blen < _blenTotol)
            return true;

        _l = _adjustL;
        _rptr = _adjustRptr;
        return false;
    }


    private int toUbyte(byte x) {
        return Byte.toUnsignedInt(x);
    }

    private int toInt32(byte[] bs, int start) {
        int x = toUbyte(bs[start + 3]);
        x = (x << 8) | toUbyte(bs[start + 2]);
        x = (x << 8) | toUbyte(bs[start + 1]);
        x = (x << 8) | toUbyte(bs[start]);
        return x;
    }

    private final static boolean needReverseByte = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;


    private long Fetch()
    {
        _rptr += _l >> 3;
        _l &= 7;
        int x = toInt32(_legacyIn.array(), _rptr);
        if(needReverseByte)
            x = Integer.reverseBytes(x);
        x <<=  _l;
        return Integer.toUnsignedLong(x);
    }


    private void LameCopy(byte[] arry, int dst, int src, int len)
    {
        while (len-- > 0)
            arry[dst++] = arry[src++];
    }

    private static boolean less(int x, int y) {
        return Integer.compareUnsigned(x, y) < 0;
    }

    private static boolean greaterEqual(int x, int y) {
        return Integer.compareUnsigned(x, y) >= 0;
    }

    public Octets doUpdate(Octets oin)
    {
        _legacyIn.insert(_legacyIn.size(), oin);
        _blenTotol = (_legacyIn.size()*8 - _l);
        _legacyIn.reserve(_legacyIn.size() + 3);

        _rptr = 0;
        _blen = 7;
        Octets oout = oin;
        oout.clear();
        int histhead = _histptr;

        while (_blenTotol > _blen)
        {
            _adjustL = _l;
            _adjustRptr = _rptr;
            long val = Fetch();

            if (val < 0x80000000L)
            {
                if (!Passbits(8))
                    break;
                _history[_histptr++] = (byte) (val >> 24);
                continue;
            }
            if (val < 0xc0000000L)
            {
                if (!Passbits(9))
                    break;
                _history[_histptr++] = (byte) (((val >> 23) | 0x80) & 0xff);
                continue;
            }

            int len;
            int off = 0;
            if (val >= 0xf0000000L)
            {
                if (!Passbits(10))
                    break;
                off = (int)((val >> 22) & 0x3f);
                if (off == CtrlOffEob)
                {
                    int advance = 8 - (_l & 7);
                    if (advance < 8)
                        if (!Passbits(advance))
                            break;
                    oout.insert(oout.size(), _history,  histhead, _histptr - histhead);
                    if (_histptr == MppcHistLen)
                        _histptr = 0;
                    histhead = _histptr;
                    continue;
                }
            }
            else if (val >= 0xe0000000L)
            {
                if (!Passbits(12))
                    break;
                off = (int)(((val >> 20) & 0xff) + 64);
            }
            else if (val >= 0xc0000000L)
            {
                if (!Passbits(16))
                    break;
                off = (int)(((val >> 16) & 0x1fff) + 320);
            }


            val = Fetch();
            if (val < 0x80000000L)
            {
                if (!Passbits(1))
                    break;
                len = 3;
            }
            else if (val < 0xc0000000L)
            {
                if (!Passbits(4))
                    break;
                len = (int)(4 | ((val >> 28) & 3));
            }
            else if (val < 0xe0000000L)
            {
                if (!Passbits(6))
                    break;
                len = (int)(8 | ((val >> 26) & 7));
            }
            else if (val < 0xf0000000L)
            {
                if (!Passbits(8))
                    break;
                len = (int)(16 | ((val >> 24) & 15));
            }
            else if (val < 0xf8000000L)
            {
                if (!Passbits(10))
                    break;
                len = (int)(32 | ((val >> 22) & 0x1f));
            }
            else if (val < 0xfc000000L)
            {
                if (!Passbits(12))
                    break;
                len = (int)(64 | ((val >> 20) & 0x3f));
            }
            else if (val < 0xfe000000L)
            {
                if (!Passbits(14))
                    break;
                len = (int)(128 | ((val >> 18) & 0x7f));
            }
            else if (val < 0xff000000L)
            {
                if (!Passbits(16))
                    break;
                len = (int)(256 | ((val >> 16) & 0xff));
            }
            else if (val < 0xff800000L)
            {
                if (!Passbits(18))
                    break;
                len = (int)(0x200 | ((val >> 14) & 0x1ff));
            }
            else if (val < 0xffc00000L)
            {
                if (!Passbits(20))
                    break;
                len = (int)(0x400 | ((val >> 12) & 0x3ff));
            }
            else if (val < 0xffe00000L)
            {
                if (!Passbits(22))
                    break;
                len = (int)(0x800 | ((val >> 10) & 0x7ff));
            }
            else if (val < 0xfff00000L)
            {
                if (!Passbits(24))
                    break;
                len = (int)(0x1000 | ((val >> 8) & 0xfff));
            }
            else
            {
                _l = _adjustL;
                _rptr = _adjustRptr;
                break;
            }

            if (_histptr < off || _histptr + len > MppcHistLen)
                break;
            // Array.Copy(history, histptr - off, history, histptr, histptr - histhead);
            LameCopy(_history, (int) _histptr, (int) (_histptr - off), (int) len);
            // Array.Copy(history, histptr - off, history, histptr, len);
            _histptr += len;
        }

        oout.insert(oout.size(), _history, histhead, _histptr - histhead);
        _legacyIn.erase(0, _rptr);

        return oout;
    }

    public static void main(String argv[]) {
        int x = 1;
        System.out.println(less(x, 0xfff00000));
        System.out.println(less(0xfff00000,x ));
        System.out.println(greaterEqual(x, 0xfff00000));
        System.out.println(greaterEqual(0xfff00000,x ));
        x = 0xfff00000;
        System.out.println(greaterEqual(0xfff00000,x));
    }
}
