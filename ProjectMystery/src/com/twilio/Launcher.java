package ourGame;


public class Launcher {
	MessageSender msgSend;
	
	public static void main(String[] args)
	{
		MessageSender msgSend = new MessageSender();
		WorkingGame game = new WorkingGame();
		System.out.println(game.inputText("1", "look"));
	}
	
	public void makeMessageSender()
	{
		MessageSender msgSend = new MessageSender();
	}
	
	public MessageSender getMessageSender()
	{
		return msgSend;
	}

}
