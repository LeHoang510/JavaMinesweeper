package Gameplay;

import java.util.Scanner;

public class Game {
    public static void play() {
        Scanner sc = new Scanner(System.in);
        int command;
        while (true) {
            System.out.println("Welcome to Minesweeper");
            System.out.println("1. Start");
            System.out.println("2. Credit");
            System.out.println("3. Quit");
            command = sc.nextInt();
            if (command == 1) {
                int x, y;
                Grid grid = new Grid();
                grid.generateGrid();
                grid.generateMines();
                grid.update();
                while (true) {
                    if (grid.getGameOver() && grid.getVictory()) {
                        System.out.println("*** Congratulation ***");
                        grid.onEnd();
                        break;
                    } else if (grid.getGameOver()) {
                        grid.onEnd();
                        System.out.println();
                        System.out.println("End game");
                        break;
                    } else if (!grid.getGameOver()) {
                        System.out.print("Enter x:");
                        y = sc.nextInt();
                        System.out.print("Enter y:");
                        x = sc.nextInt();
                        grid.open(x, y);
                        grid.isVictory();
                        grid.exploreGrand(x, y);
                        grid.update();
                    }
                }
            } else if (command == 2) {
                System.out.println("\nNGUYEN Le Hoang\n");
            } else if (command == 3) {
                System.out.println("\nBye\n");
                break;
            }
        }
    }
}