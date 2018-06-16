package cz.alisma.alej.text.wrapping;

public class FunctionHelper {
	public static String repeat(char c, int i)
	{
		StringBuilder result = new StringBuilder();
		for(int l = 0; l < i; l++)
		{
			result.append(c);
		}
		return result.toString();
	}
}
