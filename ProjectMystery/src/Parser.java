import java.util.ArrayList;

import world.Area;


public class Parser {
	
	public String logicCheck(Game gref, ArrayList<Area> areas, String s){
		Area area = areas.get(gref.getPlayerArea());
		if(s.equals("look"))
			return area.getLook();
	return "Invalid!";
	}
	
	
}
