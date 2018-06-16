package cz.alisma.alej.text.wrapping;

import java.util.List;

public class JustifyAligner implements Aligner {
	
	@Override
	public String format(List<String> words, int width, int lengthSoFar) {
		StringBuilder result = new StringBuilder();
		int numberOfSpaces = words.size() - 1;
		int spaceTotalLength = width - lengthSoFar + words.size();
		int spaceLeftouts = 0;
		int basicSpaceSize = 0;
		if(numberOfSpaces != 0) {
			spaceLeftouts = spaceTotalLength % numberOfSpaces;
			basicSpaceSize = (spaceTotalLength - spaceLeftouts) / numberOfSpaces;
			
			boolean first = true;
	        for (String w : words) {
	            if (!first) {
	                result.append(FunctionHelper.repeat(' ', basicSpaceSize));
	                if(spaceLeftouts > 0)
	                {
	                	result.append(' ');
	                	spaceLeftouts--;
	                }

	            } else {
	                first = false;
	            }
	            result.append(w);
	        }
		}
		else
		{
			result.append(words.get(0));
		}        
        return result.toString();
	}
}
