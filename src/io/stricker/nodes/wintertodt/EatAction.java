package io.stricker.nodes.wintertodt;

import io.stricker.config.Config;
import io.stricker.config.Predicates;
import io.stricker.debug.Logger;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import io.stricker.wrappers.CombatWrapper;
import io.stricker.framework.Node;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.function.Predicate;

public class EatAction extends Node {

    @Override
    public boolean validate() {
        if(CombatWrapper.getHealthPercent() <= Random.high(48, 70) ||
                ((CurrentStatus.get() == Status.BANKING || CurrentStatus.get() == Status.IDLE) && CombatWrapper.getHealthPercent() < 95)){
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        if(Inventory.getFirst(Predicates.JUG_OF_WINE) != null){
            Inventory.getFirst(Predicates.JUG_OF_WINE).interact("Drink");
        } else {
            if(CombatWrapper.getHealthPercent() < 30){
                CurrentStatus.set(Status.BANKING);
            }
        }
        Time.sleep(648, 923);
    }

    @Override
    public void onInvalid() {}

    @Override
    public String status() {
        return "Eating";
    }
}
