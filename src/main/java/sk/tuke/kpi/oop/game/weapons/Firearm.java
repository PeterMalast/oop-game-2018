package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int maxAmmo;
    private int ammo;
    private int superAmmo;

    public Firearm(int ammo){
        this.ammo = ammo;
        this.maxAmmo = ammo;
        this.superAmmo = 0;
    }

    public Firearm(int ammo, int maxAmmo) {
        this.maxAmmo = maxAmmo;
        this.ammo = ammo;
        this.superAmmo =0;
    }

    public int getAmmo(){
        return this.ammo;
    }
    public int getSuperAmmo(){
        return this.superAmmo;
    }

    public void reload(int newAmmo){
        this.ammo+=newAmmo;
        if(this.ammo>this.maxAmmo){
            this.ammo=maxAmmo;
        }
    }

    public void reloadSuperAmmo(int superAmmo){
        this.superAmmo += superAmmo;

    }

    protected abstract Fireable createBullet();

    public Fireable fire(){
        if (this.getAmmo() > 0){
            this.ammo --;
            return new Bullet();
        }
        return null;
    }
}
