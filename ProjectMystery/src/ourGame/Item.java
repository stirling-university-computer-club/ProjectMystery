package ourGame;


import java.io.Serializable;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Action> actions;
	private ArrayList<String> attributes;
	private int id;

	public Item(Node object) {

		actions = new ArrayList<Action>();
		attributes = new ArrayList<String>();
		Element e = (Element) object;
		this.name = e.getAttribute("name");
		this.id = Integer.parseInt(e.getAttribute("id")) % 1000;
		// System.out.println("\t\t"+name);

		NodeList attribList = e.getElementsByTagName("attributes");
		if (attribList.getLength() > 0)
			spliceInitialData(attribList.item(0).getTextContent());

		NodeList actionList = e.getElementsByTagName("action");
		for (int i = 0; i < actionList.getLength(); i++) {
			Node action = actionList.item(i);
			actions.add(new Action(action, false));
		}
	}

	// NULL CONSTRUCTOR FOR IF INVALID INPUT IS GIVEN
	public Item(int id) {
		this.id = id;
	}

	// THIS IS FOR SPAWNING LOOT DROPS
	/*
	 * TODO: THIS SHIT
	 */

	public Action getAction(String command, boolean isRoom) {
		for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i).isCommand(command, isRoom))
				return actions.get(i);
		}
		return null;
	}

	public boolean testAttributes(String[] attrib, int length) {
		String[] testAttrib = attrib[length].split(" ");

		// System.out.println(testAttrib[0]+attributes.get(0));

		for (int i = 0; i < testAttrib.length; i++) {
			boolean found = false;
			for (int j = 0; j < attributes.size(); j++) {
				if (testAttrib[i].equals(attributes.get(j)))
					found = true;
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	public void spliceInitialData(String toSplice) {
		String[] data = toSplice.split(",");
		for (int i = 0; i < data.length; i++) {
			addAttribute(data[i]);
		}
	}

	public boolean checkForAttributes(String attrib) {
		boolean contained = true;
		for (int i = 0; i < attributes.size(); i++) {
			if (!attrib.contains(attributes.get(i)))
				contained = false;
		}
		return contained;
	}

	public void addAttribute(String attribute) {
		boolean canEnter = true;
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i).equals(attribute))
				canEnter = false;
		}
		if (canEnter)
			attributes.add(attribute);
	}

	public void changeAttribute(String attribute) {
		boolean canEnter = false;
		String[] attributeChange = attribute.split(" ");
		int element = 0;
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i).equals(attributeChange[0])) {
				canEnter = true;
				element = i;
			}
		}
		if (canEnter)
			attributes.set(element, attributeChange[1]);
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}


}
