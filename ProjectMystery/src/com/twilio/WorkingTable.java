package com.twilio;


import java.util.Hashtable;

public class WorkingTable {

		
		private Hashtable<String, Integer> phoneList;
		
		public WorkingTable() {
			phoneList = new Hashtable<String, Integer>();
		}
		
		public void addToList(String phoneNo, Integer progress) {
			Integer check = phoneList.get(phoneNo);
			if (check == null) {
				phoneList.put(phoneNo, progress);
			}
		}
		
		public boolean isInList(String phoneNo) {
			return phoneList.containsKey(phoneNo);
		}
		
		public void modifyProgress(String phoneNo, Integer progress) {
			Integer check = phoneList.get(phoneNo);
			if (check != null) {
				phoneList.replace(phoneNo, progress);
			}
		}
		
		public int getAreaProgress(String phoneNo) {
			Integer check = phoneList.get(phoneNo);
			if (check !=  null) {
				return check;
			}
			return 0;
		}

	}
