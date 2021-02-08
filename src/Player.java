import java.util.Random;

public class Player {
    private char ID;
    private Square square;

    public char getID() {
        return ID;
    }

    public void setID(char ID) {
        this.ID = ID;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public boolean move( Board board) {
        Random r = new Random();
        int dice = r.nextInt(6) + 1;
        System.out.printf("The player %s threw %d%n",this.toString(),dice);

        int finalPosition = board.getBoardItself()[0][0].getPosition();
        int newPosition  = getSquare().getPosition() + dice;
        board.reference(getSquare().getPosition()).setPlayer(null);
        if (newPosition >= board.getBoardItself()[0][0].getPosition()) {
            setSquare(board.reference(finalPosition));
        } else if(board.reference(newPosition).getDelta() == 0) {
            setSquare(board.reference(newPosition));
        }else{
            setSquare(board.reference(newPosition+board.reference(newPosition).getDelta()));
        }
        return getSquare().getPosition() == finalPosition;
    }


    @Override
    public String toString() {
        return String.valueOf(ID);
    }
}
