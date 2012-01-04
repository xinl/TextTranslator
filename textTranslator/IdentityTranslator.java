package textTranslator;

/**
 * Identity Translator class An Identity Translator makes no changes to the
 * input.
 * 
 * @author Xin Li
 * 
 */
public class IdentityTranslator implements TranslatorInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getName()
	 */
	@Override
	public String getName() {
		return "Identity";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Makes no changes to the input.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see textTranslator.TranslatorInterface#translate(java.lang.String)
	 */
	@Override
	public String translate(String text) {
		return text;
	}

}
