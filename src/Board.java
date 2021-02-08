
public class Board {
    public static void main(String[] args) {
        Board b = new Board(10,5);
        b.createBoard();
        Player p = new Player();
        Player p1 = new Player();
        p.setID('k');
        p1.setID('x');
        b.addPlayer(p);
        b.addPlayer(p1);
        b.takeTurn();
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    private final int rows;
    private final int columns;
    private Square[][] boardItself;
    private Player[] playerList;

    public Square[][] getBoardItself() {
        return boardItself;
    }

    public void setBoardItself(Square[][] boardItself) {
        this.boardItself = boardItself;
    }

    public Player[] getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Player[] playerList) {
        this.playerList = playerList;
    }

    private void createBoard(){ // Initialising the board
        setBoardItself(new Square[rows][columns]);
        int counter =0;
        int lastPosition = 0;
        for (int r = 0; r < rows; r++) {
            if (r % 2 == 0) {
                for (int c = 0; c < columns; c++) { //Basic row construction
                    Square sq = new Square();
                    sq.setPosition((rows*columns-1)-counter);
                    counter++;
                    getBoardItself()[r][c] = sq;
                    lastPosition=sq.getPosition();
                }
            } else {
                for (int c = 0; c < columns; c++) {     //Second loop for inverting every second row
                    Square sq = new Square();
                    sq.setPosition(lastPosition-columns+c);
                    counter++;
                    getBoardItself()[r][c] = sq;
                }
            }
        }
    }

    private String helper(int position,int r,int c){ //Requirement to put the position at -1 if coordinates are given
        String answer = "Position not found";
        if(position!= -1){
        for ( int i = 0; i < rows; ++i ) {
            for ( int j = 0; j < columns; ++j ) {
                if ( getBoardItself()[i][j].getPosition() == position ) {
                    answer = String.format("This position is in row %d and column %d",i+1,j+1);
                }
            }
        }}else{
            answer = String.format("The position on given coordinates is %d ",r*c);
        }
        return answer;
    }

    private void addPlayer(Player p){
        if(getPlayerList() == null){
            setPlayerList(new Player[1]);
        }else{
            Player[] newPlayer = new Player[getPlayerList().length+1];
            System.arraycopy(getPlayerList(),0,newPlayer,0,getPlayerList().length);
            setPlayerList(newPlayer);
        }
        p.setSquare(getBoardItself()[rows-1][0]);
        getPlayerList()[getPlayerList().length-1] = p;
    }

    public Square reference(int position){
        Square sq = null;
        for (Square[] s: getBoardItself()) {
            for (Square q: s) {
                if(q.getPosition() == position){
                    sq = q;
                }
            }
        }
        return sq;
    }

    public void takeTurn(){
        boolean winner = false;
        Player trueMVP = null;
        for (Player p: getPlayerList()) {
            this.reference(p.getSquare().getPosition()).setPlayer(p);
        }
        while(!winner){
            for (Player p: getPlayerList()){
                winner = p.move(this);
                this.reference(p.getSquare().getPosition()).setPlayer(p);
                System.out.println(this.toString());
                if(winner){
                    trueMVP = p;
                    break;
                }
            }
        }
        System.out.println(String.format("The winner is %s",trueMVP.toString()));
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) { //Basic row construction
                    board.append(getBoardItself()[r][c].toString());
                }
            board.append('\n');
        }
        return board.toString().toString();
    }
}