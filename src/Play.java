public class Play {
    public static void main(String[] args) {

        Board b = new Board(10,5);
        b.createBoard();
        Player p1 = new Player();
        Player p2 = new HumanPlayer();
        p1.setID('x');
        p2.setID('d');
        b.reference(48).setDelta(-11);
        b.reference(38).setDelta(5);
        b.reference(23).setDelta(12);
        b.reference(12).setDelta(-4);
        b.addPlayer(p1);
        b.addPlayer(p2);
        b.takeTurn();

    }
}
