/**
 * 
 */
package textTranslator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

/**
 * Text Translator GUI class
 * 
 * @author Xin Li
 * 
 */
public class TextTranslator extends JFrame {

	private static final long serialVersionUID = 1L;
	static TextTranslator tt = new TextTranslator();
	
	TranslatorInterface currentTranslator;
	
	JPanel mainPanel = new JPanel();
	JPanel topPanel = new JPanel();
	
	JMenuBar menuBar = new JMenuBar();

	JMenu fileMenu = new JMenu("File");
	JMenu translateMenu = new JMenu("Translate");
	
	JMenuItem loadMenuItem = new JMenuItem("Load...");
	JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
	JMenuItem quitMenuItem = new JMenuItem("Quit");
	
	ButtonGroup translateMenuGroup = new ButtonGroup();
	
	JTextArea descriptionTextArea = new JTextArea("");
	
	JTextArea sourceTextArea = new JTextArea("", 15, 80);
	JTextArea destinationTextArea = new JTextArea("", 15, 80);
	
	JScrollPane sourceScrollPane = new JScrollPane(sourceTextArea);
	JScrollPane destinationScrollPane = new JScrollPane(destinationTextArea);

	JButton translateButton = new JButton("Translate");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		tt.setUpAndShowGUI();
	}

	/**
	 * Set up and display the main window
	 */
	public void setUpAndShowGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		topPanel.setLayout(new BorderLayout());

		add(topPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);

		// Menus
		menuBar.add(fileMenu);
		menuBar.add(translateMenu);

		loadMenuItem.addActionListener(new LoadListener());
		saveAsMenuItem.addActionListener(new SaveAsListener());
		quitMenuItem.addActionListener(new QuitListener());

		fileMenu.add(loadMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(quitMenuItem);

		
		topPanel.add(menuBar, BorderLayout.NORTH);

		// Description
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5,
				10));
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setBackground(Color.getColor("window"));

		topPanel.add(descriptionTextArea, BorderLayout.SOUTH);

		// Text Areas
		Font textAreaFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);

		sourceTextArea.setFont(textAreaFont);
		destinationTextArea.setFont(textAreaFont);
		sourceTextArea.setTabSize(4);
		destinationTextArea.setTabSize(4);

		sourceScrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Text to be translated"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		destinationScrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Translated text"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		mainPanel.add(sourceScrollPane);
		mainPanel.add(destinationScrollPane);

		// Button
		translateButton.addActionListener(new TranslateButtonListener());
		add(translateButton, BorderLayout.SOUTH);

		
		addTranslator(new IdentityTranslator());
		translateMenu.addSeparator();
		addTranslator(new DetabTranslator());
		addTranslator(new EntabTranslator());
		translateMenu.addSeparator();
		addTranslator(new SingleSpaceTranslator());
		addTranslator(new DoubleSpaceTranslator());
		translateMenu.addSeparator();
		addTranslator(new WrapTextTranslator());
		addTranslator(new FlowTextTranslator());
		translateMenu.addSeparator();
		addTranslator(new FixIndentationTranslator());
		
		translateMenu.getItem(0).doClick(); // set the first translator as default
		
		pack();
		setVisible(true);
	}

	/**
	 * Add a new translator to the program and add a new item for it in the Translate menu.
	 * @param translator The translator to add.
	 */
	private void addTranslator(TranslatorInterface translator) {
		JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(translator.getName());
		menuItem.addActionListener(new TranslateListener(translator));
		translateMenuGroup.add(menuItem);
		translateMenu.add(menuItem);
	}

	
	/**
	 * Listener class for Load menu item.
	 *
	 */
	class LoadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Load which file?");
			int result = chooser.showOpenDialog(TextTranslator.tt);
			if (result != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File file = chooser.getSelectedFile();
			BufferedReader reader = null;
			sourceTextArea.setText("");
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					sourceTextArea.append(line + "\n");
					line = reader.readLine();
				}
			} catch (FileNotFoundException err) {
				System.out.println("File not found!");
				err.printStackTrace();
			} catch (IOException err) {
				err.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (NullPointerException err) {
					err.printStackTrace();
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
	}

	/**
	 * Listener for save as menu item.
	 *
	 */
	class SaveAsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save as which file?");
			int result = chooser.showOpenDialog(TextTranslator.tt);
			if (result != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File file = chooser.getSelectedFile();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(file));
				String text = destinationTextArea.getText();
				writer.write(text, 0, text.length());
			} catch (FileNotFoundException err) {
				System.out.println("File not found!");
				err.printStackTrace();
			} catch (IOException err) {
				err.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (NullPointerException err) {
					err.printStackTrace();
				} catch (IOException err) {
					err.printStackTrace();
				}
			}

		}

	}

	/**
	 * Listener class for Quit menu item.
	 *
	 */
	class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(NORMAL);
		}

	}
	
	/**
	 * Listener class for Translate button
	 *
	 */
	class TranslateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			destinationTextArea.setText(currentTranslator.translate(sourceTextArea.getText()));
		}
		
	}

	/**
	 * Listener class for menu items in Translate menu
	 */
	class TranslateListener implements ActionListener {
		private TranslatorInterface translator;
		public TranslateListener(TranslatorInterface translator) {
			this.translator = translator;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			tt.setTitle("Text Translator - " + translator.getName());
			descriptionTextArea.setText(translator.getDescription());
			currentTranslator = translator;
			translateButton.doClick();
			
		}
	}

}
