package Herzi;

import java.util.Arrays;
import java.util.Random;

public class Herzi {

    Random random = new Random();

    public int[] grid = new int[16]; // default values are 0
    public int score = 0;
    public boolean game = true;


    int free_slots(int[] grid) {
        int i = 0;
        for (int val : grid) {
            if (val == 0) i++;
        }
        return i;
    }

/*  0  1  2  3     3  7 11 15     +12 +7  +2  -3  -> -5
    4  5  6  7     2  6 10 14     +9  +4  -1  -6  -> -5
    8  9 10 11     1  5  9 13     +6  +1  -4  -9  -> -5
   12 13 14 15     0  4  8 12     +3  -2  -7  -12 -> -5
                                   |   |   |   |
                                   V   V   V   V
                                  -3  -3  -3  -3
 */

    public void rotate(int[] grid) {
        int[] temp_grid = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            temp_grid[i + 12 - (i % 4) * 5 - (i / 4) * 3] = grid[i];
        }
        System.arraycopy(temp_grid, 0, grid, 0, 16);
    }

    public void rotate(int[] grid, int n) {
        for (int i = 1; i <= (n % 4); i++) {
            rotate(grid);
        }
    }

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

    public void random_tile(int[] grid) {
        int pos, val;
        pos = (int) (random.nextInt(0, free_slots(grid)));
        val = random.nextInt(0, 1) < 0.9 ? 2 : 4;
        insert_tile(grid, pos, val);
    }

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

    public int move(int[] grid) {
        int score = 0;
        shift(grid);
        score = merge(grid);
        shift(grid);
        return score;
    }

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