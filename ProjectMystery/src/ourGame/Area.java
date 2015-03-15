package ourGame;

import java.io.Serializable;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int id; // FOR EASY ACCESS
	private ArrayList<Item> items;
	private ArrayList<Action> actions; // Not object actions, but
													// room

	/**
	 * Create a room object.
	 * 
	 * @param room
	 *            The node which contains the data for the room.
	 * @param loadName
	 *            A string that digs down and prints out to console.
	 */
	public Area(Element area, Game gref) {

		items = new ArrayList<Item>();
		actions = new ArrayList<Action>();

		Element e = (Element) area;
		this.name = e.getAttribute("name");
		this.id = Integer.parseInt(e.getAttribute("id")) % 100;

		//System.out.println("\t" + name);

		NodeList actionList = e.getElementsByTagName("actionR");
		for (int i = 0; i < actionList.getLength(); i++) {
			Node action = actionList.item(i);
			actions.add(new Action(action, true));
		}
		NodeList itemList = e.getElementsByTagName("item");
		for (int i = 0; i < itemList.getLength(); i++) {
			Node item = itemList.item(i);
			items.add(new Item(item));
		}

	}

	public boolean checkForAction(String command) {
		for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i).isCommand(command, true))
				return true;
		}
		return false;
	}

	public void removeObject(int id) {
		for(int i = 0; i < items.size(); i++){
		if(items.get(i).getID() == id)
			items.remove(i);
		}
	}
	
	public void removeObject(String name){
		for (int i = 0; i < items.size(); i++) {
			if (name.contains(items.get(i).getName()))
				items.remove(i);
		}
	}

	public boolean doesObjectExist(String name) {
		for (int i = 0; i < items.size(); i++) {
			if (name.contains(items.get(i).getName()))
				return true;
		}
		return false;
	}

	public boolean testAttributes(String[] attrib, int element, Game gref,
			boolean isObject) throws Exception {
		if (attrib[0].contains("@")) {
			return doesObjectExist(attrib[element]);
		}
		if (isObject) {
			Item item = getItem(attrib[element]);
			return item.testAttributes(attrib, element + 1);
		}
		return false;
	}

	public void applyEffect(String effect, String effectValue, int element,
			Game gref, boolean isObject)
			throws Exception {
		/**
		 * I LOVE YOU MAN :'( THIS IS GONNA HURT
		 */
		String effectValues[] = effectValue.split("\\.");
		int x = effectValues.length;

		/**
		 * Go through all the available effects. Aw shit son, Java 1.7 only,
		 * switches with Strings? BAD MOVE SON LOOKS LIKE THERES NO BACKWARDS
		 * COMPATIBILITY FOR YO ASS
		 */

		switch (effect) {
		case "addAttribute":
			if (isObject)
				getItem(effectValues[x - 2])
						.addAttribute(effectValues[x - 1]);
			break;
		case "changeAttribute":
			if (isObject)
				getItem(effectValues[x - 2]).changeAttribute(
						effectValues[x - 1]);
			break;
		case "removeObject":
			removeObject(Integer.parseInt(effectValues[x - 1]) % 1000);
			break;
		case "moveArea":
			gref.setPlayerArea(Integer.parseInt(effectValues[x - 1]));
			break;
		case "die":
			gref.die();
			break;
		}

	}

	// ONLY GETTERS BEYOND THIS POINT

	/**
	 * Returns the Room Object by the command from the user.
	 * 
	 * @param name
	 *            The command from the user
	 * @return The object associated with that command. OR A null id -1 object
	 *         which then forces another test.
	 */
	public Item getItem(String name) {
		for (int i = 0; i < items.size(); i++) {
			//System.out.println(items.get(i).getName() + " is contained within "+name);
			
			if (name.contains(items.get(i).getName()))
				return items.get(i);
		}
		return new Item(-1);
	}
	
	public Item getObject(int id){
		return items.get(id);
	}

	public boolean isObject(String obj) {
		for (int i = 0; i < items.size(); i++) {
			if (obj.contains(items.get(i).getName()))
				return true;
		}
		return false;
	}

	/**
	 * Returns the Room/Object Action by the command from the user.
	 * 
	 * @param c
	 *            The command from the user
	 * @return The Room/Object associated with that command.
	 */
	public Action getAction(String c, boolean isRoom) {
		for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i).isCommand(c, isRoom))
				return actions.get(i);
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public int howManyItems() {
		return items.size();
	}

}
