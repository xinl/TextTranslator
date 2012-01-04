package textTranslator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextTranslatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIdentityTranslate() {
		String src = "According tot he researchers, \nthe newly discovered defense system could lead to new therapies for diseases.\n";
		String dest = "According tot he researchers, \nthe newly discovered defense system could lead to new therapies for diseases.\n";
		IdentityTranslator t = new IdentityTranslator();
		assertEquals(dest, t.translate(src));
	}

	@Test
	public void testDetabTranslate() {
		String src = "\t\t\tHahaha\t\n\t\tHohoho\nMuhaha\n\t 3376\n";
		String dest = "            Hahaha\t\n        Hohoho\nMuhaha\n     3376\n";
		DetabTranslator t = new DetabTranslator();
		assertEquals(dest, t.translate(src));
	}

	@Test
	public void testEntabTranslate() {
		String src = "            Hahaha\t\n        Hohoho\nMuhaha\n     3376\n";
		String dest = "\t\t\tHahaha\t\n\t\tHohoho\nMuhaha\n\t 3376\n";
		EntabTranslator t = new EntabTranslator();
		assertEquals(dest, t.translate(src));
	}

	@Test
	public void testSingleSpaceTranslate() {
		String src = "\nHahaha\t\n\nHohoho\n \nMuhaha\n\n\n3376";
		String dest = "\nHahaha\t\nHohoho\n \nMuhaha\n\n3376";
		SingleSpaceTranslator t = new SingleSpaceTranslator();
		assertEquals(dest, t.translate(src));
	}

	@Test
	public void testDoubleSpaceTranslate() {
		String src = "\nHahaha\t\nHohoho\n \nMuhaha\n3376";
		String dest = "\n\nHahaha\t\n\nHohoho\n\n \n\nMuhaha\n\n3376";
		DoubleSpaceTranslator t = new DoubleSpaceTranslator();
		assertEquals(dest, t.translate(src));
	}

	@Test
	public void testWrapTestTranslate() {
		String src = "Ha\n\n\t  If the modification time of the file is more than 6 months in the past or future, then the year of the last modification is displayed in place of the hour and minute fields Ifthemodificationtimeofthefileismorethan6monthsintheaadfsfdsafdsafdsafdsaf Oranges.\nHo.\n \nNook\n\n";
		String dest = "Ha\n\n\t  If the modification time of the file is more than 6 months in the\n\t  past or future, then the year of the last modification is\n\t  displayed in place of the hour and minute fields\n\t  Ifthemodificationtimeofthefileismorethan6monthsintheaadfsfdsafdsafdsafdsaf\n\t  Oranges.\nHo.\n \nNook\n\n";
		WrapTextTranslator t = new WrapTextTranslator();
		assertEquals(dest, t.translate(src));
	}

	@Test
	public void testFlowTextTranslate() {
		String src = "If the modification time\n\n\nof the file is more than 6 months in the past or future, then the year of the\n                	\n\nlast modification is displayed in place\n\nof the hour and minute fields\nIfthemodificationtimeofthefileismorethan6monthsintheaadfsfdsafdsafdsafdsaf\nOranges.\n";
		String dest = "If the modification time\n\nof the file is more than 6 months in the past or future, then the year\nof the\n\nlast modification is displayed in place\n\nof the hour and minute fields\nIfthemodificationtimeofthefileismorethan6monthsintheaadfsfdsafdsafdsafdsaf\nOranges.\n";
		FlowTextTranslator t = new FlowTextTranslator();
		assertEquals(dest, t.translate(src));
	}

	@Test
	public void testFixIndentationTranslate() {
		String src = "\tpublic void testDoubleSpaceTranslate() {\n	\tString src = \"!!!\";\n	String dest = \"???\";\n	if ( 1 < 2) {\n	//Do nothing\n} {{}}{}else {\npanic!\n}\n	}\n";
		String dest = "    public void testDoubleSpaceTranslate() {\n        String src = \"!!!\";\n        String dest = \"???\";\n        if ( 1 < 2) {\n            //Do nothing\n        } {{}}{}else {\n            panic!\n        }\n    }\n";
		FixIndentationTranslator t = new FixIndentationTranslator();
		assertEquals(dest, t.translate(src));
	}

}
