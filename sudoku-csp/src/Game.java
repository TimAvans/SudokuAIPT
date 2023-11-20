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
    int complexity = 0;
    LinkedList<Constraint> queue = createConstraints();
    while (!queue.isEmpty()) {
      complexity++;;
      Constraint constraint = queue.poll();
      
      if (constraint.checkConstraint()) {
        for (Field neighbour : constraint.field1.getOtherNeighbours(constraint.field2)) {
          if (neighbour.getValue() == 0) {
            Constraint cstr = new Constraint(neighbour, constraint.field1);
            if (!queue.contains(cstr)) {
              queue.add(cstr);
            }
          }
        }
      }
    }
    System.out.println("Complexity of AC3: " + complexity);
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
    Field[][] grid = sudoku.getBoard();
    for(int i = 0; i < grid.length; i++)
    {
      for (Field field : grid[i]) {
        if (field.getValue() == 0) {
          return false;
        }
        for (Field neighbour : field.getNeighbours()) {
          if (neighbour.getValue() == field.getValue()) {
            return false;
          } 
        }

      }
    }
    return true;
  }
}
