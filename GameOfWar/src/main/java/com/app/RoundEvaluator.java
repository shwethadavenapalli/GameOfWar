package com.app;

import com.model.GameStatus;
import com.model.Move;

import java.util.List;

/**
 * Created by Shwetha on 28-12-2018.
 */
public class RoundEvaluator {


    public static GameStatus getGameStatus(List<Move> moveList) {
        boolean allMovesHasSameValue = moveList.stream().allMatch(
                move -> move.getCard().getValue() ==
                        (moveList.get(0).getCard().getValue()));
        if (allMovesHasSameValue)
            return GameStatus.War;

        return GameStatus.Playable;
    }
}
