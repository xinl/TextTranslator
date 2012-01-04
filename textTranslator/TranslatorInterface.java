/**
 * 
 */
package textTranslator;

/**
 * The interface for all translators. A translator is an object that can modify
 * an input string base one a set of rules, as well as return the name and
 * description of such function.
 * 
 * @author Xin Li
 * 
 */
public interface TranslatorInterface {
	/**
	 * Returns the name string of this translator to be used as a menu item and
	 * as the title of the GUI window.
	 * 
	 * @return The name string of the translator
	 */
	String getName();

	/**
	 * Returns a descriptive string of what this translator does.
	 * 
	 * @return The description string.
	 */
	String getDescription();

	/**
	 * Perform the translation on the input string and return the translated text.
	 * @param text
	 *            The text to be translated.
	 * @return The translated text.
	 */
	String translate(String text);

}
