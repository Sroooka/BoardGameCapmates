package com.capgemini.jstk.BoardGameCapmates.model;

public class BoardGame {
	private String name;
	private int minPlayers;
	private int maxPlayers;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinPlayers() {
		return minPlayers;
	}
	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}
	public int getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
}
