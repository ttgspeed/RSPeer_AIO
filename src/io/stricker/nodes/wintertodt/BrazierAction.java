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
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Distance;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Scene;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.ui.Log;

public class BrazierAction extends Node {
    private NpcResult result;
    private int lastXP = Skills.getExperience(Skill.FIREMAKING);

    public BrazierAction(){}

    @Override
    public boolean validate() {
        if (CurrentStatus.get() == Status.BURNING){
            if(!Players.getLocal().isMoving() && Areas.WINTERTODT_AREA.contains(Players.getLocal()) && Areas.BRAZIER_AREA.contains(Players.getLocal())) {
                lastXP = Skills.getExperience(Skill.FIREMAKING);
                return true;
            }
        }
        //1622 3996
        return false;
    }

    @Override
    public void execute() {
        SceneObject target = SceneObjects.getNearest(29314);
        Npc pyromancer = Npcs.getNearest(7371);

        if(target != null && pyromancer != null){
            if(Distance.between(Areas.BRAZIER_AREA.getCenter().getPosition(),pyromancer.getPosition()) > 5){
                Log.fine("Swapping areas. Distance: "+Distance.between(Players.getLocal().getPosition(),pyromancer.getPosition()));
                Areas.swapAreas();
                return;
            }
            if(Inventory.getFirst(Predicates.BRUMA_KINDLING) != null){
                if(Distance.between(Players.getLocal().getPosition(),target.getPosition()) < 5) {
                    Time.sleep(1312, 1562);
                    if (Players.getLocal().getAnimation() == -1 && lastXP == Skills.getExperience(Skill.FIREMAKING)) {
                        if (Inventory.getFirst(Predicates.BRUMA_KINDLING) != null) {
                            Log.fine("Feeding Brazier...");
                            //lastXP = Skills.getExperience(Skill.FIREMAKING);
                            target.interact("Feed");
                        }
                    }
                }
            } else{
                if(WintertodtStats.getPoints() < 500){
                    CurrentStatus.set(Status.CHOPPING);
                } else {
                    CurrentStatus.set(Status.BANKING);
                }
            }
        } else {
            if(WintertodtStats.getEnergy() == 0){
                CurrentStatus.set(Status.BANKING);
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
        return "Feeding Brazier";
    }
}
