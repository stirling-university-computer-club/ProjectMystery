package world;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Area {

	int id;
	ArrayList<Item> items;
	
	String lookOutput;
	
	public Area(Element e){
		id = Integer.parseInt(e.getAttribute("id"));
		
		NodeList looks = e.getElementsByTagName("look");
		for(int i = 0; i < looks.getLength(); i++){
			if(i == 0)
				lookOutput += looks.item(0).getTextContent();
			else
				lookOutput += looks.item(i).getTextContent()+"\n";
		}
		
		NodeList itemList = e.getElementsByTagName("i");
		for(int i = 0; i < itemList.getLength(); i++){
			
		}
	}
	
	
}
