import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Game {

	/**
	 * Initialise an instance, then all the game data is loaded into these
	 * classes.
	 */

	String dataXML = "game.xml";
	ArrayList<Area> areas;

	Parser parser;

	private int playerArea;

	public Game() {
		areas = new ArrayList<Area>();
		parser = new Parser();

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File("res\\" + dataXML));

			NodeList areaList = doc.getElementsByTagName("a");
			for (int i = 0; i < areaList.getLength(); i++) {
				Element e = (Element) areaList.item(i);
				areas.add(new Area(e, this));
			}
		} catch (Exception e) {
			System.out.println("go screw yourself"); // PG rating
		}
	}

	public void parser(ArrayList<Area> a, String s) {
		String output;
		try {
			output = parser.logicCheck(this, s, a);
		} catch (Exception e) {
			output = "Now you've done it";
		}
		
		// Pass this back to the user.
	}
	
	public int getPlayerArea() {
		return playerArea;
	}

	public void setPlayerArea(int value) {
		playerArea = value;

	}

}