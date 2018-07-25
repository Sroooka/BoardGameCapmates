package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class NotExistingBoardGameException extends Exception {

	private static final long serialVersionUID = -1405368248287472803L;

	public NotExistingBoardGameException() {
		super("There is no Board Game with this name!");
	}
	
	public NotExistingBoardGameException(String message) {
		super("There is no Board Game with this name! " + message);
	}
}
