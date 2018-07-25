package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class TooMuchPlayersException extends Exception{

	private static final long serialVersionUID = -4425586999303441806L;
	
	public TooMuchPlayersException() {
		super("Too much players for this game!");
	}
	
	public TooMuchPlayersException(String message) {
		super("Too much players for this game! " + message);
	}
}
