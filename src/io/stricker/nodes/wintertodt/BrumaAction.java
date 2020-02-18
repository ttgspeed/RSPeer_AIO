package io.stricker.nodes.wintertodt;

import io.stricker.config.Areas;
import io.stricker.config.Predicates;
import io.stricker.framework.Location;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Scene;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class BrumaAction extends Node {
    private NpcResult result;

    private final static Area BRUMA_AREA = Area.absolute(new Position(1622, 3988));

    public BrumaAction(){}

    @Override
    public boolean validate() {
        if (CurrentStatus.get() == Status.CHOPPING){
            if(!Players.getLocal().isMoving() && Areas.WINTERTODT_AREA.contains(Players.getLocal()) && BRUMA_AREA.contains(Players.getLocal())) {
                return true;
            }
        }
        //1622 3996
        return false;
    }

    @Override
    public void execute() {
        Log.fine("Chopping Bruma...");
        SceneObject target = SceneObjects.getNearest(29311);

        if(target != null){
            if(!Inventory.isFull()) {
                if(Players.getLocal().getAnimation() == -1) {
                    target.interact("Chop");
                }
            } else {
                CurrentStatus.set(Status.BURNING);
            }
        }
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
        return "Chopping Bruma";
    }
}
