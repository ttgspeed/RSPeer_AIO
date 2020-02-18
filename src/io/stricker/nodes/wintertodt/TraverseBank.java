package io.stricker.nodes.wintertodt;

import io.stricker.config.Predicates;
import io.stricker.framework.Location;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

import java.util.function.Predicate;

public class TraverseBank extends Node {
    private NpcResult result;
    private String status;

    private final static Area BANK_AREA = Area.rectangular(1638, 3943, 1640, 3945);

    public TraverseBank(){}

    @Override
    public boolean validate() {
        SceneObject target = SceneObjects.getNearest(6924);

        if(!Players.getLocal().isMoving() && !BANK_AREA.contains(Players.getLocal())) {
            if(Inventory.getCount(Predicates.WINE_PREDICATE) != 10) {
                if(Players.getLocal().getPosition().getY() < 3964) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void execute() {
        Log.fine("Walking to bank...");
        Movement.getDaxWalker().walkTo(BANK_AREA.getCenter().randomize(1));
    }

    @Override
    public void onInvalid() {
        super.onInvalid();
    }

    @Override
    public void onScriptStop() {
        super.onScriptStop();
    }

    @Override
    public String status() {
        return status;
    }
}
