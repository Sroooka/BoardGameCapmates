package com.capgemini.jstk.BoardGameCapmates.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;

public class Challenge {
	private int challengeID;
	private BoardGame game;
	private int numberOfPlayers;
	private List<String> listOfPlayerNicknames;
	private LocalTime gameStart;
	private LocalTime gameEnd;
	private List<String> acceptations;
	private List<String> gameResult;
	private ChallengeCreator challengeCreator;
	private String ownerNickname;
	private int responsesFromPlayers;
	private List<String> comments;
	private String invitationMessage;
	private Game theGameTookPlace;

	public Challenge() {
		this.game = null;
		this.gameStart = null;
		this.gameEnd = null;
		this.ownerNickname = null;
		this.challengeCreator = null;
		this.numberOfPlayers = 0;
		this.listOfPlayerNicknames = new ArrayList<String>();
		this.gameResult = new ArrayList<String>();
		this.acceptations = new ArrayList<String>();
		this.comments = new ArrayList<String>();
		this.responsesFromPlayers = 0;
		this.invitationMessage = "";
	}

	public void setListOfPlayerNicknames(List<String> listOfPlayerNicknames)
			throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		if (this.game == null) {
			throw new NotSelectedBoardGame();
		}
		if (listOfPlayerNicknames.size() > this.game.getMaxPlayers()) {
			throw new TooMuchPlayersException();
		}
		if (listOfPlayerNicknames.size() < this.game.getMinPlayers()) {
			throw new NotEnoughPlayersException();
		}
		this.listOfPlayerNicknames = listOfPlayerNicknames;
		this.numberOfPlayers = listOfPlayerNicknames.size();
	}

	public void addAcceptation(String nickname, String comment) {
		acceptations.add(nickname);
		comments.add(nickname + " acceptation: " + comment);
		responsesFromPlayers++;
	}

	public void rejectAcceptation(String nickname, String comment) {
		comments.add(nickname + " rejectation: " + comment);
		responsesFromPlayers++;
	}

	public int getResponsesFromPlayers() {
		return responsesFromPlayers;
	}

	public void setResponsesFromPlayers(int responsesFromPlayers) {
		this.responsesFromPlayers = responsesFromPlayers;
	}



	public int getChallengeID() {
		return challengeID;
	}

	public void setChallengeID(int challengeID) {
		this.challengeID = challengeID;
	}

	public Game getTheGameTookPlace() {
		return theGameTookPlace;
	}

	public void setTheGameTookPlace(Game theGameTookPlace) {
		this.theGameTookPlace = theGameTookPlace;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public String getInvitationMessage() {
		return invitationMessage;
	}

	public void setInvitationMessage(String invitationMessage) {
		this.invitationMessage = invitationMessage;
	}

	public BoardGame getGame() {
		return game;
	}

	public void setGame(BoardGame game) {
		this.game = game;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public LocalTime getGameStart() {
		return gameStart;
	}

	public void setGameStart(LocalTime gameStart) {
		this.gameStart = gameStart;
	}

	public LocalTime getGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(LocalTime gameEnd) {
		this.gameEnd = gameEnd;
	}

	public List<String> getAcceptations() {
		return acceptations;
	}

	public void setAcceptations(List<String> acceptations) {
		this.acceptations = acceptations;
	}

	public List<String> getGameResult() {
		return gameResult;
	}

	public void setGameResult(List<String> gameResult) {
		this.gameResult = gameResult;
	}

	public ChallengeCreator getChallengeCreator() {
		return challengeCreator;
	}

	public void setChallengeCreator(ChallengeCreator challengeCreator) {
		this.challengeCreator = challengeCreator;
	}

	public String getOwnerNickname() {
		return ownerNickname;
	}

	public void setOwnerNickname(String ownerNickname) {
		this.ownerNickname = ownerNickname;
	}

	public List<String> getListOfPlayerNicknames() {
		return listOfPlayerNicknames;
	}

	public boolean canStartGame() {
		if (numberOfPlayers != responsesFromPlayers) {
			return false;
		}
		for (int i = 0; i < listOfPlayerNicknames.size(); i++) {
			if (!acceptations.contains(listOfPlayerNicknames.get(i))) {
				return false;
			}
		}
		return true;
	}

}
