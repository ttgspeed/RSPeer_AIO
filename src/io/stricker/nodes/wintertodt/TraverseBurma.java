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
import org.rspeer.runetek.api.movement.position.ScenePosition;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Scene;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

import java.util.function.Predicate;

public class TraverseBurma extends Node {
    private NpcResult result;
    private String status;

    private final static Area WT_AREA = Area.rectangular(1628, 3962, 1643, 3999);
    private final static Area DOOR_AREA = Area.rectangular(1628, 3962, 1632, 3963);

    public TraverseBurma(){}

    @Override
    public boolean validate() {
        if(!Players.getLocal().isMoving() && !DOOR_AREA.contains(Players.getLocal())) {
            if(Inventory.getCount(Predicates.WINE_PREDICATE) == 10) {
                Scene.getBase().getPosition();
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        Log.fine("Walking...");
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
        return status;
    }
}
