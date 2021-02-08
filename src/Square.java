public class Square {
    private int position;
    private int delta = 0;
    private Player player;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        if(getDelta() != 0 && getPlayer()!= null)
            return String.format("%3s%3d(%3d)",getPlayer().toString(),getPosition(),getDelta());
        else if(getPlayer()!=null)
            return String.format("%3s%3d(   )",getPlayer(),getPosition());
        else
            return String.format("   %3d(   )",getPosition());
    }
}
