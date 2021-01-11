package com.gruppe21.prison;

import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.player.StatusEffects;

public class GetOutOfPrison {

    private StatusEffects statuseffects;
    PlayerController playercontroller;

    public StatusEffects GetOutOfPrison(PlayerController playerController, StatusEffects statusEffects){
        this.statuseffects = statusEffects;
        statuseffects.setImprisoned(false);

        this.playercontroller = playerController;

        playercontroller.transferMoney(1000, null); // Hvordan fører man penge tilbage til banken?
        return statuseffects;
    }
}
