package View;

import Controller.Controller;
import processing.core.PApplet;

import java.util.Arrays;

public class DarkView extends PApplet implements ViewInterface {

    final int X_POS = 0;
    final int Y_POS = 0;
    final int X_OFFSET = 20;
    final int Y_OFFSET = 20;

    final int SIZE_TILE = 80;
    final int SIZE_BORDER = 10;
    Controller controller = new Controller();

    int[] grid = controller.getGrid();
    int score = controller.getScore();
    boolean game = controller.getGame();


    public static void main(String[] args) {

        PApplet.runSketch(new String[]{""}, new DarkView());


    }

   public void show(int[] grid) {
        int edge_length = (int) (sqrt(grid.length));
        int i = 0;
        int X, Y;
        for (int y = 0; y < edge_length; y++) {
            Y = Y_POS + Y_OFFSET + SIZE_BORDER + y * (SIZE_TILE + SIZE_BORDER);
            for (int x = 0; x < edge_length; x++) {
                X = X_POS + X_OFFSET + SIZE_BORDER + x * (SIZE_TILE + SIZE_BORDER);
                // fill(color(179, 189, 214));
                fill(color(30 + log(grid[i] + 1) / log(2) * 10, 100, 100));
                rect(X, Y, SIZE_TILE, SIZE_TILE, 15);
                if (grid[i] != 0) {
                    fill(color(271, 0, 1));
                    text(grid[i], X + SIZE_TILE / 2 + 1, Y + SIZE_TILE / 2 + 1);
                }
                i++;
            }
        }
    }


    public void settings() {
        int X_SIZE = 2 * X_POS + 2 * X_OFFSET + SIZE_BORDER + 4 * (SIZE_TILE + SIZE_BORDER);
        int Y_SIZE = 2 * Y_POS + 2 * Y_OFFSET + SIZE_BORDER + 4 * (SIZE_TILE + SIZE_BORDER);
        size(X_SIZE, Y_SIZE);

    }

    public void setup() {
        textAlign(CENTER, CENTER);
        textSize(27);
        noStroke();
        background(color(0));
        colorMode(HSB, 360, 100, 100);

        controller.random_tile(grid);
        controller.random_tile(grid);
        show(grid);
        println(grid);
    }

    public void keyPressed() {
        int[] temp_grid = new int[grid.length];
        arrayCopy(grid, temp_grid);

        if (key == CODED && game) {
            switch (keyCode) {
                case LEFT:
                    score += controller.move(grid);
                    break;
                case RIGHT:
                    controller.rotate(grid, 2);
                    score += controller.move(grid);
                    controller.rotate(grid, 2);
                    break;
                case UP:
                    controller.rotate(grid);
                    score += controller.move(grid);
                    controller.rotate(grid, 3);
                    break;
                case DOWN:
                    controller.rotate(grid, 3);
                    score += controller.move(grid);
                    controller.rotate(grid);
            }
        }
        if (!Arrays.equals(grid, temp_grid)) {
            controller.random_tile(grid);
            show(grid);
            println("SCORE =", score);
        }
        if (controller.getIsGameOver(grid)) {
            game = false;
            println("GAME OVER. YOUR SCORE =", score);
        }
    }

    public void draw() {

    }


}
