package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import java.util.Objects;

public class Move<T extends Movable> implements Action<T> {
    private T actor;
    private boolean done;
    private boolean temp=true;
    private Direction direction;
    private float trvanie;



    public void stop(){
        done = true;
        if (this.getActor() != null){
            this.getActor().stoppedMoving();
        }
    }
    public Move(Direction direction, float trvanie) {
        this.direction = direction;
        this.trvanie = trvanie;
    }

    @Override
    public boolean isDone() {
        return done;
    }
    @Nullable
    @Override
    public T getActor() {
        return this.actor;
    }
    @Override
    public void reset() {
        done = false;
        this.temp = true;
    }
    @Override
    public void setActor(T t) {
        this.actor = t;
    }
    @Override
    public void execute(float deltaTime) {
            if ((this.getActor() != null)){
                if (this.temp) {
                    this.getActor().startedMoving(this.direction);
                    this.temp = false;
                }
                this.getActor().getAnimation().setRotation(this.direction.getAngle());
                int Xpozicia = this.getActor().getPosX();
                int Ypozicia = this.getActor().getPosY();
                this.getActor().setPosition(this.getActor().getPosX() + (this.direction.getDx() * this.getActor().getSpeed()), Ypozicia);
                if (Objects.requireNonNull(this.getActor().getScene()).getMap().intersectsWithWall(this.getActor())) {
                    this.actor.collidedWithWall();
                    this.getActor().setPosition(Xpozicia, this.getActor().getPosY());
                }
                this.getActor().setPosition(this.getActor().getPosX(), this.getActor().getPosY() + (this.direction.getDy() * this.getActor().getSpeed()));
                if (Objects.requireNonNull(this.getActor().getScene()).getMap().intersectsWithWall(this.getActor())) {
                    this.actor.collidedWithWall();
                    this.getActor().setPosition(this.actor.getPosX(), Ypozicia);
                }
                this.trvanie-=deltaTime;
                if(trvanie <=0){
                    this.stop();
                }
        }
    }
}
