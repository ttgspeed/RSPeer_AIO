package io.stricker;

import io.stricker.framework.Node;
import io.stricker.nodes.plough.PloughZone;
import io.stricker.nodes.plough.PloughNode;
import io.stricker.nodes.food.EatNode;
import io.stricker.nodes.food.GetFoodNode;
import io.stricker.nodes.idle.IdleNode;
import io.stricker.nodes.loot.BuryBones;
import io.stricker.nodes.loot.DepositLootNode;
import io.stricker.nodes.loot.LootNode;
import io.stricker.nodes.progressive.ProgressionChecker;

public class NodeSupplier {

    public final Node EAT = new EatNode();
    public final Node GET_FOOD = new GetFoodNode();
    public final Node IDLE = new IdleNode();
    public final Node DEPOSIT_LOOT = new DepositLootNode();
    public final Node LOOT = new LootNode();
    public final Node PROGRESSION_CHECKER = new ProgressionChecker();
    public final Node BURY_BONES = new BuryBones();
    public final Node BACK_TO_FIGHT = new PloughZone();
    public final Node FIGHT = new PloughNode();

}
