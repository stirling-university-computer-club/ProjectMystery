package ourGame;


import java.io.Serializable;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String command[];
	private ArrayList<Case> aCases;

	/**
	 * THIS IS ONLY IF THERE ARE NO CASES
	 */
	private String output;

	public Action(Node action, boolean isRoom) {

		Element e = (Element) action;
		this.command = e.getAttribute("command").split(",");
		// if (isRoom)
		// System.out.println("\t\t" + command[0]);
		// else
		// System.out.println("\t\t\t" + command[0]);

		/**
		 * Action cases may not exist if there are no effects or attributes
		 * involved.
		 */
		NodeList aCaseList = e.getElementsByTagName("case");
		aCases = new ArrayList<Case>();
		if (aCaseList.getLength() > 0) {
			for (int i = 0; i < aCaseList.getLength(); i++) {
				Node aCase = aCaseList.item(i);
				aCases.add(new Case(aCase));
			}
		} else {
			this.output = grabOutput(e);
		}
	}

	// public String getCommand() {
	// return command[0];
	// }

	public Action(String commands, Case aCase) {
		command = commands.split("\\,");
		
		aCases = new ArrayList<Case>();
		aCases.add(aCase);

	}
	
	public Action(String commands, String output){
		command = commands.split("\\,");
		
		aCases = new ArrayList<Case>();
		this.output = output;
	}
	
	public String getCommand() {
		String list = "";
		for(int i = 0; i < command.length; i++){
			list += command[i] + "/";
		}
		return list;
	}

	public ArrayList<Case> getCases() {
		return aCases;
	}

	public String getOutput() {
		return output;
	}

	public String grabOutput(Element e) {
		NodeList outputList = e.getElementsByTagName("output");
		String outputStr = outputList.item(0).getTextContent();
		if (outputList.getLength() > 0) {
			for (int i = 1; i < outputList.getLength(); i++) {
				outputStr += "\n" + outputList.item(i).getTextContent();
			}
		}
		return outputStr;
	}

	public boolean isCommand(String c, boolean isRoom) {
		for (int i = 0; i < command.length; i++) {
			if (!isRoom) {
				if (c.contains(command[i]))
					return true;
			} else {
				if (c.equals(command[i])) {
					return true;
				}
			}
		}
		return false;
	}
	

}
