package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class ExistingNicknameException extends Exception {

	private static final long serialVersionUID = -2437109279436371222L;

	public ExistingNicknameException() {
		super("There are player with this nickname!");
	}
	
	public ExistingNicknameException(String message) {
		super("There are player with this nickname! " + message);
	}
}
