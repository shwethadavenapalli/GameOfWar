package com.model;

/**
 * Created by Shwetha on 27-12-2018.
 */
public class Move {
    private final Player player;
    private final Card card;

    public Move(Player player, Card card) {

        this.player = player;
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move that = (Move) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        return card != null ? card.equals(that.card) : that.card == null;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        return result;
    }
}
