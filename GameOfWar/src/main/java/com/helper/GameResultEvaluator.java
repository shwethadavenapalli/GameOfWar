package com.helper;

import com.model.Card;
import com.model.Move;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Shwetha on 27-12-2018.
 */
public class GameResultEvaluator {

    public static Move getWinnerForThisMove(List<Move> moveList) {
        return moveList.stream()
                .max(Comparator.comparing(playerCardDetails -> playerCardDetails.getCard().getValue()))
                .get();

    }


    public static void updatePlayerCardStock(List<Move> moveList) {
        Move winnerForThisMove = getWinnerForThisMove(moveList);
        for (Move move : moveList) {
            if (!move.equals(winnerForThisMove)) {
                Card wonCard = move.getCard();
                winnerForThisMove.getPlayer().getCardStock().add(wonCard);
            } else {
                //add the card removed
                winnerForThisMove.getPlayer().getCardStock().add(move.getCard());
            }
        }
    }
}
