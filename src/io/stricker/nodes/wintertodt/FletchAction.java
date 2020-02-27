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
import org.rspeer.runetek.api.commons.math.Random;
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

public class FletchAction extends Node {
    private NpcResult result;

    public FletchAction(){}

    @Override
    public boolean validate() {
        if (CurrentStatus.get() == Status.FLETCHING){
            if(!Players.getLocal().isMoving() && Areas.WINTERTODT_AREA.contains(Players.getLocal())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if(Players.getLocal().getAnimation() == -1 || Players.getLocal().getAnimation() == 867) {
            if(Inventory.getFirst(Predicates.BRUMA_ROOT) != null){
                Inventory.getFirst(Predicates.KNIFE).interact("Use");
                Time.sleep(380, 725);
                int randomRoot = Random.low(Inventory.getFirst(Predicates.BRUMA_ROOT).getIndex(),Inventory.getLast(Predicates.BRUMA_ROOT).getIndex());
                Inventory.getItemAt(randomRoot).interact("Use");
                Time.sleep(725, 1225);
                Log.fine("Fletching Bruma...");
            } else {
                if(WintertodtStats.getEnergy() == 0){
                    CurrentStatus.set(Status.BANKING);
                }else {
                    CurrentStatus.set(Status.BURNING);
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
        return "Fletching";
    }
}
