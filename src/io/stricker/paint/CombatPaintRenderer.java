package io.stricker.paint;

import io.stricker.CombatStore;
import io.stricker.models.NpcResult;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.movement.Movement;
import io.stricker.wrappers.CombatWrapper;

import java.awt.*;
import java.util.HashSet;

public class CombatPaintRenderer {

    public static void onRenderEvent(Graphics g) {
        Npc target = CombatStore.getCurrentTargetNpc();
        HashSet<Npc> targetingMeSet = CombatStore.getTargetingMe();
        g.setColor(Color.yellow);
        if (target != null) {
            target.getPosition().outline(g);
        }
        NpcResult next = CombatStore.getNextTarget();
        if(next != null) {
            g.setColor(Color.PINK);
            next.getNpc().getPosition().outline(g);
        }
        for (Npc npc : targetingMeSet) {
            if (npc == null)
                continue;
            g.setColor(Color.red);
            npc.getPosition().outline(g);
        }

    }

}
