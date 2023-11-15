import java.util.PriorityQueue;

public class Game {
  private Sudoku sudoku;

  Game(Sudoku sudoku) {
    this.sudoku = sudoku;
  }

  public void showSudoku() {
    System.out.println(sudoku);
  }

  /**
   * Implementation of the AC-3 algorithm
   * 
   * @return true if the constraints can be satisfied, else false
   */
  public boolean solve() {
    Field[][] grid = sudoku.getBoard();
    ac3(grid);

    return true;
  }
  //TODO: Constraint voor verticaal, horizontaal en de 3x3 grid.

  /**
   * 
   * @param grid
   */
  public boolean ac3(Field[][] grid)
  {
    PriorityQueue<Constraint> queue = createConstraints();


    return false;
  } 

  /**
   * 
   */
  public PriorityQueue<Constraint> createConstraints()
  {
    PriorityQueue<Constraint> queue = new PriorityQueue<>();
    Field[][] grid = sudoku.getBoard();
    for(int i = 0; i < grid.length; i++)
    {
      for(int j = 0; j < grid[i].length; j++)
      {
        for (Field field : grid[i][j].getNeighbours()) {
          queue.add(new Constraint(grid[i][j], field));
        }
      }
    }
    return queue;
  }


  /**
   * Checks the validity of a sudoku solution
   * 
   * @return true if the sudoku solution is correct
   */
  public boolean validSolution() {
    // TODO: implement validSolution function
    return true;
  }
}
