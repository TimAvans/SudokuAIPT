import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomateResults {

    private static AutomateResults instance;
    
    private AutomateResults() {
    }

    public static AutomateResults Instance()
    {
        if (instance == null) {
            instance = new AutomateResults();
        }
        return instance;
    }

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

    public int run(String filePath, List<String> comparators)
    {
        Game game = new Game(new Sudoku(filePath), comparators);
        game.showSudoku();
        int iterations = game.solve();
        if (game.validSolution()){
            System.out.println("Solved! " + iterations);
        }
        else{
            System.out.println("Could not solve this sudoku :( " + iterations);
        }
        game.showSudoku();
        return iterations;
    }

    public void saveInformation(String filepath, int iterations, List<String> comparators)
    {
        try {
            File file = new File(filepath);
            FileWriter writer = new FileWriter("results.csv", true);
            PrintWriter pWriter = new PrintWriter(writer);
            String line = file.getName() + "; Comparators: ";
            for(int i = 0; i < comparators.size(); i++)
            {               
                line += comparators.get(i) + (i == comparators.size() - 1 ? "; " : ", ");
            }
            line += "Amount of iterations: " + iterations;
            pWriter.println(line);
            pWriter.close();
        } catch (Exception e) {
        }
    }

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
