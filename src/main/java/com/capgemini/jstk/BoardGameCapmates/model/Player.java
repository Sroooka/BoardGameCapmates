package com.capgemini.jstk.BoardGameCapmates.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

public class Player {
	private String nickname;
	private int points;
	private Rank rank;
	private String playerDescription;
	private List<BoardGame> ownedGames;
	private AbilityTime abilityTime;
	private List<Integer> newInvitationsByID;
	private List<Integer> acceptedInvitationsByID;
	private List<Integer> rejectedInvitationsByID;

	public Player() {
		this.nickname = null;
		this.points = 0;
		this.rank = Rank.NEWBIE;
		this.playerDescription = "";
		this.ownedGames = new ArrayList<BoardGame>();
		this.newInvitationsByID = new ArrayList<Integer>();
		this.acceptedInvitationsByID = new ArrayList<Integer>();
		this.rejectedInvitationsByID = new ArrayList<Integer>();
		abilityTime = new AbilityTime();
	}

	public Player(String nickname) {
		this.nickname = nickname;
		this.points = 0;
		this.rank = Rank.NEWBIE;
		this.playerDescription = "";
		this.ownedGames = new ArrayList<BoardGame>();
		abilityTime = new AbilityTime();
	}
	
	public void addInvitation(Integer id){
		newInvitationsByID.add(id);
	}
	
	public void acceptInvitation(Integer id){
		newInvitationsByID.remove(id);
		acceptedInvitationsByID.add(id);
	}
	
	public void rejectInvitation(Integer id){
		newInvitationsByID.remove(id);
		rejectedInvitationsByID.add(id);
	} 

	public void addGame(BoardGame game) {
		if (!ownedGames.contains(game)) {
			ownedGames.add(game);
		}
	}
	
	public void setNewAbilityTime(int dayOfWeek, int fromHours, int fromMinutes, int toHours, int toMinutes){
		abilityTime.setAbility(dayOfWeek, fromHours, fromMinutes, toHours, toMinutes);
	}
	
	public void clearAbilityTime(){
		abilityTime.clearAbility();
	}
	
	public void clearAbilityTime(int dayOfWeek){
		abilityTime.clearAbility(dayOfWeek);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String getPlayerDescription() {
		return playerDescription;
	}

	public void setPlayerDescription(String playerDescription) {
		this.playerDescription = playerDescription;
	}

	public List<BoardGame> getOwnedGames() {
		return ownedGames;
	}

	public void setOwnedGames(List<BoardGame> ownedGames) {
		this.ownedGames = ownedGames;
	}

	public AbilityTime getAbilityTime() {
		return abilityTime;
	}

	public void setAbilityTime(AbilityTime abilityTime) {
		this.abilityTime = abilityTime;
	}

}
