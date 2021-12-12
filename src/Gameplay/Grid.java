package Gameplay;

/* This class contains the logic and methods that
 * allow the game to function properly. Two
 * arrays are created during program execution.
 * Tables are filled with question marks and one
 * from them receives mines. The computer can
 * see the table with the mines and follow the progress
 * of the player, while the player only sees the board
 * question marks.
 */

public class Grid {
    public String[][] displayGrid;
    public String[][] realGrid;
    public boolean gameOver = false;
    public boolean victory = false;
    public int width=9;
    public int height=9;
    public int mines=10;

    final private String unknown = " ? ";
    final private String mine = " * ";
    final private String empty = "   ";

    //Generate 2 grids
    public void generateGrid() {
        displayGrid = new String[height + 2][width + 2];
        realGrid = new String[height + 2][width + 2];
        for (int x = 0; x < displayGrid.length; x++) {
            for (int y = 0; y < displayGrid[0].length; y++) {
                if ((x == 0 || x == displayGrid.length - 1) || (y == 0 || y == displayGrid[0].length - 1)) {
                    displayGrid[x][y] = empty;
                    realGrid[x][y] = empty;
                } else {
                    displayGrid[x][y] = unknown;
                    realGrid[x][y] = unknown;
                }
            }
        }
    }

    //Generate mines at random position
    public void generateMines() {
        for (int i = 0; i < mines; i++) {
            while (true) {
                int x, y;   
                x = (int) (width * Math.random());
                y = (int) (height * Math.random());

                //Put mine into the grid
                if (x >= 1 && x <= width) {
                    if (y >= 1 && y <= height) {
                        //Check if a mine is already there
                        if (!realGrid[x][y].equals(mine)) {
                            realGrid[x][y] = mine;
                            break;
                        }
                    }
                }
            }
        }
    }

    //Update
    public void update() {
        printGame(displayGrid);
        System.out.println();
    }

    //Print the grid
    public static void printGame(String[][] s) {
        System.out.print("    ");
        //Print the index of x
        for (int i = 1; i < 10; i++) {
            System.out.print("  " + i + " ");
        }
        for (int x = 1; x < s.length - 1; x++) {
            for (int y = 0; y < s[0].length; y++) {
                if (y > 0)
                    System.out.print("|");
                else {
                    System.out.println();
                    System.out.print(x);
                }
                System.out.print(s[x][y]);
            }
        }
        System.out.println();
    }
    public void open(int x, int y) {
        if (realGrid[x][y].equals(unknown)) {
            realGrid[x][y] = empty;
            displayGrid[x][y] = empty;
            countMines();
            exploreGrand(x, y);
        } else if (realGrid[x][y].equals(mine)) {
            gameOver = true;
            victory = false;
        }
    }
    //Count mines around and put it in the case
    public void countMines() {
        for (int x = 1; x < realGrid.length - 1; x++) {
            for (int y = 1; y < realGrid.length - 1; y++) {
                if (realGrid[x][y].equals(empty)) {
                    int nums = 0;
                    for (int i = (x - 1); i <= (x + 1); i++) {
                        for (int j = (y - 1); j <= (y + 1); j++) {
                            if (realGrid[i][j].equals(mine)) {
                                nums++;
                            }
                        }
                    }
                    displayGrid[x][y] = " " + nums + " ";
                }
            }
        }
    }

    //Open other case if there is no mine in it
    public void exploreGrand(int x, int y) {
        if (displayGrid[x][y].equals(" 0 ")) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if ((i != x) || (j != y)) open(i, j);
                }
            }
        }
    }

    //Check if the player win
    public void isVictory() {
        int cases = 0;
        for (int i = 0; i < realGrid.length; i++) {
            for (int j = 0; j < realGrid.length; j++) {
                if (displayGrid[i][j].equals(unknown))
                    cases++;
            }
        }
        if (cases != mines)
            victory = false;
        else {
            victory = true;
            gameOver = true;
        }
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean getVictory() {
        return victory;
    }

    //Print the real grid
    public void onEnd() {
        printGame(realGrid);
    }
}
