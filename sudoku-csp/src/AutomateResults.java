import java.io.File;
import java.io.FilenameFilter;
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

    }

    private List<String> getFiles(String directory)
    {
        File dir = new File(directory);
        //File[] files = dir.listFiles((directory, name) -> name.toLowerCase().endsWith(".txt"));

        return null;
    }
}
