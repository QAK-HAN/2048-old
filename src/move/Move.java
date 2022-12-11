package move;

import java.util.Arrays;
import java.util.Random;

/**
 *Game 2048 by @denkspuren,
 * New BSD License: <a href="http://opensource.org/licenses/BSD-3-Clause">...</a>
 * This is an implementation of 2048 without animation effects.
 * */



public class Move {


    Random random = new Random();

    public int[] grid = new int[16]; // default values are 0
    public int score = 0; // score shows  alle merged value
    public boolean game = true; // true shows if the game is started


    /**
     * Looks if there are free slots (looks for value 0 for the grid)
     * @param grid: transfer the grid
     * @return number of free slots
     */
    int free_slots(int[] grid) {
        int i = 0;
        for (int val : grid) {
            if (val == 0) i++;
        }
        return i;
    }


    /**
     * rotates the grid once counterclockwise
     * @param grid: transfer the grid
     */
    public void rotate(int[] grid) {
        int[] temp_grid = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            temp_grid[i + 12 - (i % 4) * 5 - (i / 4) * 3] = grid[i];
        }
        System.arraycopy(temp_grid, 0, grid, 0, 16);
    }

    /**
     * rotates the grid n times counterclockwise
     * n is given by the keypressed method from the view
     */
    public void rotate(int[] grid, int n) {
        for (int i = 1; i <= (n % 4); i++) {
            rotate(grid);
        }
    }

    /**
     * shift the tile in the right grid position
     * @param grid: transfer the grid
     */
    void shift(int[] grid) {
        int offset = 0;
        for (int i = 0; i < grid.length; i++) {
            if (i % 4 == 0) {
                offset = 0;
            }
            if (grid[i] == 0) {
                offset++;
            } else if (offset > 0) {
                grid[i - offset] = grid[i];
                grid[i] = 0;
            }
        }
    }

    /** random_tile
     * checks if there are free slots and fills one position with a two or four
     * @param grid: transfer the grid
     *
     */
    public void random_tile(int[] grid) {
        int pos, val;
        pos = (int) (random.nextInt(0, free_slots(grid)));
        val = random.nextInt(0, 1) < 0.9 ? 2 : 4;
        insert_tile(grid, pos, val);
    }

    /**
     * @param grid: transfer the grid
     * @return updated score
     * if two tiles with the same value move together there value gets add up
     */
    int merge(int[] grid) {
        int score = 0;
        for (int i = 0; i < grid.length; i++) {
            if (i % 4 < 3) {
                if (grid[i] > 0 && grid[i] == grid[i + 1]) {
                    grid[i] += grid[i + 1];
                    grid[i + 1] = 0;
                    score += grid[i];
                }
            }
        }
        return score;
    }

    /** insert_tile
     * inserts the random tile at a random spot
     * @param grid: transfer the grid
     * @param n
     * @param val: 2 or 4
     */
    void insert_tile(int[] grid, int n, int val) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] == 0) {
                if (n == 0) {
                    grid[i] = val;
                    break;
                }
                n--;
            }
        }
    }

    /**
     * moves tiles with the method shift and update score
     * @param grid: transfer the grid
     * @return recent game score
     */
    public int move(int[] grid) {
        int score = 0;
        shift(grid);
        score = merge(grid);
        shift(grid);
        return score;
    }
    /**
     * checks if next move is possible or the game gets game over
     * @param grid:transfer the grid
     * @return checks if temp_grid and grid are the same
     */
    public boolean is_game_over(int[] grid) {
        int[] temp_grid = new int[grid.length];
        System.arraycopy(grid, 0, temp_grid, 0, grid.length);
        for (int i = 1; i <= 4; i++) {
            move(temp_grid);
            rotate(temp_grid);
        }
        return Arrays.equals(temp_grid, grid);
    }

    public int[] getGrid() {
        return grid;
    }

}