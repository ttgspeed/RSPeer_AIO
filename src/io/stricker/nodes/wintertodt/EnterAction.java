package io.stricker.nodes.wintertodt;

import io.stricker.config.Predicates;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

public class EnterAction extends Node {

    private NpcResult result;
    private String status;
    private boolean running;

    private final static Area DOOR_AREA = Area.rectangular(1628, 3962, 1632, 3963);

    public EnterAction(){}

    @Override
    public boolean validate() {
        if(CurrentStatus.get() == Status.ENTERING) {
            if(!Players.getLocal().isMoving() && DOOR_AREA.contains(Players.getLocal())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        SceneObject target = SceneObjects.getNearest(29322);
        if(target != null){
            target.interact("Enter");
            CurrentStatus.set(Status.CHOPPING);
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
        return "Entering Wintertodt";
    }
}
