package io.stricker.nodes.wintertodt;

import io.stricker.config.Areas;
import io.stricker.config.Predicates;
import io.stricker.framework.Location;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Scene;
import org.rspeer.ui.Log;

public class TraverseBrazier extends Node {
    private NpcResult result;

    private final static Area BRAZIER_AREA = Area.absolute(new Position(1622, 3996));

    public TraverseBrazier(){}

    @Override
    public boolean validate() {
        if (CurrentStatus.get() == Status.BURNING){
            if(!Players.getLocal().isMoving() && Areas.WINTERTODT_AREA.contains(Players.getLocal()) && !BRAZIER_AREA.contains(Players.getLocal())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        Log.fine("Walking to Brazier...");
        Movement.walkTo(Location.location(BRAZIER_AREA).asPosition());
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
        return "Walking to Brazier";
    }
}