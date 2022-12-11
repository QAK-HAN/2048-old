

package Controller;

import move.Move;

public class Controller implements ControllerInterface {

    Move Move = new Move();

    /**
     * @param grid This Array Object is given into @return to be used
     * @return game.is_game_over(grid). The given Object
     */
    public boolean getIsGameOver(int[] grid) {return Move.is_game_over(grid);}


    /**
     *
     * @return score (int)
     */
    public int getScore() {return Move.score;}

    /**
     *
     * @return game (boolean)
     */
    public boolean getGame() {return Move.game;}

    /**
     *
     * @return grid (int[])
     */
    public int[] getGrid() {return Move.getGrid();}


    /**
     *
     * @param grid ()
     * @return grid (moved)
     */
    public int move(int[] grid) {return Move.move(grid);}

    /**
     *
     * @param grid (of random tile)
     */
    public void random_tile(int[] grid) {Move.random_tile(grid);}

    /**
     * A Method which gets called by the view.
     * @param grid is an Array used to call a Method from Model
     * @param n is an int which is used to call the same Method.
     */
    public void rotate(int[] grid, int n) {Move.rotate(grid, n);}

    /**
     * A Method without a return value from Model.
     * @param grid
     */
    public void rotate(int[] grid) {Move.rotate(grid);}

}
