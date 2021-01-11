package com.gruppe21.prison;

import com.gruppe21.player.StatusEffects;

public class Prison {

    StatusEffects statuseffects;

    public StatusEffects GoToPrison(StatusEffects statusEffects){
        this.statuseffects = statusEffects;
        statuseffects.setImprisoned(true);
        return statuseffects;


    }
    public StatusEffects GetOutOfPrison(StatusEffects statusEffects){
        this.statuseffects = statusEffects;
        statuseffects.setImprisoned(false);
        return statuseffects;
    }
}
