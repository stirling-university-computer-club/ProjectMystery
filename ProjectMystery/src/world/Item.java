package world;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class Item {
	
	private ArrayList<String> attributes;
	private String name;
	
	public Item(Element e){
		attributes = new ArrayList<String>();
		name = e.getAttribute("name");
		
		String[] attr = e.getElementsByTagName("attributes").item(0).getTextContent().split("\\,");
		for(int i = 0; i < attr.length; i++){
			attributes.add(attr[i]);
		}
	}
	
	public void addAttribute(String s){
		attributes.add(s);
	}
	
	public void changeAttribute(String x, String y){
		for(int i = 0; i < attributes.size(); i++){
			if(attributes.get(i).equals(x))
				attributes.set(i, y);
		}
	}
	
	public void removeAttribute(String s){
		for(int i = 0; i < attributes.size(); i++){
			if(attributes.get(i).equals(s))
				attributes.remove(i);
		}
		
	}
	
	
}
