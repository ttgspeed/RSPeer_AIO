package io.stricker.nodes.wintertodt;

import io.stricker.CombatStore;
import io.stricker.config.Config;
import io.stricker.config.Predicates;
import io.stricker.debug.Logger;
import io.stricker.framework.BackgroundTaskExecutor;
import io.stricker.framework.Location;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.PathingEntity;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.pathfinding.region.util.Direction;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import io.stricker.services.LootService;
import io.stricker.wrappers.CombatWrapper;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

import java.util.function.Predicate;

public class BankAction extends Node {

    private NpcResult result;
    private String status;
    private boolean running;

    private final static Area BANK_AREA = Area.rectangular(1638, 3943, 1640, 3945);

    public BankAction(){}

    @Override
    public boolean validate() {
        SceneObject target = SceneObjects.getNearest(6924);

        if(!Players.getLocal().isMoving() && BANK_AREA.contains(Players.getLocal())) {
            if(Inventory.getCount(Predicates.WINE_PREDICATE) != 10) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        SceneObject target = SceneObjects.getNearest(29321);

        if(target != null){
            target.interact("Bank");
            if(Bank.isOpen()){
                Bank.depositAllExcept(Predicates.KNIFE_PREDICATE);
                Time.sleep(648, 923);
                Bank.withdraw(Predicates.WINE_PREDICATE,10);
                //Bank.close();
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
        return status;
    }
}
