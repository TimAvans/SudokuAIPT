import java.util.LinkedList;

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

  /**
   * 
   * @param grid
   */
  public boolean ac3(Field[][] grid)
  {
    LinkedList<Constraint> queue = createConstraints();
    while (!queue.isEmpty()) {
      Constraint constraint = queue.poll();
      
      if (constraint.checkConstraint()) {
        if (constraint.field1.getDomainSize() == 0) {
          return false;
        }
        for (Field neighbour : constraint.field1.getOtherNeighbours(constraint.field2)) {
          if (neighbour.getValue() == 0) {
            Constraint cstr = new Constraint(neighbour, constraint.field1);
            if (!queue.contains(cstr)) {
              queue.add(cstr);
            }
          }
        }
        queue.add(constraint);
      }
    }
    return true;
  } 


  /**
   * 
   */
  public LinkedList<Constraint> createConstraints()
  {
    LinkedList<Constraint> queue = new LinkedList<Constraint>();
    Field[][] grid = sudoku.getBoard();
    for(int i = 0; i < grid.length; i++)
    {
      for(int j = 0; j < grid[i].length; j++)
      {
        for (Field field : grid[i][j].getNeighbours()) {
          queue.add(new Constraint(field, grid[i][j]));
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
