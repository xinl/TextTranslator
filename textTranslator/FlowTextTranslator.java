package textTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Flow Text Translator class A Flow Text Translator flows text into paragraph
 * and wrapped to 72 characters each line.
 * 
 * @author Xin Li
 * 
 */
public class FlowTextTranslator implements TranslatorInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Flow Text";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Flows text into paragraph and wrapped to 72 characters each line.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {
		Pattern pEmptyLine = Pattern.compile("^\\s+$\n?", Pattern.MULTILINE);
		Matcher mEmptyLine = pEmptyLine.matcher(text);
		text = mEmptyLine.replaceAll("\n");

		Pattern pJoinLines = Pattern.compile("(.+)\n([^$])");
		Matcher mJoinLines = pJoinLines.matcher(text);
		StringBuffer sb = new StringBuffer();
		while (mJoinLines.find()) {
			mJoinLines.appendReplacement(sb, "$1 $2");
		}
		mJoinLines.appendTail(sb);
		text = sb.toString().replaceAll("(.+)\n([^$])", "$1\n\n$2");
		WrapTextTranslator t = new WrapTextTranslator();

		return t.translate(text).replaceAll(" +\n", "\n");
	}

}
