package io.stricker;

import io.stricker.config.Config;
import io.stricker.framework.BackgroundTaskExecutor;
import io.stricker.framework.Node;
import io.stricker.framework.NodeManager;
import io.stricker.nodes.plough.PloughAction;
import io.stricker.nodes.plough.PloughNode;
import io.stricker.nodes.plough.PloughZone;
import io.stricker.nodes.wintertodt.*;
import io.stricker.paint.CombatPaintRenderer;
import io.stricker.paint.ScriptPaint;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.listeners.DeathListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.listeners.TargetListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.runetek.event.types.DeathEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.event.types.TargetEvent;
import org.rspeer.runetek.providers.subclass.GameCanvas;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.awt.*;

@ScriptMeta(name = "Wintertodt", desc = "Do things", developer = "speed", category = ScriptCategory.FIREMAKING)
public class Fighter extends Script implements RenderListener, TargetListener, DeathListener, ChatMessageListener {

    private NodeManager manager;
    private ScriptPaint paint;
    private StopWatch runtime;
    private boolean setupComplete;

    public static int getLoopReturn() {
        return Random.high(262, 1385);
    }

    public StopWatch getRuntime() {
        return runtime;
    }

    public NodeManager getManager() {
        return manager;
    }

//    public NodeSupplier supplier;
//
//    public NodeSupplier getSupplier() {
//        return supplier;
//    }
    private Node PLOUGH_ZONE = new PloughZone();
    private Node PLOUGH_NODE = new PloughNode();
    private Node PLOUGH_ACTION = new PloughAction();

    private Node BANK_ACTION = new BankAction();
    private Node TRAVERSE_BANK = new TraverseBank();
    private Node ENTER_ACTION = new EnterAction();
    private Node TRAVERSE_ENTER = new TraverseEnter();
    private Node LEAVE_ACTION = new LeaveAction();
    private Node TRAVERSE_LEAVE = new TraverseLeave();
    private Node EAT_ACTION = new EatAction();
    private Node TRAVERSE_BRUMA = new TraverseBruma();
    private Node BRUMA_ACTION = new BrumaAction();

    @Override
    public void onStart() {
        setupComplete = false;
        try {
            super.onStart();
            //supplier = new NodeSupplier();
            manager = new NodeManager();
            setupNodes();
            runtime = StopWatch.start();
            paint = new ScriptPaint(this);
            setupComplete = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupNodes() {
        manager
                .submit(TRAVERSE_BANK)
                .submit(BANK_ACTION)
                .submit(TRAVERSE_ENTER)
                .submit(ENTER_ACTION)
                .submit(LEAVE_ACTION)
                .submit(TRAVERSE_LEAVE)
                .submit(EAT_ACTION)
                .submit(TRAVERSE_BRUMA)
                .submit(BRUMA_ACTION);
    }

    @Override
    public int loop() {
        if (setupComplete) {
            if(!GameCanvas.isInputEnabled()) {
                GameCanvas.setInputEnabled(true);
            }
            try {
                return manager.execute(getLoopReturn());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getLoopReturn();
    }

    @Override
    public void onStop() {
        try {
            BackgroundTaskExecutor.shutdown();
            manager.onScriptStop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public void notify(RenderEvent e) {
        Graphics g = e.getSource();
        try {
            if(manager != null) {
                paint.notify(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        g.setColor(Color.GREEN);

        Position p = Config.getStartingTile();
        if(p != null && Game.isLoggedIn()) {
            Point start = Projection.toMinimap(p);
            if (start != null) {
                int size = Config.getRadius() * 4;
                g.drawOval(start.x - (size / 2), start.y - (size / 2), size, size);
            }
            CombatPaintRenderer.onRenderEvent(g);
        }
    }

    @Override
    public void notify(TargetEvent e) {
    }

    @Override
    public void notify(DeathEvent e) {
        //CombatListener.onDeathEvent(e, supplier);
    }

    @Override
    public void notify(ChatMessageEvent e) {
    }

}
