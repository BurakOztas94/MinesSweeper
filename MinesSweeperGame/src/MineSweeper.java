import java.util.Random;
import java.util.Scanner;

//5.değerlendirme maddesi
public class MineSweeper {

    // 1. değerlendirme maddesi
    char[][] map;
    char[][] mines;
    int rows;
    int columns;
    boolean isGameOver;
    int totalCells;

    public MineSweeper (int rows , int columns)
        {
            this.rows = rows;
            this.columns = columns;
            this.map = new char[rows][columns];
            this.mines = new char[rows][columns];
            this.isGameOver = false;
            this.totalCells = rows * columns;
            setMap ();
            placeMines ();
        }

    private void setMap ()          //Creating map here         //6.Değerlendirme maddesi
    {
        for (int i = 0;i < rows;i++)
            {
                for (int j = 0;j < columns;j++)
                    {
                        map[i][j] = '-';
                    }
            }
    }

    private void placeMines ()                      //Mine placer function          //8.Değerlendirme maddesi
    {
        Random random = new Random ();          //New random class to place mines
        int numMines = totalCells / 4;          //Mine amount calculated here
        while (numMines > 0)
            {
                int row = random.nextInt (rows);
                int col = random.nextInt (columns);
                if ( mines[row][col] != '*' )
                    {
                        mines[row][col] = '*';
                        numMines--;
                    }
            }
    }

    int countAdjacentMines (int row , int columns)                      //6.Değerlendirme maddesi
    {                       //This code block controls every column and rows if selected cell has mine or not
        int count = 0;
        // Count mines while searching neighbouring cells
        for (int i = row - 1;i <= row + 1;i++)
            {
                for (int j = columns - 1;j <= columns + 1;j++)
                    {
                        // Is it valid neighbouring cell, it controls them
                        if ( isValidCell (i , j) && hasMine (i , j) )
                            {
                                count++;
                            }
                    }
            }

        return count;
    }

    // The method: The cells that you are looking at are valid or not
    boolean isValidCell (int row , int columns)                           //6.Değerlendirme maddesi
        {
            return row >= 0 && row < this.rows && columns >= 0 && columns < this.columns;
        }

    // The code block that checks whether it is a mine or not.
    boolean hasMine (int row , int columns)                 //6.Değerlendirme maddesi
        {
            return mines[row][columns] == '*';
        }

    void printControlMineBoard ()                          //6.Değerlendirme maddesi
    {
        System.out.println ("===============\n\tMine Map\n===============");        //Header for mines map
        for (int i = 0;i < rows;i++)
            {

                for (int j = 0;j < columns;j++)
                    {
                        if ( mines[i][j] == '*' )
                            {
                                System.out.print ("* ");
                            }
                        else
                            {
                                System.out.print ("- ");
                            }
                    }
                System.out.println ();
            }
    }

    void printGamerBoard ()                                 //6.Değerlendirme maddesi
    {
        for (int i = 0;i < rows;i++)
            {
                for (int j = 0;j < columns;j++)
                    {
                        System.out.print (map[i][j] + " ");
                    }
                System.out.println ();
            }
    }

    boolean hasWon ()                                       //6.Değerlendirme maddesi
    {
        int unopenedMines = 0;                          //
        for (int i = 0;i < rows;i++)
            {
                for (int j = 0;j < columns;j++)
                    {
                        if ( map[i][j] == '-' && mines[i][j] != '*' )
                            {
                                unopenedMines++;
                            }
                    }
            }
        return unopenedMines == 0;
    }

    public void playGame ()                                         //6.Değerlendirme maddesi
    {
        Scanner scanner = new Scanner (System.in);
        printControlMineBoard (); //    Shows first map
        System.out.println ("===================");      //Separator for first map and user map
        while (!isGameOver)                             //11.değerlendirme maddesi
            {
                printGamerBoard ();
                System.out.print ("Please enter row : ");           //9.değerlendirme maddesi
                int row = scanner.nextInt ();
                System.out.print ("Please enter columns : ");       //9.değerlendirme maddesi
                int column = scanner.nextInt ();

                if ( row < 0 || row >= rows || column < 0 || column >= columns )
                    {
                        System.out.println ("Invalid coordinates. Try again.");    // 10. değerlendirme maddesi
                        continue;
                    }
                if ( mines[row][column] == '*' )                        //13.değerlendirme maddesi
                    {
                        isGameOver = true;
                        System.out.println ("Game Over!!");         //15.değerlendirme maddesi
                    }
                else
                    {
                        int count = countAdjacentMines (row , column);             //14.değerlendirme maddesi
                        map[row][column] = (char) ( count + '0' );
                        if ( hasWon () )
                            {
                                printGamerBoard ();
                                System.out.println ("Congrats, You won!");      //15.değerlendirme maddesi
                                isGameOver = true;
                            }
                    }
            }
        scanner.close ();
    }
}


