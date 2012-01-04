package textTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Detab Translator class A Detab Translator replaces tabs at the beginning of
 * each line with 4 spaces.
 * 
 * @author Xin Li
 * 
 */
public class DetabTranslator implements TranslatorInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Detab";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Replaces tabs at the beginning of each line with 4 spaces.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile("^\t+", Pattern.MULTILINE);
		Matcher m = p.matcher(text);
		while (m.find()) {
			int tabCount = m.group().length();
			String replacement = "";
			for (int j = 0; j < tabCount; j++) {
				replacement += "    ";
			}
			m.appendReplacement(sb, replacement);
		}
		m.appendTail(sb);

		return sb.toString();
	}

}
