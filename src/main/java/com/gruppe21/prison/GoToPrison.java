package com.gruppe21.prison;

import com.gruppe21.player.StatusEffects;

public class GoToPrison {

    private StatusEffects statuseffects;

    public StatusEffects GoToPrison(StatusEffects statusEffects){
        this.statuseffects = statusEffects;
        statuseffects.setImprisoned(true);
        return statuseffects;


    }

}
