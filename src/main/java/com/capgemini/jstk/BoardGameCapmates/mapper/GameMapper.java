package com.capgemini.jstk.BoardGameCapmates.mapper;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.BoardGameCapmates.model.TO.GameTO;
import com.capgemini.jstk.BoardGameCapmates.model.entity.Game;

@Component
public class GameMapper {
	public static Game makeGameFromTO(GameTO gameTO){
		Game game = new Game();
		game.setListOfPlayerNicknames(gameTO.getListOfPlayerNicknames());
		game.setBoardGame(gameTO.getBoardGame());
		game.setNumberOfPlayers(gameTO.getNumberOfPlayers());
		return game;
	}
	
	public static GameTO makeTOFromGame(Game game){
		GameTO gameTO = new GameTO();
		gameTO.setListOfPlayerNicknames(game.getListOfPlayerNicknames());
		gameTO.setBoardGame(game.getBoardGame());
		gameTO.setNumberOfPlayers(game.getNumberOfPlayers());
		return gameTO;
	}
}
