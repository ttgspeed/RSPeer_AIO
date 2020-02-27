package io.stricker.nodes.wintertodt;

import io.stricker.config.Predicates;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
import io.stricker.wrappers.CombatWrapper;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

public class BankAction extends Node {

    private NpcResult result;
    private String status;
    private boolean running;

    private final static Area BANK_AREA = Area.rectangular(1638, 3943, 1640, 3945);

    public BankAction(){}

    @Override
    public boolean validate() {
        SceneObject target = SceneObjects.getNearest(6924);

        if(CurrentStatus.get() == Status.BANKING || CurrentStatus.get() == Status.IDLE) {
            if(!Players.getLocal().isMoving() && BANK_AREA.contains(Players.getLocal())) {
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
            Time.sleep(630, 890);
            if(Bank.isOpen()){
                if(!Inventory.isEmpty()){
                    Bank.depositInventory();
                    Time.sleepUntil(() -> Inventory.isEmpty(), Random.low(1826,4845));

                    return;
                }
                Time.sleep(1093, 1803);

                Bank.withdraw(Predicates.KNIFE,1);
                Time.sleepUntil(() -> Inventory.getCount(Predicates.KNIFE) == 1, Random.low(3134,4845));
                Time.sleep(1253, 1863);

                Bank.withdraw(Predicates.JUG_OF_WINE,10-Inventory.getCount(Predicates.JUG_OF_WINE));
                Time.sleepUntil(() -> Inventory.getCount(Predicates.JUG_OF_WINE) == 10, Random.low(3134,4845));
                Time.sleep(1106, 1653);

                if(Inventory.getCount(Predicates.JUG_OF_WINE) == 10 && Inventory.getCount(Predicates.KNIFE) == 1) {
                    CurrentStatus.set(Status.ENTERING);
                } else {
                    Bank.close();
                }
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
        return "Opening Bank";
    }
}
