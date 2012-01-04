package textTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Single Space Translator class A Single Space Translator replaces consecutive
 * pairs of newlines with a single newline.
 * 
 * @author Xin Li
 * 
 */
public class SingleSpaceTranslator implements TranslatorInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Single Space";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Replaces consecutive pairs of newlines with a single newline.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile("\n\n");
		Matcher m = p.matcher(text);
		while (m.find()) {
			m.appendReplacement(sb, "\n");
		}
		m.appendTail(sb);

		return sb.toString();
	}

}
