package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<T extends Actor> extends AbstractAction<T> {
    private Usable<T> usable;
    public Use(Usable<T> usable) {
        this.usable = usable;
    }
    public Disposable scheduleOnIntersectingWith(Actor workingactor) {
        Scene scene = workingactor.getScene();
        if (scene == null) return null;
        Class<T> useactor = usable.getUsingActorClass();
        for (Actor actor :scene) {
            if (workingactor.intersects(actor)  &&  useactor.isInstance(actor)) {
                return this.scheduleOn(useactor.cast(actor));
            }
        }
        return null;
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull T actor) {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }

    @Override
    public void execute(float deltaTime) {
        if (this.getActor() != null){
            System.out.print("executing Use action !");
            this.usable.useWith(this.getActor());
        }
        this.setDone(true);
    }
}
