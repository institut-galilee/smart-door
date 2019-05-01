// Idea from Simple Server (Java server for Habbo Hotel v9)
package net.nova.crypto;

public abstract class VL64 {
	public static String encode(long i)	{
		byte[] wf = new byte[6];
        int pos = 0;
        int startPos = pos;
        int bytes = 1;
        int negativeMask = i >= 0 ? 0 : 4;
        i = Math.abs(i);
        wf[pos++] = (byte)(64 + (i & 3));
        for (i >>= 2; i != 0; i >>= 6)
        {
            bytes++;
            wf[pos++] = (byte)(64 + (i & 0x3f));
        }

        wf[startPos] = (byte)(wf[startPos] | bytes << 3 | negativeMask);

        String tmp = ASCII.decode(wf);
        return tmp.replace("\0", "");
	}
	
	public static int decode(String data) {
		return decode(data.toCharArray());
	}
	
	public static int decode(char[] raw) {
		int pos = 0;
        int v = 0;
        boolean negative = (raw[pos] & 4) == 4;
        int totalBytes = raw[pos] >> 3 & 7;
        v = raw[pos] & 3;
        pos++;
        int shiftAmount = 2;
        for (int b = 1; b < totalBytes; b++)
        {
            v |= (raw[pos] & 0x3f) << shiftAmount;
            shiftAmount = 2 + 6 * b;
            pos++;
        }

        if (negative == true)
            v *= -1;
        return v;
	}
	
	public static int length(String data) {
		return length(data.toCharArray());
	}
	
	public static int length(char[] raw) {
		int pos = 0;
        int v = 0;
        boolean negative = (raw[pos] & 4) == 4;
        int totalBytes = raw[pos] >> 3 & 7;
        v = raw[pos] & 3;
        pos++;
        int shiftAmount = 2;
        for (int b = 1; b < totalBytes; b++)
        {
            v |= (raw[pos] & 0x3f) << shiftAmount;
            shiftAmount = 2 + 6 * b;
            pos++;
        }

        if (negative == true)
            v *= -1;

        String Tmp;
        Tmp = encode(v);
        return Tmp.length();
	}
}
