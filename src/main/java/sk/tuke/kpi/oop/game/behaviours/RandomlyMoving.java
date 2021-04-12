package sk.tuke.kpi.oop.game.behaviours;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.oop.game.Movable;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.Invoke;


import java.util.Random;

public class RandomlyMoving implements Behaviour<Movable> {
    private Disposable disposable;
    private int[] uhol_pohybu = new int[9];

    public RandomlyMoving() {
        for (int l = 0, a = 0; a < 360; l++, a += 45){ this.uhol_pohybu[l] = a;
        }
    }

    @Override
    public void setUp(Movable actor) {
        if(actor==null)return;

        if (actor.getScene() != null) {
            new Loop<>(
                new ActionSequence<>(
                    new Wait<>(1),
                    new Invoke<>(() -> { if (this.disposable != null) this.disposable.dispose();
                        this.disposable = new Move<>(Direction.fromAngle(this.uhol_pohybu[Math.abs(new Random().nextInt(this.uhol_pohybu.length))]), Float.MAX_VALUE).scheduleOn(actor);
                    })
                )
            ).scheduleOn(actor);
        }
    }
}
