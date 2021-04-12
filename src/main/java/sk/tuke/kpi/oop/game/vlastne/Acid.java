package sk.tuke.kpi.oop.game.vlastne;


import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;
import sk.tuke.kpi.oop.game.Facade.Facade;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Usable;

public class Acid extends AbstractActor implements Usable<Alive>, Facade {

    public Acid() {
        super("snack");
        setAnimation(new Animation("sprites/energy1.png", 16, 16));

    }


    public void useWith(Alive actor) {
        if ((this.getScene() != null) && (actor != null)) {
            if(actor.getHealth().getValue() <= 50) {
                actor.getHealth().drain(10);
                this.getScene().removeActor(this);
            }

        }
    }

    /*
    public void collidedWithRipley(){
        for(Actor actor: getScene()){
            if(actor instanceof Ripley)
            ((Ripley) actor).setSpeed(1);

        }
    }


    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::collidedWithRipley)).scheduleOn(this);
    }
    */
    @Override
    public void draw() {
        getAnimation().setTint(Color.GRAY);


    }
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }



}

