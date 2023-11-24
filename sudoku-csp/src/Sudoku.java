import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Sudoku {
  private Field[][] board;

  /**
   * Constructor that initializes the Sudoku puzzle based on a file.
   * @param filename
   */
  Sudoku(String filename) { 
    this.board = readsudoku(filename);
  }

  /**
   * Return string representation of the sudoku board.
   */
  @Override
  public String toString() { // Generates a string representation of the Sudoku puzzle for display.
    String output = "╔═══════╦═══════╦═══════╗\n";
		for(int i=0;i<9;i++){ // Iterate through each row of the Sudoku puzzle
      if(i == 3 || i == 6) {
		  	output += "╠═══════╬═══════╬═══════╣\n";
		  }
      output += "║ ";
		  for(int j=0;j<9;j++){ // Iterate through each column of the Sudoku puzzle
		   	if(j == 3 || j == 6) {
          output += "║ ";
		   	}
         output += board[i][j] + " "; // Append the value of the current field to the output
		  }
		  
      output += "║\n";
	  }
    output += "╚═══════╩═══════╩═══════╝\n";
    return output;
  }

  /**
	 * Reads sudoku from file
	 * @param filename
	 * @return 2d int array of the sudoku
	 */
	public static Field[][] readsudoku(String filename) {
		assert filename != null && filename != "" : "Invalid filename"; // Ensure that the filename is not null or an empty string
		String line = "";
		Field[][] grid = new Field[9][9];
		try {
		FileInputStream inputStream = new FileInputStream(filename); // Open the file for reading
        Scanner scanner = new Scanner(inputStream);
        for(int i = 0; i < 9; i++) { // Iterate through each row of the Sudoku puzzle
        	if(scanner.hasNext()) {
        		line = scanner.nextLine();
        		for(int j = 0; j < 9; j++) { // Iterate through each character in the line
              int numValue = Character.getNumericValue(line.charAt(j));  // Get the numeric value of the character
              if(numValue == 0) { // Create a new Field object based on the numeric value
                grid[i][j] = new Field();
              } else if (numValue != -1) {
                grid[i][j] = new Field(numValue);
        			}
        		}
        	}
        }
        scanner.close();      
    }
		catch (FileNotFoundException e) {  // Handle the case where the file is not found
			System.out.println("error opening file: "+filename);
		}
    addNeighbours(grid);
		return grid;
	}

  /**
   * Adds a list of neighbours to each field.
   * @param grid
   */
  private static void addNeighbours(Field[][] grid) 
  {
    for(int y = 0; y < grid.length; y++)
    {
      for(int x = 0; x < grid[y].length; x++)
      {
        //Make a temporary list of horizontal neighbours
        List<Field> tempList = new LinkedList<Field>(Arrays.asList(grid[y]));
        
        //Remove the current field
        tempList.remove(x);
        
        //Add the vertical neighbours
        for(int i = 0; i < grid.length; i++ )
        {
          if (y != i) {
            tempList.add(grid[i][x]);
          }
        }

        // Add cells from the same 3x3 subgrid
        int subgridStartX = (x / 3) * 3;
        int subgridStartY = (y / 3) * 3; 

        for (int i = subgridStartY; i < subgridStartY + 3; i++) {
            for (int j = subgridStartX; j < subgridStartX + 3; j++) {
                if (i != y || j != x) {
                  if (!tempList.contains(grid[i][j])) {
                    tempList.add(grid[i][j]);
                  }            
                }
            }
        }

        //Set the temporary list as the neighbours
        grid[y][x].setNeighbours(tempList);
      }
    } 
  }

  /**
	 * Generates fileformat output
	 */
	public String toFileString(){
    String output = "";
    for (int i = 0; i < board.length; i++) { // Iterate through each row of the Sudoku puzzle
      for (int j = 0; j < board[0].length; j++) { // Iterate through each column of the Sudoku puzzle
        output += board[i][j].getValue(); // Append the value of the current field to the output
      }
      output += "\n";
    }
    return output;
	}

  /**
   * Gets the 2D array representing the Sudoku puzzle board.
   * @return
   */
  public Field[][] getBoard(){ 
    return board;
  }
}
