import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import world.Area;

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
				areas.add(new Area(e));
			}
		} catch (Exception e) {
			System.out.println("go screw yourself"); // PG rating
		}
	}

	public void parser(ArrayList<Area> a, String s){
		// Text this back to player
		String output = parser.logicCheck(this, a, s);
	}
	
	public int getPlayerArea(){
		return playerArea;
	}
	
}