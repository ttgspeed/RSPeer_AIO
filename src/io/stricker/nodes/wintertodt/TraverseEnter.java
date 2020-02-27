package io.stricker.nodes.wintertodt;

import io.stricker.config.Areas;
import io.stricker.config.Predicates;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;

public class TraverseEnter extends Node {
    private NpcResult result;
    private String status;

    private final static Area DOOR_AREA = Area.rectangular(1628, 3960, 1632, 3963);

    public TraverseEnter(){}

    @Override
    public boolean validate() {
        if (CurrentStatus.get() == Status.ENTERING && !Areas.WINTERTODT_AREA.contains(Players.getLocal())){
            if (!Players.getLocal().isMoving() && !DOOR_AREA.contains(Players.getLocal())) {
                    return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        Log.fine("Walking to entrance...");
        Movement.getDaxWalker().walkTo(DOOR_AREA.getCenter().randomize(1));
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
        return "Walking to entrance...";
    }
}
