
public class Board {
    private final int rows;
    private final int columns;
    private Square[][] boardArray;
    private Player[] playerList;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Square[][] getBoardArray() {
        return boardArray;
    }

    public void setBoardArray(Square[][] boardArray) {
        this.boardArray = boardArray;
    }

    public Player[] getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Player[] playerList) {
        this.playerList = playerList;
    }

    public void createBoard() { // Initialising the board
        setBoardArray(new Square[rows][columns]);
        int counter = 0;
        int lastPosition = 0;    //This is required in the row reversion
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < columns; c++) { //Basic row construction
                Square sq = new Square();
                if (r % 2 == 0) {
                    sq.setPosition((rows * columns - 1) - counter); //Basic row filling based on an incrementing counter
                    lastPosition = sq.getPosition();
                } else {
                    sq.setPosition(lastPosition - columns + c);     //Reverse row filling based on the last position of the previous row
                }
                counter++;
                getBoardArray()[r][c] = sq;
            }
        }
    }

    public String helper(int position, int r, int c) { //Requirement to put the position at -1 if coordinates are given, not perfect
        String answer = "Position not found";
        if (position != -1) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    if (getBoardArray()[i][j].getPosition() == position) {
                        answer = String.format("This position is in row %d and column %d", i + 1, j + 1);
                    }
                }
            }
        } else {
            answer = String.format("The position on given coordinates is %d ", r * c);
        }
        return answer;
    }

    public void addPlayer(Player p) {
        if (getPlayerList() == null) {
            setPlayerList(new Player[1]);
        } else {
            Player[] newPlayer = new Player[getPlayerList().length + 1];
            System.arraycopy(getPlayerList(), 0, newPlayer, 0, getPlayerList().length);
            setPlayerList(newPlayer);
        }
        p.setSquare(getBoardArray()[rows - 1][0]);
        getPlayerList()[getPlayerList().length - 1] = p;
        p.getSquare().setPlayer(p);
        System.out.println("A new Player has arrived!");
        System.out.println(this.toString());
    }

    public Square reference(int position) {
        Square sq = null;
        for (Square[] s : getBoardArray()) {
            for (Square q : s) {
                if (q.getPosition() == position) {
                    sq = q;
                }
            }
        }
        return sq;
    }

    public void takeTurn() {
        boolean winAchieved = false;
        Player winner = null;
        for (Player p : getPlayerList()) {
            this.reference(p.getSquare().getPosition()).setPlayer(p);
        }
        while (!winAchieved) {
            for (Player p : getPlayerList()) {
                winAchieved = p.move(this);
                this.reference(p.getSquare().getPosition()).setPlayer(p);
                System.out.println(this.toString());
                if (winAchieved) {
                    winner = p;
                    break;
                }
            }
        }
        System.out.println(String.format("The winner is %s", winner.toString()));
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) { //Basic row construction
                board.append(getBoardArray()[r][c].toString());
            }
            board.append('\n');
        }
        return board.toString();
    }
}