package io.stricker.nodes.plough;

import io.stricker.debug.Logger;
import io.stricker.framework.Location;
import io.stricker.framework.Node;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;

public class PloughZone extends Node {

    private final static Area TREE_AREA = Area.rectangular(1761, 3542, 1781, 3558);

    @Override
    public boolean validate() {
        //Log.fine("Validating Plough Zone");
        //Log.fine(Movement.isDestinationSet());
        return !TREE_AREA.contains(Players.getLocal()) && (Movement.getDestination() == null);
    }

    @Override
    public void execute() {
        Logger.debug("Walking to: Plough Field");
        Movement.getDaxWalker().walkTo(Location.location(TREE_AREA,"the plough area").asPosition().randomize(5));
        Time.sleep(200, 450);
    }

    @Override
    public void onInvalid() {
        Logger.debug("Disposing back to fight zone.");
    }

    @Override
    public String status() {
        return "Walking back to plough zone.";
    }
}
