package xio.security;

import com.goldhuman.Common.Octets;
/**
 * getFrom Android IO
 * @author PCaijiacheng
 *
 */
public class StreamMPPCDecopress extends Security {
	private static final int CTRL_OFF_EOB = 0;
	private static final int MPPC_HIST_LEN = 8192;
	
	private byte[] histroy = new byte[MPPC_HIST_LEN];
	private int histp;
	private int l;
	private int adjustL;
	private int blen;
	private int blenTotal;
	private int rptr;
	private int adjustRptr;
	private Octets legacyIn = new Octets();
	
	
	static long getUnsingedInt(Octets o, int pos, boolean swaped)
	{
		long b0 = o.getByte(pos) & 0xff ;
		long b1 =o.getByte(pos + 1) & 0xff ;
		long b2 = o.getByte(pos + 2) & 0xff ;
		long b3 =o.getByte(pos +3) & 0xff ;
		if (swaped) {
			return (b0 << 24 | b1 << 16 | b2 << 8 | b3 << 0) & 0xffffffffL; 
		} else {
			return (b3 << 24 | b2 << 16 | b1 << 8 | b0 << 0) & 0xffffffffL; 
		}
	}
	
	
	private boolean passbits(int n) {
		l += n;
		blen += n;
		if (blen < blenTotal)
			return true;
		l = adjustL;
		rptr = adjustRptr;
		return false;
	}
	
	private long fetch() {
		rptr += l >>> 3;
		l &= 7;
		return (getUnsingedInt(legacyIn, rptr, true) << l)&0xffffffffL;
	}
	
	public Octets Update(Octets o) {
		legacyIn.insert(legacyIn.size(), o);
		blenTotal = legacyIn.size() * 8 - l;
		legacyIn.reserve(legacyIn.size() + 3);
		
		rptr = 0;
		blen = 7;
		Octets out = o;
		o.clear();
		
		int histh = histp;
		while (blenTotal > blen) {
			adjustL = l;
			adjustRptr = rptr;
			
			long val = fetch();
			if (val < 0x80000000L) {
				if (!passbits(8)) break;
				histroy[histp++] = (byte)(val>>>24);
				continue;
			}
			if (val < 0xc0000000L) {
				if (!passbits(9)) break;
				histroy[histp++] = (byte)(((val>>>23)|0x80L)&0xffL);
				continue;
			}
			
			int len = 0;
			int off = 0;
			if (val >= 0xf0000000L) {
				if (!passbits(10)) break;
				off = (int)((val>>>22)&0x3fL);
				if (off == CTRL_OFF_EOB) {
					int advance = 8 - (l&7);
					if (advance < 8)
						if (!passbits(advance))
							break;
					if (histp!= histh)
						out.insert(out.size(), histroy, histh, histp - histh);
					if (histp == MPPC_HIST_LEN)
						histp = 0;
					histh = histp;
					continue;
				}
			}
			else if (val >= 0xe0000000L) {
				if (!passbits(12)) break;
				off = (int)(((val>>>20)&0xffL) + 64);
			}
			else if (val >= 0xc0000000L) {
				if (!passbits(16)) break;
				off = (int)(((val>>>16)&0x1fffL) + 320);
			}
			
			val = fetch();
			if (val < 0x80000000L) {
				if (!passbits(1)) break;
				len = 3;
			}
			else if (val < 0xc0000000L) {
				if (!passbits(4)) break;
				len = (int)(4|((val>>>28)&3L));
			}
			else if (val < 0xe0000000L) {
				if (!passbits(6)) break;
				len = (int)(8|((val>>>26)&7L));
			}
			else if (val < 0xf0000000L) {
				if (!passbits(8)) break;
				len = (int)(16|((val>>>24)&15L));
			}
			else if (val < 0xf8000000L) {
				if (!passbits(10)) break;
				len = (int)(32|((val>>>22)&0x1fL));
			}
			else if (val < 0xfc000000L) {
				if (!passbits(12)) break;
				len = (int)(64|((val>>>20)&0x3fL));
			}
			else if (val < 0xfe000000L) {
				if (!passbits(14)) break;
				len = (int)(128|((val>>>18)&0x7fL));
			}
			else if (val < 0xff000000L) {
				if (!passbits(16)) break;
				len = (int)(256|((val>>>16)&0xffL));
			}
			else if (val < 0xff800000L) {
				if (!passbits(18)) break;
				len = (int)(0x200|((val>>>14)&0x1ffL));
			}
			else if (val < 0xffc00000L) {
				if (!passbits(20)) break;
				len = (int)(0x400|((val>>>12)&0x3ffL));
			}
			else if (val < 0xffe00000L) {
				if (!passbits(22)) break;
				len = (int)(0x800|((val>>>10)&0x7ffL));
			}
			else if (val < 0xfff00000L) {
				if (!passbits(24)) break;
				len = (int)(0x1000|((val>>>8)&0xfffL));
			}
			else {
				l = adjustL;
				rptr = adjustRptr;
				break;
			}
			if (histp < off || histp + len > MPPC_HIST_LEN)
				break;
			
			//System.arraycopy(histroy, histp, histroy, histp - off, len);
			arraycopy(histroy, histp, histroy, histp - off, len);
			histp += len;
		}
		if (histp != histh)
			out.insert(out.size(), histroy, histh, histp - histh);
		legacyIn.erase(0, rptr);
		
		return out;
	}
	
	private void arraycopy(byte[] dst, int dp, byte[] src, int sp, int len) {
		for (int i = len; i > 0; i--)
			dst[dp++] = src[sp++];
	}
}
