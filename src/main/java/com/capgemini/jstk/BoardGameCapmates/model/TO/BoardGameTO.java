package com.capgemini.jstk.BoardGameCapmates.model.TO;

public class BoardGameTO {
	private String name;
	private int minPlayers;
	private int maxPlayers;

	public BoardGameTO() {
		this.name = null;
		this.minPlayers = 0;
		this.maxPlayers = 0;
	}

	public BoardGameTO(String name, int minPlayers, int maxPlayers) {
		this.name = name;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
	}

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
