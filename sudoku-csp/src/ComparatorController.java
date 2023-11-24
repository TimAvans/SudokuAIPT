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

    /**
     * Creates an instance of this class if there is none yet.
     * Otherwise return a new instance.
     * Ensures only 1 instance can ever exist.
     * @return Current instance of this class
     */
    public static ComparatorController Instance()
    {
        if (instance == null) {
            instance = new ComparatorController();
        }
        return instance;
    }

    /**
     * Returns the heuristic value of a constraint using the comparators given as parameter.
     * @param comparatorids
     * @param constraint
     * @return Heuristic value of the given Constraint and Comparators
     */
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
