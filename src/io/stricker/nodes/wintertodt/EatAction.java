package io.stricker.nodes.wintertodt;

import io.stricker.config.Config;
import io.stricker.config.Predicates;
import io.stricker.debug.Logger;
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
        if(CombatWrapper.getHealthPercent() <= Random.high(48, 70)){
            if(Inventory.getFirst(Predicates.JUG_OF_WINE) != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        Inventory.getFirst(Predicates.JUG_OF_WINE).interact("Drink");
        Time.sleep(648, 923);
    }

    @Override
    public void onInvalid() {}

    @Override
    public String status() {
        return "Eating";
    }
}
