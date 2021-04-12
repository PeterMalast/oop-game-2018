package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable {
    private Animation ventilator;

    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("VENTILATOR_REPAIRED", Ventilator.class);


    public Ventilator() {
        super("ventilator");

        ventilator = new Animation("sprites/ventilator.png",16,16,1f);
        setAnimation(ventilator);
        ventilator.stop();
    }
    @Override
    public boolean repair() {
        if (ventilator != null) {
            ventilator.play();
            this.getScene().getMessageBus().publish(Ventilator.VENTILATOR_REPAIRED, this);

            return true;
        }
        return false;
    }
}
