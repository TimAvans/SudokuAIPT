import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomateResults { // Automating and collecting results of Sudoku solving with different comparators.

    /**
     * Instance of automate results to give back. 
     * Makes sure there will always be only 1 instance of this class
     */
    private static AutomateResults instance;
    
    /**
     * Private ctor
     */
    private AutomateResults() {
    }

    /**
     * Creates an instance of this class if there is none yet.
     * Otherwise return a new instance.
     * Ensures only 1 instance can ever exist.
     * @return Current instance of this class
     */
    public static AutomateResults Instance() 
    {
        if (instance == null) {
            instance = new AutomateResults();
        }
        return instance;
    }

    /**
     * Run Sudoku solving games with different comparators for each file in the specified directory.
     * @param directory
     */
    public void runGames(String directory) 
    {
        List<String> files = getFiles(directory);
        for (String file : files) {
            int iterations1 = run(file, Arrays.asList("minimumremainingvalue")); 
            int iterations2 = run(file, Arrays.asList("degree"));
            int iterations3 = run(file, Arrays.asList("minimumremainingvalue", "degree"));
            int iterations4 = run(file, Arrays.asList("off")); 

            saveInformation(file, iterations1, Arrays.asList("minimumremainingvalue"));
            saveInformation(file, iterations2, Arrays.asList("degree"));
            saveInformation(file, iterations3, Arrays.asList("minimumremainingvalue", "degree"));
            saveInformation(file, iterations4, Arrays.asList("off"));
        }
    }

    /**
     * Run Sudoku solving game with the specified comparators and display results.
     * @param filePath
     * @param comparators
     * @return Amount of iterations it took to complete the sudoku
     */
    public int run(String filePath, List<String> comparators)
    {
        Game game = new Game(new Sudoku(filePath), comparators); // Create a new Game instance with a Sudoku puzzle and specified comparators
        game.showSudoku();
        int iterations = game.solve(); // Run the solving algorithm and get the number of iterations performed
        if (game.validSolution()){
            System.out.println("Solved! " + iterations);
        }
        else{
            System.out.println("Could not solve this sudoku :( " + iterations);
        }
        game.showSudoku();
        return iterations;
    }

    /**
     * Save information about the Sudoku solving results to a CSV file.
     * @param filepath
     * @param iterations
     * @param comparators
     */
    public void saveInformation(String filepath, int iterations, List<String> comparators) 
    {
        try {
            File file = new File(filepath); // Create a File object representing the Sudoku puzzle file
            FileWriter writer = new FileWriter("results.csv", true); // Create a FileWriter to append information to the results.csv file
            PrintWriter pWriter = new PrintWriter(writer); // Create a PrintWriter to write formatted text to the FileWriter
            String line = file.getName() + "; Comparators: "; // Build a line of information including file name, comparators, and iteration count
            for(int i = 0; i < comparators.size(); i++)
            {               
                line += comparators.get(i) + (i == comparators.size() - 1 ? "; " : ", ");
            }
            line += "Amount of iterations: " + iterations;
            pWriter.println(line); // Write the formatted line to the results.csv file
            pWriter.close();
        } catch (Exception e) { //TODO: MOET HIER NOG WAT IN???
        }
    }

    /**
     * Get a list of file paths for Sudoku puzzle files in the specified directory.
     * @param directory
     * @return
     */
    private List<String> getFiles(String directory) 
    {
        File direc = new File(directory);
        File[] files = direc.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt")); 
        List<String> filenames = new ArrayList<String>();
        for (File file : files) {
            filenames.add(file.getAbsolutePath());
        }
        return filenames;
    }
}
