package textTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fix Indentation Translator class A Fix Indentation Translator indent a Java
 * (or Java-like) program according to the curly braces.
 * 
 * @author Xin Li
 * 
 */
public class FixIndentationTranslator implements TranslatorInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Fix Indentation";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Given a Java (or Java-like) program, indent it according to the curly braces.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {
		text = text.replaceAll("\t", "    ");

		Pattern pIndent = Pattern.compile("^ *");
		Matcher mIndent = pIndent.matcher(text);

		int baseIndent = 0;
		if (mIndent.find()) {
			baseIndent = mIndent.group().length();
		}
		int indentCount = baseIndent;

		Pattern pClearIndent = Pattern.compile("^ *", Pattern.MULTILINE);
		Matcher mClearIndent = pClearIndent.matcher(text);
		text = mClearIndent.replaceAll("");

		String lines[] = text.split("\n");
		String result = "";

		Pattern pBraces = Pattern.compile("[\\{\\}]");
		for (int i = 0; i < lines.length; i++) {
			Matcher mBraces = pBraces.matcher(lines[i]);
			String braces = "";
			while (mBraces.find()) {
				braces += mBraces.group();
			}

			int thisLineDelta = 0;
			int nextLineDelta = 0;
			int temp = 0;

			for (int j = 0; j < braces.length(); j++) {
				if (j == 0 && braces.charAt(0) == '}') {
					thisLineDelta = -4;
				} else {
					if (braces.charAt(j) == '{') {
						temp += 4;
					} else {
						temp -= 4;
					}
				}
			}
			if (temp > 0) {
				nextLineDelta += temp;
			} else {
				thisLineDelta += temp;
			}

			indentCount += thisLineDelta;

			String indent = "";
			for (int j = 0; j < indentCount; j++) {
				indent += " ";
			}
			result += indent + lines[i] + "\n";

			indentCount += nextLineDelta;
		}

		if (text.endsWith("\n")) {
			return result;
		} else {
			return result.substring(0, result.length() - 1);
		}
	}

}
