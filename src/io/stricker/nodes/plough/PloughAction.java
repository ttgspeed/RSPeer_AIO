package io.stricker.nodes.plough;

import io.stricker.CombatStore;
import io.stricker.config.Config;
import io.stricker.debug.Logger;
import io.stricker.framework.BackgroundTaskExecutor;
import io.stricker.framework.Location;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.PathingEntity;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.pathfinding.region.util.Direction;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import io.stricker.services.LootService;
import io.stricker.wrappers.CombatWrapper;
import org.rspeer.ui.Log;

public class PloughAction extends Node {

    private NpcResult result;
    private String status;
    private boolean running;

    public PloughAction(){}

    @Override
    public boolean validate() {
        Npc target = Npcs.getNearest(6924);
        Npc target_broken = Npcs.getNearest(6925);

        if(!Players.getLocal().isMoving()) {
            if (target_broken != null) {
                return true;
            } else if (target != null) {
                if (target.getDirection() == Direction.WEST || target.getDirection() == Direction.NORTH_WEST) {
                    if ((Players.getLocal().getPosition().getX() == (target.getPosition().getX() - 2)) &&  Players.getLocal().getPosition().getY() == target.getPosition().getY()){
                        return true;
                    }
                } else if (target.getDirection() == Direction.EAST || target.getDirection() == Direction.NORTH_EAST) {
                    if ((Players.getLocal().getPosition().getX() == (target.getPosition().getX() + 2)) &&  Players.getLocal().getPosition().getY() == target.getPosition().getY()){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void execute() {
        Npc target = Npcs.getNearest(6924);
        Npc target_broken = Npcs.getNearest(6925);

//        if(target != null && target_broken != null){
//            if(Players.getLocal().getPosition().distance(target.getPosition()) < Players.getLocal().getPosition().distance(target_broken.getPosition())){
//                target.interact("Push");
//            }
//        }

        if (target_broken != null) {
            target_broken.interact("Repair");
        } else if (target != null) {
            target.interact("Push");
        } else {
            Log.fine("Could not find target");
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
        return status;
    }
}
