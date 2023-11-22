import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;

public class Game {
  private Sudoku sudoku;
  private PriorityQueue<Constraint> queue;

  //TODO: Make it autocomplete all sudokus with different settings for heuristics
  Game(Sudoku sudoku) {
    this.sudoku = sudoku;
    queue = createConstraints(Arrays.asList("mrvc", "dgc"));
  }

  Game(Sudoku sudoku, List<String> comparators) {
    this.sudoku = sudoku;
    queue = createConstraints(comparators);
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
  public boolean ac3(Field[][] grid) {
    int iterations = 0;
    while (!queue.isEmpty()) {
      iterations++;
      revise(queue.poll());
    }
    System.out.println("Complexity of AC3: " + iterations);
    return true;
  }

  private void revise(Constraint constraint)
  {
    if (constraint.checkConstraint()) {
      for (Field neighbour : constraint.field1.getOtherNeighbours(constraint.field2)) {
          addToQueue(neighbour, constraint.field1);
      }
    }
  }

  private void addToQueue(Field neighbour, Field currentField1)
  {
    if (neighbour.getValue() == 0) {
      Constraint cstr = new Constraint(neighbour, currentField1);
      if (!queue.contains(cstr)) {
        queue.add(cstr);
      }
    }
  }

  /**
   *  TODO: CleanUp
   */
  public PriorityQueue<Constraint> createConstraints(List<String> comparators) {
    PriorityQueue<Constraint> queue = new PriorityQueue<>(Comparator.comparingInt(cstr -> ComparatorController.Instance().getHeuristicValue(comparators, cstr)));
    Field[][] grid = sudoku.getBoard();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        Field field = grid[i][j];
        if (field.getValue() == 0) {
          for (Field neighbour : grid[i][j].getNeighbours()) {
            queue.add(new Constraint(field, neighbour));
          }
        }
      }
    }
    return queue;
  }

  /**
   * Checks the validity of a sudoku solution
   * Checks for each field if its current value exists
   * @return true if the sudoku solution is correct
   */
  public boolean validSolution() {
    Field[][] grid = sudoku.getBoard();
    for (int i = 0; i < grid.length; i++) {
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
