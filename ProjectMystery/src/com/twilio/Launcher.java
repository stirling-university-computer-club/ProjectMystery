package com.twilio;


public class Launcher {
	Game game;
	
	public Launcher()
	{
		Game game = new Game();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		System.out.println(game.parser("look"));
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
