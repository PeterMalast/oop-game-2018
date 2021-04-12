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
import sk.tuke.kpi.gamelib.graphics.Color;
import sk.tuke.kpi.oop.game.Facade.Facade;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.items.Usable;

public class fire extends AbstractActor implements Usable<Alive> {
    public fire() {
        super("snack");
        setAnimation(new Animation("sprites/small_explosion.png", 16, 16));

    }

    public void aliveActor(){
        Scene scene = getScene();
        for(Actor actor : scene.getActors()){
            if(this.intersects(actor)&&(actor instanceof Alive) &&!(actor instanceof Enemy) &&!(actor instanceof AlienWizard)){
                ((Alive) actor).getHealth().exhaust();
            }
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new While<>(action -> true,
            new ActionSequence<>(
                new Invoke<>(this::aliveActor),
                new Wait<>(0)
            )).scheduleOn(this);

    }

    @Override
    public void useWith(Alive actor) {

    }

    public Class<Alive>getUsingActorClass() {
        return Alive.class;
    }

}
