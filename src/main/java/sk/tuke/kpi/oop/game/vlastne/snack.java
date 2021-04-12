package sk.tuke.kpi.oop.game.vlastne;;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Color;
import sk.tuke.kpi.oop.game.Facade.Facade;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.oop.game.characters.Alive;


public class snack extends AbstractActor implements Usable<Alive>, Facade {
    public snack() {
        super("snack");
        setAnimation(new Animation("sprites/energy1.png", 16, 16));

    }

    @Override
    public void useWith(Alive actor) {
        if ((this.getScene() != null) && (actor != null)) {
            if(actor.getHealth().getValue() < 100) {
                actor.getHealth().refill(20);
                draw();
                //collidedWithRipley();
                //    this.getScene().removeActor(this);
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
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }

    @Override
    public void draw() {
        getAnimation().setTint(Color.GRAY);


    }

}




