import java.util.ArrayList;

public class Square {
    private int position;
    private int delta = 0;
    private Player player;
    private final ArrayList<Player> players = new ArrayList<>();

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
        if (player != null && !getPlayers().contains(player)) {
            getPlayers().add(getPlayer());
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private String playerIds() {
        String IDs = "";
        for (Player p : getPlayers()) {
            IDs += p.toString();
        }
        return IDs;
    }

    @Override
    public String toString() {
        if (getDelta() != 0 && !getPlayers().isEmpty()) {
            return String.format("%3s%3d(%3d)", playerIds(), getPosition(), getDelta());

        } else if (!getPlayers().isEmpty()) {
            return String.format("%3s%3d(   )", playerIds(), getPosition());

        } else if (getDelta() == 0) {
            return String.format("   %3d(   )", getPosition());

        } else {
            return String.format(("   %3d(%3d)"), getPosition(), getDelta());
        }
    }
}
