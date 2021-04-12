package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;
import sk.tuke.kpi.oop.game.Facade.Facade;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Energy extends AbstractActor implements Usable<Alive>, Facade {

    public Energy() {
        super("energy");
        setAnimation(new Animation("sprites/energy.png", 16, 16));
    }

    @Override
    public void useWith(Alive actor) {
        if ((this.getScene() != null) && (actor != null)) {
            if(actor.getHealth().getValue() < 100) {
                actor.getHealth().refill(50);
                draw();
                //    this.getScene().removeActor(this);
            }
        }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }

    @Override
    public void draw() {
        getAnimation().setTint(Color.GRAY);


    }
}
