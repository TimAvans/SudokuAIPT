public class App {
    public static void main(String[] args) throws Exception {
        String workingDir = System.getProperty("user.dir");
        
        //start(workingDir +  "\\sudoku-csp\\Sudoku1.txt");
        AutomateResults.Instance().runGames(workingDir + "\\sudoku-csp\\");
    }

    /**
     * Start AC-3 using the sudoku from the given filepath, and reports whether the sudoku could be solved or not, and how many steps the algorithm performed
     * @param filePath
     */
    public static void start(String filePath){
        Game game1 = new Game(new Sudoku(filePath));
        game1.showSudoku();
        int iterations = game1.solve();
        if (game1.validSolution()){
            System.out.println("Solved! " + iterations);
        }
        else{
            System.out.println("Could not solve this sudoku :( " + iterations);
        }
        game1.showSudoku();
    }
}
