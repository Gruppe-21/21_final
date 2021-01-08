package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.MoveRelativeCard;
import com.gruppe21.deck.Deck;
import com.gruppe21.utils.localisation.Localisation;

public class MoveRelativeCardController extends CardController {
    private MoveRelativeCard model;
    Localisation localisation = Localisation.getInstance();

    public MoveRelativeCardController(CardView view, MoveRelativeCard cardModel) {
        super(view);
        this.model = cardModel;
    }


    @Override
    protected void onDraw(CardView view, MoveRelativeCard model) {
        String description = localisation.getStringValue(descriptionOnDrawLabel);
        // TO-DO: Make CardView show on draw description in GUI
    }

    @Override
    protected int use(Deck deck, CardController putBackCard, MoveRelativeCard model){
        super.use(deck,putBackCard);
        String description = localisation.getStringValue(descriptionOnUseLabel);

        int square_ID = model.getSquare_ID();

        return square_ID;
    }



    public MoveRelativeCard getModel() {
        return model;
    }

    public void setModel(MoveRelativeCard model) {
        this.model = model;
    }
}
