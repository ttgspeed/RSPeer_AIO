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
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Scene;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class WintertodtStats extends Node {
    private NpcResult result;

    private static int points = 0;
    private static int energy = 0;
    private static int returns = 0;

    private static int roundsCompleted = 0;
    private static int initialXP = 0;

    public WintertodtStats(){}

    @Override
    public boolean validate() {
        InterfaceComponent pointsInterface = Interfaces.getComponent(396,7);
        InterfaceComponent energyInterface = Interfaces.getComponent(396,21);
        InterfaceComponent returnsInterface = Interfaces.getComponent(396,3);
        if(pointsInterface != null){
            String pointsStr = pointsInterface.getText();
            pointsStr = pointsStr.replaceAll("\\D+","");
            points = parseInt(pointsStr);
        }
        if(energyInterface != null){
            String energyStr = energyInterface.getText();
            energyStr = energyStr.replaceAll("\\D+","");
            energy = parseInt(energyStr);
        }
        if(returnsInterface != null){
            String returnsStr = returnsInterface.getText();
            returnsStr = returnsStr.replaceAll("\\D+","");
            returns = parseInt(returnsStr);
        }

        return false;
    }

    @Override
    public void execute() {}

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
        return null;
    }

    private int parseInt(String pointStr){
        try {
            return Integer.parseInt(pointStr);
        }
        catch (NumberFormatException e)
        {
        }
        return 0;
    }

    public static int getPoints(){
        return points;
    }
    public static int getEnergy(){
        return energy;
    }
    public static int getReturns(){
        return returns;
    }

    public static int getXPGained() {
        if(initialXP == 0){
            initialXP = Skills.getExperience(Skill.FIREMAKING);
        }
        return Skills.getExperience(Skill.FIREMAKING)-initialXP;
    }

    public static int getRoundsCompleted() {
        return roundsCompleted;
    }

    public static void setInitialXP(int initialXP) {
        WintertodtStats.initialXP = initialXP;
    }

    public static void roundsCompleted() {
        roundsCompleted++;
    }
}
