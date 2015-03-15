package com.twilio;


public class Launcher {
	MessageSender msgSend;
	
	public static void main(String[] args)
	{
		MessageSender msgSend = new MessageSender();
		WorkingGame game = new WorkingGame();
		System.out.println(game.inputText("1", "look"));
		System.out.println(game.inputText("1", "start"));
		System.out.println(game.inputText("1", "Go Right"));
		System.out.println(game.inputText("1", "Eat Food"));
		System.out.println(game.inputText("1", "Eat Food"));
		System.out.println(game.inputText("1", "Restart"));
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
