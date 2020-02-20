package io.stricker.nodes.wintertodt;

import io.stricker.config.Predicates;
import io.stricker.framework.Node;
import io.stricker.models.NpcResult;
import io.stricker.status.CurrentStatus;
import io.stricker.status.Status;
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
            if(Bank.isOpen()){
//                if(Inventory.getCount(Predicates.JUG) != 0){
//                    Bank.depositAll(Predicates.JUG);
//                    Time.sleep(1356, 1505);
//                }
//                if(Inventory.getCount(Predicates.SUPPLY_CRATE) != 0){
//                    Bank.depositAll(Predicates.SUPPLY_CRATE);
//                    Time.sleep(1256, 1705);
//                }
                Bank.depositInventory();
                Time.sleepUntil(() -> Inventory.isEmpty(), Random.high(3134,4845));
                Bank.withdraw(Predicates.KNIFE,1);
                Time.sleepUntil(() -> Inventory.getCount(Predicates.KNIFE) == 1, Random.high(3134,4845));
                Bank.withdraw(Predicates.JUG_OF_WINE,10-Inventory.getCount(Predicates.JUG_OF_WINE));
                Time.sleepUntil(() -> Inventory.getCount(Predicates.JUG_OF_WINE) == 10, Random.high(3134,4845));

//                if(Inventory.getCount(Predicates.JUG_OF_WINE) < 10){
//                    Bank.withdraw(Predicates.JUG_OF_WINE,10-Inventory.getCount(Predicates.JUG_OF_WINE));
//                }
                Time.sleep(1253, 1863);

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
