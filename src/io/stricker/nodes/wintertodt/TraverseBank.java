package io.stricker.nodes.wintertodt;

import io.stricker.config.Areas;
import io.stricker.config.Predicates;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class TraverseBank extends Node {
    private NpcResult result;
    private String status;

    private final static Area BANK_AREA = Area.rectangular(1638, 3943, 1640, 3945);

    public TraverseBank(){}

    @Override
    public boolean validate() {
        SceneObject target = SceneObjects.getNearest(6924);
        if(CurrentStatus.get() == Status.BANKING || CurrentStatus.get() == Status.IDLE) {
            if(!Players.getLocal().isMoving() && !BANK_AREA.contains(Players.getLocal()) && !Areas.WINTERTODT_AREA.contains((Players.getLocal()))) {
                return true;
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
        return "Walking to bank...";
    }
}
