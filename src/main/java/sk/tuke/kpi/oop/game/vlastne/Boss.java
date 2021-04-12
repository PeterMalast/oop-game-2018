package sk.tuke.kpi.oop.game.vlastne;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.gamelib.graphics.OverlayDrawing;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Health;

import java.util.Timer;
import java.util.TimerTask;

public class Boss extends AbstractActor implements Movable, Alive, Enemy {


    private Health health;
    private Behaviour<? super Boss> behaviour;

    public Boss() {
        super("boss");
        setAnimation(new Animation("sprites/monster.png", 72, 128, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        health = new Health(300);
    }

    public Boss(int healthv,Behaviour<? super Boss> behaviour){
        setAnimation(new Animation("sprites/monster.png", 72, 128, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.behaviour = behaviour;
        health = new Health(healthv);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new While<>(action -> true,
            new ActionSequence<>(
                new Invoke<>(this::aliveActor),
                new Wait<>(0)
            )).scheduleOn(this);
        getHealth().onExhaustion(()->
            scene.removeActor(this));
        if (behaviour!=null){
            behaviour.setUp(this);
        }
    }

    public void aliveActor(){
        Scene scene = getScene();
        for(Actor actor : scene.getActors()){
            if(this.intersects(actor)&&(actor instanceof Alive) &&!(actor instanceof Enemy)){
                ((Alive) actor).getHealth().drain(5);
            }
        }
        if(getHealth().getValue() < 1){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);

                }
            },3*1000);




        }
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Health getHealth() {
        return health;
    }
}
