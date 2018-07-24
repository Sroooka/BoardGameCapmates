package com.capgemini.jstk.BoardGameCapmates.repository;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper;
import com.capgemini.jstk.BoardGameCapmates.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper.*;


@Repository
public class PlayerListDAO {

	private Map<String, Player> playerList;
	
	PlayerListDAO(){
		this.playerList = new HashMap<>();

	}

	
	public int size(){
		return playerList.size();
	}
	
	public boolean isEmpty(){
		return playerList.isEmpty();
	}

	

	public void updatePlayer(Player editedPlayer){
		playerList.put(editedPlayer.getNickname(), editedPlayer);
	}
	
	public Player getUserByNick(String nickname) throws NonExistingPlayerException {
		for(Player player : playerList.values()){
            if(player.getNickname().equals(nickname)){
                return player;
            }
        }

        throw new NonExistingPlayerException();
	}


	public Map<String, PlayerTO> searchPlayersByRank(Rank rank) {
		Map<String, PlayerTO> returnMap = new HashMap<>();
		for (Map.Entry<String, Player> entry : playerList.entrySet())
		{
		    if(entry.getValue().getRank() == rank){
		    	returnMap.put(entry.getKey(), makeTOFromPlayer(entry.getValue()));
		    }
		}
		return returnMap;
	}
	
	public Map<String, PlayerTO> searchPlayersByGame(BoardGame boardGame) {
		Map<String, PlayerTO> returnMap = new HashMap<>();
		for (Map.Entry<String, Player> entry : playerList.entrySet())
		{
		    List<BoardGame> gameList = entry.getValue().getOwnedGames();
		    for(BoardGame game : gameList){
		    	if(game.getName() == boardGame.getName()){
			    	returnMap.put(entry.getKey(), makeTOFromPlayer(entry.getValue()));
		    	}
		    }
		}
		return returnMap;
	}

}
