package com.twilio;

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
	private boolean dead = false;

	private int playerArea = 0;

	public Game() {
		areas = new ArrayList<Area>();
		parser = new Parser();

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File("res\\" + dataXML));

			NodeList areaList = doc.getElementsByTagName("area");
			for (int i = 0; i < areaList.getLength(); i++) {
				Element e = (Element) areaList.item(i);
				areas.add(new Area(e, this));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("go screw yourself"); // PG rating
		}
	}

	public ArrayList<Area> getAreas() {
		return areas;
	}

	public String parser(String s) {

		String output =  "Yer dead ya dank memes";
		if (!dead) {
			output = parser.logicCheck(this, s, areas);
			// Pass this back to the user.
		}
		return output;
	}

	public int getPlayerArea() {
		return playerArea;
	}

	public void die() {
		dead = true;
	}

	public void setPlayerArea(int value) {
		playerArea = value;

	}

}