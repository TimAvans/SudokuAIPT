import java.util.HashMap;
import java.util.List;

public class ComparatorController {
    private static ComparatorController instance;
    private HashMap<String, IComparator> comparators; // Map of comparator names to their corresponding implementations

    // Private constructor to initialize the ComparatorController with default comparators.
    private ComparatorController() {
        comparators = new HashMap<String, IComparator>();
        comparators.put("minimumremainingvalue", new MinimumRemainingValueComparator());
        comparators.put("degree", new DegreeComparator());
        comparators.put("off", new NoComparator());
    }

    public static ComparatorController Instance() // Get the solitary instance of the ComparatorController
    {
        if (instance == null) {
            instance = new ComparatorController();
        }
        return instance;
    }

    public int getHeuristicValue(List<String> comparatorids, Constraint constraint)
    {
        int heuristicValue = 0;
        for (String string : comparatorids) { // Iterate through each comparator name in the list
            if (comparators.containsKey(string)) { // Check if the comparator is present in the map
                heuristicValue += comparators.get(string).getHeuristicValue(constraint);  // Accumulate heuristic values from each comparator
            }
        }
        return heuristicValue;
    }
}
