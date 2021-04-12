package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private int zvysovanie;
    public PerpetualReactorHeating(int abc){
        zvysovanie = abc;
    }

    @Override
    public void execute(float deltaTime) {

        if(getActor() == null) return;
        super.getActor().increaseTemperature(zvysovanie);

    }
}
