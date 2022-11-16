package Controller;

import Herzi.Herzi;

public class Controller implements ControllerInterface {

    Herzi herzi = new Herzi();

    public boolean getIsGameOver(int[] grid) {return herzi.is_game_over(grid);}

    public int getScore() {return herzi.score;}

    public boolean getGame() {return herzi.game;}

    public int[] getGrid() {return herzi.getGrid();}

    public int move(int[] grid) {return herzi.move(grid);}

    public void random_tile(int[] grid) {herzi.random_tile(grid);}

    public void rotate(int[] grid, int n) {herzi.rotate(grid, n);}

    public void rotate(int[] grid) {herzi.rotate(grid);}

}
