package io.stricker.nodes.wintertodt;

import io.stricker.config.Areas;
import io.stricker.config.Predicates;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

public class NewRoundAction extends Node {

    private NpcResult result;
    private String status;
    private boolean running;

    private final static Area DOOR_AREA = Area.rectangular(1628, 3962, 1632, 3963);

    public NewRoundAction(){}

    @Override
    public boolean validate() {
        if(CurrentStatus.get() == Status.ENTERING) {
            if(!Players.getLocal().isMoving() && Areas.WINTERTODT_AREA.contains(Players.getLocal())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        Time.sleep(1757,3287);
        if(WintertodtStats.getEnergy() == 0 || WintertodtStats.getEnergy() > 95){
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
        return "Waiting for new round";
    }
}
