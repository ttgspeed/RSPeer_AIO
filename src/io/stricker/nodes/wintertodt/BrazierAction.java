package io.stricker.nodes.wintertodt;

import io.stricker.config.Areas;
import io.stricker.config.Predicates;
import io.stricker.framework.Location;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Scene;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class BrazierAction extends Node {
    private NpcResult result;

    private final static Area BRAZIER_AREA = Area.absolute(new Position(1622, 3996));

    public BrazierAction(){}

    @Override
    public boolean validate() {
        if (CurrentStatus.get() == Status.BURNING){
            if(!Players.getLocal().isMoving() && Areas.WINTERTODT_AREA.contains(Players.getLocal()) && BRAZIER_AREA.contains(Players.getLocal())) {
                return true;
            }
        }
        //1622 3996
        return false;
    }

    @Override
    public void execute() {
        SceneObject target = SceneObjects.getNearest(29314);

        if(target != null){
            if(Inventory.getFirst(Predicates.BRUMA_KINDLING) != null){
                Time.sleep(1250, 1780);
                if(target.getPosition().getX() == 1620 && target.getPosition().getY() == 3997) {
                    if (Players.getLocal().getAnimation() == -1) {
                        Log.fine("Feeding Bruma...");
                        target.interact("Feed");
                    }
                }
            } else{
                if(WintertodtStats.getPoints() < 500){
                    CurrentStatus.set(Status.CHOPPING);
                } else {
                    CurrentStatus.set(Status.BANKING);
                }
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
        return "Feeding Brazier";
    }
}
