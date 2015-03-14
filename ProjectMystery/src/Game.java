import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class Game {

	/**
	 * Initialise an instance, then all the game data is loaded into these classes.
	 */
	
	String dataXML = "game.xml";
	
	
	
	public Game() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(new File("res\\roomdata.xml"));
	}
	
	
}
