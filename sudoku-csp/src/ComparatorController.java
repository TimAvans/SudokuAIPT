import java.util.HashMap;
import java.util.List;

public class ComparatorController {
    private static ComparatorController instance;
    private HashMap<String, Comparator> comparators;

    private ComparatorController() {
        comparators = new HashMap<String,Comparator>();
        comparators.put("mrvc", new MinimumRemainingValueComparator());
        comparators.put("dgc", new DegreeComparator());
    }

    public static ComparatorController Instance()
    {
        if (instance == null) {
            instance = new ComparatorController();
        }
        return instance;
    }

    public int getHeuristicValue(List<String> comparatorids, Constraint constraint)
    {
        int heuristicValue = 0;
        for (String string : comparatorids) {
            if (comparators.containsKey(string)) {
                heuristicValue += comparators.get(string).getHeuristicValue(constraint);
            }
        }
        return heuristicValue;
    }
}
