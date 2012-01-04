package textTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wrap Text Translator class A Wrap Text Translator wraps lines so that no line
 * is longer than 72 characters. But do not break long words.
 * 
 * @author Xin Li
 * 
 */
public class WrapTextTranslator implements TranslatorInterface {

	/* (non-Javadoc)
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Wrap Text";
	}

	/* (non-Javadoc)
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Wraps lines so that no line is longer than 72 characters. But do not break long words.";
	}

	/* (non-Javadoc)
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {

		text += "A"; // appended 'A' at the end to avoid dealing with trailing
						// newline problem introduced by split().

		String lines[] = text.split("\n");
		String result = "";
		Pattern pIndent = Pattern.compile("^[\t ]*");
		for (int i = 0; i < lines.length; i++) {

			lines[i] += "\n"; // add '\n' back because we lost them during
								// split()

			if (lines[i].length() <= 72) {
				result += lines[i];
				continue; // short circuit when < 72 char per line
			}
			Matcher mIndent = pIndent.matcher(lines[i]);
			int indentLength = 0;
			String indentStr = "";
			if (mIndent.find()) {
				for (int j = 0; j < mIndent.group().length(); j++) {
					if (mIndent.group().charAt(j) == '\t') {
						indentLength += 4;
					} else if (mIndent.group().charAt(j) == ' ') {
						indentLength++;
					}
				}
				indentStr = mIndent.group();
				lines[i] = mIndent.replaceFirst("");
			}

			Pattern p = Pattern.compile("((.{1," + (72 - indentLength)
					+ "})|(\\S{" + (72 - indentLength + 1) + ",}))( +|$)\n?"); // without
																				// "\n?",
																				// it
																				// will
																				// add
																				// extra
																				// newline
																				// for
																				// "word \n"
			Matcher m = p.matcher(lines[i]);
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
				m.appendReplacement(sb, indentStr + "$1\n");
			}

			m.appendTail(sb);

			result += sb.toString();
		}

		return result.substring(0, result.length() - 2); // because we appended
															// 'A' at the end.

	}

}
