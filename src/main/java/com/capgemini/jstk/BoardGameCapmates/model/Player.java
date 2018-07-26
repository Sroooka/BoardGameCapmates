package com.capgemini.jstk.BoardGameCapmates.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String nickname;
	private int points;
	private Rank rank;
	private String playerDescription;
	private List<BoardGame> ownedGames;
	private AbilityTime abilityTime;
	private List<ChallengeTO> newInvitations;
	private List<ChallengeTO> thrownInvitations;
	private List<ChallengeTO> acceptedInvitations;
	private List<ChallengeTO> rejectedInvitations;
	private List<BoardGame> playerBoardGames;
	private List<GameTO> myPlayedGamesInChallenges;

	public Player() {
		this.nickname = null;
		this.points = 0;
		this.rank = Rank.NEWBIE;
		this.playerDescription = "";
		this.ownedGames = new ArrayList<BoardGame>();
		this.newInvitations = new ArrayList<ChallengeTO>();
		this.thrownInvitations = new ArrayList<ChallengeTO>();
		this.acceptedInvitations = new ArrayList<ChallengeTO>();
		this.rejectedInvitations = new ArrayList<ChallengeTO>();
		this.playerBoardGames = new ArrayList<BoardGame>();
		this.myPlayedGamesInChallenges = new ArrayList<GameTO>();
		abilityTime = new AbilityTime();
	}

	public Player(String nickname) {
		this.nickname = nickname;
		this.points = 0;
		this.rank = Rank.NEWBIE;
		this.playerDescription = "";
		this.ownedGames = new ArrayList<BoardGame>();
		this.newInvitations = new ArrayList<ChallengeTO>();
		this.thrownInvitations = new ArrayList<ChallengeTO>();
		this.acceptedInvitations = new ArrayList<ChallengeTO>();
		this.rejectedInvitations = new ArrayList<ChallengeTO>();
		this.playerBoardGames = new ArrayList<BoardGame>();
		this.myPlayedGamesInChallenges = new ArrayList<GameTO>();
		abilityTime = new AbilityTime();
	}

	public Player(String nickname, int points, String playerDescription) {
		this.nickname = nickname;
		this.points = points;
		this.playerDescription = playerDescription;
		this.ownedGames = new ArrayList<BoardGame>();
		this.newInvitations = new ArrayList<ChallengeTO>();
		this.thrownInvitations = new ArrayList<ChallengeTO>();
		this.acceptedInvitations = new ArrayList<ChallengeTO>();
		this.rejectedInvitations = new ArrayList<ChallengeTO>();
		this.playerBoardGames = new ArrayList<BoardGame>();
		this.myPlayedGamesInChallenges = new ArrayList<GameTO>();
		abilityTime = new AbilityTime();
		this.computeRank();
	}

	public void addGameWhichTookPlace(GameTO game){
		myPlayedGamesInChallenges.add(game);
	}
	
	public void addNewInvitation(ChallengeTO challenge) {
		newInvitations.add(challenge);
	}

	public void acceptInvitation(ChallengeTO challenge) {
		newInvitations.remove(challenge);
		acceptedInvitations.add(challenge);
	}

	public void rejectInvitation(ChallengeTO challenge) {
		newInvitations.remove(challenge);
		rejectedInvitations.add(challenge);
	}

	public void addThrowInvitation(ChallengeTO challenge) {
		thrownInvitations.add(challenge);
	}

	public void addGame(BoardGame game) {
		if (!ownedGames.contains(game)) {
			ownedGames.add(game);
		}
	}

	public List<BoardGame> getPlayerBoardGames() {
		return playerBoardGames;
	}

	public void setPlayerBoardGames(List<BoardGame> playerBoardGames) {
		this.playerBoardGames = playerBoardGames;
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

	public List<ChallengeTO> getNewInvitations() {
		return newInvitations;
	}

	public void setNewInvitations(List<ChallengeTO> newInvitations) {
		this.newInvitations = newInvitations;
	}

	public List<ChallengeTO> getThrownInvitations() {
		return thrownInvitations;
	}

	public void setThrownInvitations(List<ChallengeTO> thrownInvitations) {
		this.thrownInvitations = thrownInvitations;
	}

	public List<ChallengeTO> getAcceptedInvitations() {
		return acceptedInvitations;
	}

	public void setAcceptedInvitations(List<ChallengeTO> acceptedInvitations) {
		this.acceptedInvitations = acceptedInvitations;
	}

	public List<ChallengeTO> getRejectedInvitations() {
		return rejectedInvitations;
	}

	public void setRejectedInvitations(List<ChallengeTO> rejectedInvitations) {
		this.rejectedInvitations = rejectedInvitations;
	}

	private void computeRank() {
		System.out.println(this.points);
		if (this.points < 10) {
			this.rank = Rank.NEWBIE;
		}
		else if (this.points < 20) {
			this.rank = Rank.WEAKLING;
		}
		else if (this.points < 40) {
			this.rank = Rank.BEGINNER;
		}
		else if (this.points < 80) {
			this.rank = Rank.EXPERIENCED_BEGINNER;
		}
		else if (this.points < 160) {
			this.rank = Rank.MIDDLEBROW;
		}
		else if (this.points < 320) {
			this.rank = Rank.EXPERIENCED_MIDDLEBORW;
		}
		else if (this.points < 640) {
			this.rank = Rank.ADVANCED;
		}
		else if (this.points < 1280) {
			this.rank = Rank.PROFESSIONAL;
		}
		else if (this.points < 2560) {
			this.rank = Rank.MASTER;
		}
		else if (this.points < 5120) {
			this.rank = Rank.ULTRA_MASTER;
		}
		else if (this.points < 99999) {
			this.rank = Rank.ULTRA_TURBO_MASTER;
		}else{
			this.rank = Rank.GOD_OF_BOARD_GAMES;
		}
		
	}

	public void addPoints(int points) {
		this.points+=points;
		this.computeRank();
	}
}
