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

public class PloughNode extends Node {

    private NpcResult result;
    private String status;
    private boolean running;

    public PloughNode(){}

    @Override
    public boolean validate() {
        if((Players.getLocal().getTargetIndex() == -1) && !Players.getLocal().isMoving()){
            Npc target = Npcs.getNearest(6924);

            if(!Players.getLocal().isMoving()) {
                if (target != null) {
                    if (target.getDirection() == Direction.WEST || target.getDirection() == Direction.NORTH_WEST /*|| (target.getDirection() == Direction.EAST && target.getOrientation() == 512)*/) {
                        if ((Players.getLocal().getPosition().getX() != (target.getPosition().getX() - 2)) || Players.getLocal().getPosition().getY() != target.getPosition().getY()){
                            return true;
                        }
                    } else if (target.getDirection() == Direction.EAST || target.getDirection() == Direction.NORTH_EAST) {
                        if ((Players.getLocal().getPosition().getX() != (target.getPosition().getX() + 2)) || Players.getLocal().getPosition().getY() != target.getPosition().getY()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void execute() {
        Npc target = Npcs.getNearest(6924);

        if (target != null) {
            Log.fine("Found plough at " + target.getPosition() + " facing " + target.getDirection() + " orientation "+target.getOrientation());
            if (target.getDirection() == Direction.WEST || target.getDirection() == Direction.NORTH_WEST) {
                Movement.walkTo(Location.location(Area.absolute(new Position(target.getPosition().getX() - 2, target.getPosition().getY())), "behind the west plow").asPosition());
            } else if (target.getDirection() == Direction.EAST || target.getDirection() == Direction.NORTH_EAST) {
                Movement.walkTo(Location.location(Area.absolute(new Position(target.getPosition().getX() + 2, target.getPosition().getY())), "behind the east plow").asPosition());
            }
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