/*EXTRA EXPLANATORY FOR CURIOUS PEOPLE*/

  /*                                GENERAL EXPLANATION
        MineSweeper(int rows, int columns): This is the constructor of the MineSweeper class. It initializes a new
        instance of the game with the specified number of rows and columns. It sets up the game board and randomly
        places mines on it.
              private void setMap(): This method is responsible for initializing the game board (map) with empty
              cells represented by '-'. It iterates through each cell in the map and sets it to '-'.
        private void placeMines(): This method is used to randomly place mines (*) on the game board. The number
        of mines is determined based on the total number of cells, and it ensures that mines are distributed randomly
         without duplicates.
        int countAdjacentMines(int row, int columns): This method counts the number of mines adjacent to a given
        cell, specified by the row and columns parameters. It checks the cells in the vicinity (including diagonals)
        and counts how many of them contain mines. The count is returned as an integer.
        boolean isValidCell(int row, int columns): This method checks whether a given cell, specified by the row and
        columns parameters, is within the bounds of the game board. It returns true if the cell is valid (within bounds)
         and false otherwise.
        boolean hasMine(int row, int columns): This method checks whether a given cell, specified by the row and columns
         parameters, contains a mine (*) or not. It returns true if the cell contains a mine and false if it does not.
        void printControlMineBoard(): This method prints the mine map, showing the locations of mines (*) and empty
         cells (-). It is used to display the initial state of the game board with mines hidden.
        void printGamerBoard(): This method prints the player's view of the game board. It shows the cells that have
        been revealed by the player, including numbers that represent the count of adjacent mines.
        boolean hasWon(): This method checks if the player has won the game. It does this by counting the number of
         unopened cells that do not contain mines. If all non-mine cells are opened, the player is declared the winner, and it returns true.
        public void playGame(): This method starts the game loop where players take turns to select cells and uncover
         them. It prompts the player for row and column inputs, checks the validity of the inputs, reveals the cell,
          and updates the game state. It continues until the game is over, either due to a mine being hit or the player winning.
        These methods collectively manage the game logic of a basic Minesweeper game, including setting up the board,
         placing mines, checking for adjacent mines, validating cell coordinates, revealing cells, and determining the game outcome.
        */


        /*  EXPLANATION OF COUNT ADJACENT MINES METHOD DETAILED
        Here's an explanation of each part of the code block:
    int countAdjacentMines(int row, int columns): This is a method that takes two parameters, row and columns,
    representing the coordinates of the cell you want to count adjacent mines for. It returns an integer value,
    which is the count of adjacent mines.
    {: This is the start of the method block.
    int count = 0;: This initializes a variable count to 0. This variable will be used to keep track of how many
    adjacent mines are found.
    for (int i = row - 1; i <= row + 1; i++): This is an outer loop that iterates through rows surrounding the cell.
     It starts one row above the current cell (row - 1) and goes down to one row below the current cell (row + 1).
    for (int j = columns - 1; j <= columns + 1; j++): This is an inner loop that iterates through columns surrounding
     the cell. It starts one column to the left of the current cell (columns - 1) and goes to one column to the right
     of the current cell (columns + 1).
    if (isValidCell(i, j) && hasMine(i, j)): Within the nested loops, this condition checks whether the cell at
     coordinates (i, j) is a valid cell (within the boundaries of the game grid) and whether it contains a mine.
     If both conditions are met, it means an adjacent mine is found.
    count++;: If an adjacent mine is found, the count variable is incremented by 1.
    }: This closes the inner loop.
    }: This closes the outer loop.
    return count;: Finally, the method returns the count variable, which represents the number of adjacent mines.
    Additionally, there are two helper methods used within this code block:
    boolean isValidCell(int row, int columns): This method checks if the cell at (row, columns) is within
    the boundaries of the game grid (this.rows and this.columns represent the dimensions of the grid).
    It returns true if the cell is valid and false if it's out of bounds.
    boolean hasMine(int row, int columns): This method checks if the cell at (row, columns)
     contains a mine (*). It returns true if there is a mine in the cell and false if there isn't.
        */
