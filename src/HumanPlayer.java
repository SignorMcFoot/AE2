import java.util.Scanner;

public class HumanPlayer extends Player{

    @Override
    public boolean move(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please provide an integer between 1 and 6: ");
        int dice = sc.nextInt();
        int finalPosition = board.getBoardArray()[0][0].getPosition();                   //Position in which the player wins the game
        int throwPosition = getSquare().getPosition() + dice;                            //Temporary position right after throw, without consideration for Delta
        System.out.printf("The player %s threw %d%n", this.toString(), dice);            //Purely cosmetic

        board.reference(getSquare().getPosition()).getPlayers().remove(this);         //Removing the player reference from the List of Players on the square
        board.reference(getSquare().getPosition()).setPlayer(null);                      //Resetting the reference on the square as player leaves it

        if (throwPosition >= board.getBoardArray()[0][0].getPosition()) {                //Decided that if the throw exceeds the final position on board, the player wins anyways
            setSquare(board.reference(finalPosition));
        } else if (board.reference(throwPosition).getDelta() == 0) {                     //Delta is set to 0 by default in each Square object
            setSquare(board.reference(throwPosition));
        } else {                                                                         //Delta is present and the throw is not game winning
            if(board.reference(throwPosition).getDelta() > 0 ) {
                System.out.println(String.format(("Player %s stepped on a ladder! Go up!"),this.toString()));
            }else{
                System.out.println(String.format(("Player %s stepped on a snake! Go down!"),this.toString()));
            }
            setSquare(board.reference(throwPosition + board.reference(throwPosition).getDelta()));
        }
        return getSquare().getPosition() == finalPosition;
    }
}
