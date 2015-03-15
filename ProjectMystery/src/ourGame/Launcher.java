package ourGame;


public class Launcher {
	MessageSender msgSend;
	Game game;
	
	public Launcher()
	{
		MessageSender msgSend = new MessageSender();
		Game game = new Game();
		game.parser(game.getAreas(), "open door");
		game.parser(game.getAreas(), "go great unknown");
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
