/*
 * MIT License
 * Copyright (c) 2017 Gymnazium Nad Aleji
 * Copyright (c) 2017 Vojtech Horky
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cz.alisma.alej.text.wrapping;

import java.util.Scanner;

public class WrapAndAlign {
    private static int width = 50;

    public static void main(String[] args) {
    	String[] mainArgs = new String[] {"--left", "--right", "--center", "--centre", "--justify"};
        Scanner input = new Scanner(System.in);
        ParagraphDetector pd = new ParagraphDetector(input);
        int alignerId = 0;
        Aligner aligner = null;
        
        for(int i = 0; i < args.length; i++) {
        	for(int j = 0; j < mainArgs.length; j++) {
        		if(args[i].equalsIgnoreCase(mainArgs[j])) {
        			alignerId = j;
        			break;
        		}
        	}
        	if(alignerId != 0 || args[i].equalsIgnoreCase("--left")) // Zarovnavani bude probihat podle prvniho spravneho argumentu
        	{
        		break;
        	}
        }
        
        // Urceni delky bloku
        for(int i = 0; i < args.length; i++) {
        	try{
    			if(args[i].equalsIgnoreCase("-w")) {
        			width = Integer.parseInt(args[i + 1]);
          		}
        		else if(args[i].toLowerCase().contains("--width=")) {
        			width = Integer.parseInt(args[i].substring(8));
        		}
        	} catch (IllegalArgumentException | IndexOutOfBoundsException e) { 
    		System.out.println("When using custom width, you have to provide desired width in correct format -> either \"-w X\" or \"--width=X\"!");
        		return;
        	}
    	}
        
        // Iniciializace pouzivaneho aligneru
        switch(alignerId) 
        {
        case 0:
        	aligner = new LeftAligner();
        	break;
        case 1:
        	aligner = new RightAligner();
        	break;
        case 2:
        case 3:
        	aligner = new CenterAligner();
        	break;
        case 4:
        	aligner = new JustifyAligner();
        	break;
        }

        while (pd.hasNextParagraph()) {
            Paragraph para = pd.nextParagraph();
            LinePrinter line = new LinePrinter(System.out, width, aligner);
            while (para.hasNextWord()) {
                String word = para.nextWord();
                line.addWord(word);
            }
            line.flush();
            System.out.println();
        }
    }
}
