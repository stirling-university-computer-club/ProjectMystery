package ourGame;

public class WorkingGame {
	
	private WorkingTable table;
	
	public WorkingGame() {
		table = new WorkingTable();
	}
	
	public String inputText(String mobile, String inputText){
		if (!(table.isInList(mobile))) {
			table.addToList(mobile, 1);
		}
		if (inputText.toLowerCase().equals("restart") || inputText.toLowerCase().equals("new game")) {
			table.modifyProgress(mobile, 1);
		}
		if (table.getAreaProgress(mobile) == 1) {
			if (inputText.toLowerCase().equals("look") || inputText.toLowerCase().equals("look around")) {
				return "You are in a hall sitting on a chair surrounded by people with laptops, watching some uncomprehendable presentation. Infront of you is your own laptop. To your right is the entrance hall, to your left is a door marked push to open";
			}
			else if (inputText.toLowerCase().equals("look at laptop")) {
				return "You stare at the laptop to see only one word appearing on the screen: \"Segfault\"";
			}
			else if (inputText.toLowerCase().equals("open door") || inputText.toLowerCase().equals("go left")) {
				return "You push open the door and alarms start blaring, the security moves towards you. Being the brave person you are, you decide to turn tail and leg it. The door shuts behind you, as you reflect on what just happened a bolt of lighting strikes you";
			}
			else if (inputText.toLowerCase().equals("go right")) {
				table.modifyProgress(mobile, 2);
				return "You see lots of tables covered in discarded food";
			}
			else {
				return "I dont understand that command";
			}
		}
		else if (table.getAreaProgress(mobile) == 2) {
			if (inputText.toLowerCase().equals("look") || inputText.toLowerCase().equals("look around")) {
				return "You see lots of tables covered in discarded food";
			}
			else if (inputText.toLowerCase().equals("eat food") || inputText.toLowerCase().equals("eat discarded food")) {
				return "You choke on a foosty crumb and drop to the floor dead GG";
			}
			else if (inputText.toLowerCase().equals("pick up food") || inputText.toLowerCase().equals("pick up discarded food")) {
				return "A virulent mould spreads up your arm consuming you completely. Your corpse drops to the floor with a thud muffled by a thick layer of furry mould";
			}
			else {
				return "I dont understand that command";
			}
		}
		return "uh oh";
	}

}
