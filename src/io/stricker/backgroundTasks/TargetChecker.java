package io.stricker.backgroundTasks;

import io.stricker.CombatStore;
import io.stricker.config.Config;
import io.stricker.framework.BackgroundTaskExecutor;
import org.rspeer.runetek.adapter.scene.Npc;
import io.stricker.wrappers.CombatWrapper;

public class TargetChecker {

    public TargetChecker() {
        BackgroundTaskExecutor.submit(checkTargetHealth, 100);
    }

    private Runnable checkTargetHealth = () -> {
        Npc npc = CombatStore.getCurrentTargetNpc();
        if(npc == null) {
            CombatStore.setCurrentTarget(Config.isLooting() ? null : CombatWrapper.findTarget(false));
            return;
        }
        if(npc.getHealthPercent() <= 0) {
            CombatStore.setCurrentTarget(Config.isLooting() ? null : CombatWrapper.findTarget(false));
        }
    };

}
