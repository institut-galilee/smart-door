// Idea from Simple Server (Java server for Habbo Hotel v9)
package net.nova.crypto;

public abstract class B64 {
	public static String encode(String s)
	{
		int value = s.length();
        int length = 2;
        StringBuilder stack = new StringBuilder(length);
        for (int x = 1; x <= length; x++)
        {
            int offset = 6 * (length - x);
            byte val = (byte)(64 + (value >> offset & 0x3f));
            stack.append((char)val);
        }
        return stack.toString();
	}
	
	public static String encode(int value, int length)
    {
        StringBuilder stack = new StringBuilder(length);
        for (int x = 1; x <= length; x++)
        {
            int offset = 6 * (length - x);
            byte val = (byte)(64 + (value >> offset & 0x3f));
            stack.append((char)val);
        }
        return stack.toString();
    }
	
	public static int decode(String s)
	{
		if (s.length() != 2)
			return 0;
		int n1, n2;
		n1 = ((int)s.charAt(0) - 64) * 64;
		n2 = (int)s.charAt(1) - 64;
		return n1 + n2;
	}
}
