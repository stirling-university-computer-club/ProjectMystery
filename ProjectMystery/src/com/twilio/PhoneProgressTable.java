package com.twilio;
import java.util.Hashtable;


public class PhoneProgressTable {
	
	private Hashtable<String, Game> phoneList;
	
	public PhoneProgressTable() {
		phoneList = new Hashtable<String, Game>();
	}
	
	public void addToList(String phoneNo, Game progress) {
		Game check = phoneList.get(phoneNo);
		if (check == null) {
			phoneList.put(phoneNo, progress);
		}
	}
	
	public boolean isInList(String phoneNo) {
		return phoneList.containsKey(phoneNo);
	}
	
	public void modifyProgress(String phoneNo, String progress) {
		Game check = phoneList.get(phoneList);
		if (check != null) {
			check.parser(check.getAreas(), progress );
		}
	}
	
	public int getAreaProgress(String phoneNo) {
		Game check = phoneList.get(phoneList);
		if (check !=  null) {
			return check.getPlayerArea();
		}
		return 0;
	}
	
	
	

}
