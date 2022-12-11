package test;

import move.Move;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GameTest {
        @Test
        void should_be_game_over() {
            var game = new Move();

            assertTrue(
                   game.is_game_over(
                           new int[]{4,16,8,2,16,8,32,8,4,32,8,4,2,4,128,2}
                   )
            );

            assertFalse(
                    game.is_game_over(
                            new int[]{4,16,8,0,16,8,32,8,4,32,8,4,2,4,128,2}

                    )
            );
        }

        @Test
    void tiles_should_move() {
            var game = new Move();
            int[] grid = new int[]{0,2,0,2,0,0,0,0,0,0,0,0,0,0,0,0};
            assertNotEquals(game.score,game.move(grid));
        }





      @Test
    void should_start_with_two_tiles() {
        var game = new Move();
        game.random_tile(game.grid);
        game.random_tile(game.grid);
        int j = 0;
        for(int i = 0; i<game.grid.length; i++) {
            if(game.grid[i]!=0)
                j++;
        }
  assertEquals(j,2);
    }
}

  /*  @Test
    void should_start_with_two_tiles(){
        var game = new Move();
        assertEquals(8,game.grid.length);
    }
*/

