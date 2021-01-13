package com.gruppe21.squares.views;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.TaxSquare;
import com.gruppe21.utils.localisation.Localisation;

public class TaxSquareView extends SquareView{
    final protected GUIManager guiManager;
    final protected Localisation localisation;

    public TaxSquareView() {
        guiManager = GUIManager.getInstance();
        localisation = Localisation.getInstance();
    }

    /**
     *
     * @param playerController
     * @param model
     * @return true if the player has chosen to pay the tax and false if the player choose to pay the flat fee
     */
    public boolean TaxOrFee(PlayerController playerController, TaxSquare model){
        return guiManager.getUserBoolean("tax_or_fee", ((int)(model.getTax() * 100) )+ "%", localisation.getStringValue("currency", Integer.toString(model.getModifyAmount())));
    }

}
