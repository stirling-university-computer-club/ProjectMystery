

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Case implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[][] attributes; // OBJECT, ATTRIBUTES
	private String[] effect; // EFFECT
	private String[] effectValue; // VALUE
	private String output;

	public Case(Node aCase) {

		Element e = (Element) aCase;
		String attributeList = e.getAttribute("attributes");
		this.attributes = grabAttributes(attributeList);
		this.effect = grabEffects(e.getAttribute("effect"));
		if (!(effect[0].equals("")))
			this.effectValue = grabEffectValues(this.effect, e);

		/**
		 * Output consists of 1 or more output elements.
		 */
		NodeList outputList = e.getElementsByTagName("output");
		output = outputList.item(0).getTextContent();
		if (outputList.getLength() > 0) {
			for (int i = 1; i < outputList.getLength(); i++) {
				output += "\n" + outputList.item(i).getTextContent();
			}
		}

	}

	public Case(String effect, String effectValue, String output) {

		this.attributes = grabAttributes("");
		if (!(effect.equals("")))
			this.effect = effect.split("\\,");
		if (!(effectValue.equals("")))
			this.effectValue = effectValue.split("\\,");
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public String[] getEffects() {
		return effect;
	}

	public String[] getEffectValues() {
		return effectValue;
	}

	public String[][] getAttributes() {
		return attributes;
	}

	public String[] grabEffects(String effect) {
		String[] effects = effect.split("\\,");
		return effects;
	}

	public String[] grabEffectValues(String[] effect, Element e) {
		String[] effectValues = new String[effect.length];
		for (int i = 0; i < effect.length; i++) {
			effectValues[i] = e.getElementsByTagName(effect[i]).item(0)
					.getTextContent();
		}
		return effectValues;
	}

	/**
	 * GRAB ATTRIBUTES: Take an attribute list like so: <>a.x y z,b.d f g Split
	 * into separate objects: [0]a.x y z [1]b.d f g Split into object name and
	 * attribute list: [0]a [1]x y z
	 * 
	 * IF LENGTH OF DATA IS 4: AREA.ROOM.OBJECT.ATTRIBUTELIST IF LENGTH OF DATA
	 * IS 3: ROOM.OBJECT.ATTRIBUTELIST IF LENGTH OF DATA IS 2:
	 * OBJECT.ATTRIBUTELIST IF LENGTH OF DATA IS 1: ATTRIBUTELIST (SELF OBJECT
	 * ATTRIB)
	 * 
	 * @param attributeList
	 * @return
	 */
	public String[][] grabAttributes(String attributeList) {
		String[][] objAttributes = new String[10][4];

		// NULLIFY EVERYTHING
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				objAttributes[i][j] = "x";
			}
		}

		String[] objects = attributeList.split("\\,");

		for (int i = 0; i < objects.length; i++) {
			String[] data = objects[i].split("\\.");

			for (int j = 0; j < data.length; j++) {
				objAttributes[i][j] = data[j]; //

			}
		}

		return objAttributes;
	}

	public boolean doesItWork(ArrayList<Area> areas, Area area,
			Game gref) throws Exception {
		String[][] attrib = getAttributes();
		boolean works = true;

		if (!(attrib[0][0].equals(""))) { // If the first attribute isn't null

			for (int j = 0; j < attrib.length; j++) {
				int length = 0;
				for (int k = 0; k < attrib[j].length; k++) {
					if (attrib[j][k].equals("x")) {
						length = k;
						break;
					}
				}

				switch (length) { // Test if
				case 1:
					works = area.testAttributes(attrib[j], 0, gref,
							area.isObject(attrib[j][0])); // @object,data
					break;
				case 2:
					works = area.testAttributes(attrib[j], 0, gref,
							area.isObject(attrib[j][0])); // object.data
					break;
				case 3:
					works = area.testAttributes(attrib[j], 1, gref,
							area.isObject(attrib[j][0])); // room.object.data
					break;
				default:
					break;
				}
				if (!works)
					break;
			}
			return works;
		} else
			return true;
	}

	public void applyEffect(ArrayList<Area> areas, Area area, int m,
			String effectValue, Game gref) throws Exception {
		String[] data = effectValue.split("\\.");
		switch (data.length) {
		case 1:
			area.applyEffect(effect[m], effectValue, m, gref,
					area.isObject(data[0]));
			break;
		case 2:
			area.applyEffect(effect[m], effectValue, m, gref,
					area.isObject(data[0]));
			break;
		case 3:
			area.applyEffect(effect[m], effectValue, m, gref,
					area.isObject(data[1]));
			break;
		case 4:
			areaByName(areas, effectValue).applyEffect(effect[m], effectValue,
					m, gref, area.isObject(data[2]));
			break;
		default:
			break;

		}
	}


	public Area areaByName(ArrayList<Area> areas, String name) {
		for (int i = 0; i < areas.size(); i++) {
			if (areas.get(i).getName().equals(name))
				return areas.get(i);
		}
		return null;
	}

	/**
	 * THESE THREE METHODS ARE FOR THE LISTOBJ COMMAND
	 * 
	 * @return
	 */
	public String printAttributes() {
		String list = "";
		for (int i = 0; i < attributes.length; i++) {
			list += attributes[i][0] + " + ";
		}
		return list.substring(0, list.length() - 2);
	}

	public String printEffects() {
		String list = "";
		for (int i = 0; i < effect.length; i++) {
			list += effect[i] + " + ";
		}
		return list.substring(0, list.length() - 2);
	}

	public String printValues() {
		String list = "";
		if (!(effect[0].equals("") || effect[0].equals("takeLoot"))) {
			for (int i = 0; i < effectValue.length; i++) {
				list += effectValue[i] + " + ";
			}
			return list.substring(0, list.length() - 2);
		}
		return list;
	}
}
