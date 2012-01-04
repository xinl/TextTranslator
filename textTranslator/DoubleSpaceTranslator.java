package textTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Double Space Translator class A Double Space Translator replaces each newline
 * in the input with two newlines.
 * 
 * @author Xin Li
 * 
 */
public class DoubleSpaceTranslator implements TranslatorInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Double Space";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Replaces each newline in the input with two newlines.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile("\n");
		Matcher m = p.matcher(text);
		while (m.find()) {
			m.appendReplacement(sb, "\n\n");
		}
		m.appendTail(sb);

		return sb.toString();
	}

}
