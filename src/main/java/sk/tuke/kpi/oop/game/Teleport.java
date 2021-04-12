package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;

public class Teleport extends AbstractActor {

    private Teleport tel;
    private boolean prisiel;
    // private Animation lift;

    public Teleport(Teleport teleport)
    {
        super("teleport");
        Animation lift = new Animation("sprites/lift.png", 48, 48, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(lift);
        tel = teleport;
        prisiel=false;
    }

    public boolean setDestination(Teleport destinationTeleport)
    {
        if(destinationTeleport!=this){
            tel = destinationTeleport;
            return true;
        }
        return false;
    }

    public Point getDestination(){

        return tel.getPosition();
    }

    public void teleportPlayer()
    {
        Player player = (Player) getScene().getFirstActorByName("ellen");

        int tx, ty;
        tx=tel.getPosX();
        ty=tel.getPosY();
        tx=tx+getWidth()/2;
        ty=ty+getHeight()/2;
        tx=tx-player.getWidth()/2;
        ty=ty-player.getHeight()/2;
        tel.setPrisiel(true);
        player.setPosition(tx, ty);

    }

    @Override
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::activate)).scheduleOn(this);
        //new PerpetualReactorHeating(1).scheduleOn(this);
    }
    public void activate()
    {
        Player player = (Player) getScene().getFirstActorByName("ellen");

        if(tel==null) {
            return;
        }

        if(prisiel==false)
        {
            int px,py, tx,ty;
            px = player.getPosX();
            py = player.getPosY();
            tx = this.getPosX();
            ty = this.getPosY();
            px = px + player.getWidth()/2;
            py = py + player.getHeight()/2;
            if(px>=tx && px<=tx+getWidth() && py>=ty && py<=ty+getHeight() && tel!=null)
                teleportPlayer();
            System.out.println("tu je bazmeg2");
        }
        if(prisiel==true && player.intersects(this)==false)
            prisiel=false;
    }
    void setPrisiel(boolean b)
    {
        prisiel=b;
    }
}

