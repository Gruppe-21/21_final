package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.MoveRelativeCard;
import com.gruppe21.deck.Deck;
import com.gruppe21.utils.localisation.Localisation;

public class MoveRelativeCardController extends CardController {

    private MoveRelativeCard model;
    private Localisation localisation;
    public MoveRelativeCardController(CardView view, MoveRelativeCard cardModel) {
        super(view);
        localisation = Localisation.getInstance();
        this.model = cardModel;
    }
/*

    @Override
    protected void onDraw(CardView view, MoveRelativeCard model) {
        String description = localisation.getStringValue(model.);
    }

    protected int use(Deck deck, CardController putBackCard, MoveRelativeCard model){
        super.use(deck,putBackCard);
        String description = localisation.getStringValue(descriptionOnUseLabel);

        int square_ID = model.getSquare_ID();

        return square_ID;
    }

*/

}
