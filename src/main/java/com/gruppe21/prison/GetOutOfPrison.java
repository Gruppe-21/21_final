package com.gruppe21.prison;

import com.gruppe21.player.StatusEffects;

public class GetOutOfPrison {

    private StatusEffects statuseffects;

    public StatusEffects GetOutOfPrison(StatusEffects statusEffects){
        this.statuseffects = statusEffects;
        statuseffects.setImprisoned(false);
        return statuseffects;
    }
}
