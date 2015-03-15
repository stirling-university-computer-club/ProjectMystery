package com.twilio;


public class Launcher {
	Game game;
	
	public Launcher()
	{
		Game game = new Game();
	}
	
	public void makeMessageSender()
	{
		MessageSender msgSend = new MessageSender();
	}
	
	public Game getGame()
	{
		return game;
	}

}
