package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;

public class LockedDoor extends Door {

    private boolean zamknute;

    public LockedDoor(Orientation orientation) {
        super(orientation);
        this.zamknute = false;
    }


    public void lock(){
        this.zamknute = true;
        this.close();
    }

    public void unlock(){
        this.zamknute = false;
        this.open();
    }

    public boolean isLocked() {
        return zamknute;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.lock();
    }
}
