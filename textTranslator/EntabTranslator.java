package textTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Entab Translator class An Entab Translator replaces each group of four spaces
 * at the beginning of each line with a tab character.
 * 
 * @author Xin Li
 * 
 */
public class EntabTranslator implements TranslatorInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Entab";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Replaces each group of four spaces at the beginning of each line with a tab character.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile("^(    )+", Pattern.MULTILINE);
		Matcher m = p.matcher(text);
		while (m.find()) {
			int tabCount = m.group().length() / 4;
			String replacement = "";
			for (int j = 0; j < tabCount; j++) {
				replacement += "\t";
			}
			m.appendReplacement(sb, replacement);
		}
		m.appendTail(sb);

		return sb.toString();
	}

}
