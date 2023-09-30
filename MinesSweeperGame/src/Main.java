import java.util.Scanner;

public class Main {
    public static void main (String[] args)
        {
            Scanner scanner = new Scanner (System.in);
            System.out.println ("Welcome to Mine Sweeper Game! ");
            System.out.print ("Rows Number : ");
            int rows = scanner.nextInt ();             //  User row and column taken by user
            System.out.print ("Column Number : ");
            int columns = scanner.nextInt ();

            if ( rows > 0 && columns > 0 && rows <= 100 && columns <= 100 )
                {   //The game is limited  2 to 100      //7.değerlendirme maddesi
                    MineSweeper game = new MineSweeper (rows , columns);
                    game.playGame ();
                }
            else
                {
                    System.out.println ("Please enter rows and columns numbers 1 between 100 ");
                }
            scanner.close ();
        }
}