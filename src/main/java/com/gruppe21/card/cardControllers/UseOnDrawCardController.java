package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardType.Card;
import com.gruppe21.card.view.CardView;
import com.gruppe21.player.PlayerController;

public abstract class UseOnDrawCardController extends CardController{

    public UseOnDrawCardController(CardView cardView, Card card){
        super(cardView, card);
    }

    /**
     * @param drawer Player that draws card
     */
    @Override
    public void onDraw(PlayerController drawer){
        use(drawer);
    }
}
