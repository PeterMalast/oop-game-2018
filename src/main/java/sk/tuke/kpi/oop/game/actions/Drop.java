package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;



public class Drop<A extends Actor> extends AbstractAction<Keeper<A>> {
    @Override
    public void execute(float deltaTime) {
        if (getActor().getContainer().peek() != null && getActor() != null && getActor().getScene() != null) {
            getActor().getScene().addActor(getActor().getContainer().peek(), getActor().getPosX(), getActor().getPosY());
            getActor().getContainer().remove(getActor().getContainer().peek());
        }
        setDone(true);
    }
}
