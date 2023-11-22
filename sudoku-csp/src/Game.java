import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;

public class Game {
  private Sudoku sudoku;
  private PriorityQueue<Constraint> queue;

  Game(Sudoku sudoku) {
    this.sudoku = sudoku;
    createConstraints(Arrays.asList("minimumremainingvalue", "degree")); // Create constraints with default comparators ("minimumremainingvalue" and "degree")
  }

  Game(Sudoku sudoku, List<String> comparators) {
    this.sudoku = sudoku;
    createConstraints(comparators); // Create constraints with the specified comparators
  }

  public void showSudoku() {
    System.out.println(sudoku);
  }

  /**
   * Implementation of the AC-3 algorithm
   * @return true if the constraints can be satisfied, else false
   */
  public int solve() {
    Field[][] grid = sudoku.getBoard();
    return ac3(grid);
  }

  //#region AC3
  /**
   * 
   * @param grid
   */
  public int ac3(Field[][] grid) {
    int iterations = 0;
    while (!queue.isEmpty()) { // Continue processing constraints until the priority queue is empty
      iterations++;
      revise(queue.poll()); // Process the next constraint and revise domains
    }
    System.out.println("Complexity of AC3: " + iterations); // Print the complexity of the AC-3 algorithm
    return iterations;
  }

  private void revise(Constraint constraint)
  {
    if (constraint.checkConstraint()) { // Check if the constraint holds
      for (Field neighbour : constraint.field1.getOtherNeighbours(constraint.field2)) { // Iterate through neighbors of field1 excluding field2
          addToQueue(neighbour, constraint.field1); // Add the neighbor to the priority queue if its value is unknown
      }
    }
  }

  private void addToQueue(Field neighbour, Field currentField1)
  {
    if (neighbour.getValue() == 0) { // Check if the neighbor's value is unknown (0)
      Constraint cstr = new Constraint(neighbour, currentField1); // Create a new constraint with the neighbor and currentField1
      if (!queue.contains(cstr)) {  // Add the constraint to the priority queue if it is not already present
        queue.add(cstr);
      }
    }
  }
  //#endregion
  
  //#region Constraint creation
  /**
   * 
   */
  public PriorityQueue<Constraint> createConstraints(List<String> comparators) { // Create a priority queue of constraints sorted by heuristic values using specified comparators
    queue = new PriorityQueue<Constraint>(Comparator.comparingInt(cstr -> ComparatorController.Instance().getHeuristicValue(comparators, cstr)));
    Field[][] grid = sudoku.getBoard();// Get the Sudoku board
    for (int i = 0; i < grid.length; i++) { // Iterate through each row in the grid
      for (int j = 0; j < grid[i].length; j++) { // Iterate through each field in the row
        Field field = grid[i][j];
        addConstraintsToQueue(field); // Add constraints to the queue for the current field
      }
    }
    return queue;
  }

  private void addConstraintsToQueue(Field field)
  {
    if (field.getValue() == 0) { // Check if the field's value is unknown (0)
      for (Field neighbour : field.getNeighbours()) { // Iterate through each neighbor of the field
        queue.add(new Constraint(field, neighbour)); // Add a new constraint to the priority queue
      }
    }
  }

  //#endregion
  
  //#region Solution check
  /**
   * Checks the validity of a sudoku solution
   * Checks for each field if its current value exists
   * @return true if the sudoku solution is correct
   */
  public boolean validSolution() {
    Field[][] grid = sudoku.getBoard(); // Iterate through each row in the grid
    for (int i = 0; i < grid.length; i++) {
      for (Field field : grid[i]) { // If the field value is 0, the puzzle is not solved
        if (field.getValue() == 0) {
          return false;
        }
        if (!checkNeighbours(field)) { // Check if the value of the field is unique among its neighbors
          return false;
        }
      }
    }
    return true;
  }

  private boolean checkNeighbours(Field field)
  {
    for (Field neighbour : field.getNeighbours()) { // Iterate through each neighbor of the field
      if (neighbour.getValue() == field.getValue()) { // If the neighbor has the same value as the field, the puzzle is not solved
        return false;
      }
    }
    return true;
  }
  //#endregion

}
