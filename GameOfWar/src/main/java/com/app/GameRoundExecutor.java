package com.app;

import com.config.CardDistributor;
import com.config.PlayerConfig;
import com.helper.GameResultEvaluator;
import com.model.Card;
import com.model.GameStatus;
import com.model.Move;
import com.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shwetha on 27-12-2018.
 */
public class GameRoundExecutor {
    private final PlayerConfig playerConfig;
    private final CardDistributor cardDistributor;
    private GameResultEvaluator gameResultEvaluator;
    GameStatus status = GameStatus.Playable;


    public GameRoundExecutor(PlayerConfig playerConfig, CardDistributor cardDistributor) {
        this.playerConfig = playerConfig;
        this.cardDistributor = cardDistributor;
    }

    public GameStatus executeRound() {

        //fetch move status

        //gameState -- gameStatus,Player details
        List<Move> movesList = fetchAllMovesForRound();
        if (doesPlayerHasNoCardPlay(movesList)) return status;

        GameStatus gameStatus = RoundEvaluator.getGameStatus(movesList);
        boolean roundExecutionFinished = false;
        while (true) {
            switch (gameStatus) {
                case Playable:
                    GameResultEvaluator.updatePlayerCardStock(movesList);
                    roundExecutionFinished = true;
                    break;
                case War:
                    List<Move> firstSubRound = fetchAllMovesForRound();
                    if (doesPlayerHasNoCardPlay(firstSubRound)) return status;
                    movesList.addAll(firstSubRound);
                    List<Move> secondSubRound = fetchAllMovesForRound();
                    if (doesPlayerHasNoCardPlay(secondSubRound)) return status;
                    movesList.addAll(secondSubRound);
                    gameStatus = RoundEvaluator.getGameStatus(secondSubRound);
                    break;
                case Draw:
                    break;
                case Won:
                    break;

            }

            if (roundExecutionFinished)
                break;
            else
                continue;
        }
        return gameStatus;
    }

    private boolean doesPlayerHasNoCardPlay(List<Move> movesList) {
        if (movesList == null) {
            return true;
        }
        return false;
    }

    public List<Move> fetchAllMovesForRound() {

        if (numberOfPlayerHasNonZeroCards() == 0) {
            status = GameStatus.Draw;
            return null;
        }
        if (!isDoesAnyPlayersHaveZeroCards()) {
            status = GameStatus.Won;
            return null;
        }

        List<Move> moveList = new ArrayList<>();
        int playerCount = playerConfig.getPlayerCount();
        while (playerCount > 0) {
            Player nextPlayer = playerConfig.getNextPlayer();
            Card playerCard = nextPlayer.getNextMove();
            Move playerMove = new Move(nextPlayer, playerCard);
            moveList.add(playerMove);
            playerCount--;

        }
        return moveList;
    }

    private boolean isDoesAnyPlayersHaveZeroCards() {
        int playerCount = playerConfig.getPlayerCount();
        while (playerCount > 0) {
            Player nextPlayer = playerConfig.getNextPlayer();
            if (nextPlayer.getCardStock().isEmpty()) {
                return false;
            }
            playerCount--;
        }
        return true;
    }

    private int numberOfPlayerHasNonZeroCards() {
        int playerCount = playerConfig.getPlayerCount();
        int numberOfPlayersHasNonZeroCards = 0;
        for (int i = 0; i < playerCount; i++) {
            Player nextPlayer = playerConfig.getNextPlayer();
            if (!nextPlayer.getCardStock().isEmpty()) {
                numberOfPlayersHasNonZeroCards++;
            }
        }
        return numberOfPlayersHasNonZeroCards;
    }

}
