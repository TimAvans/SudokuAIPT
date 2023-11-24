
// This class implements the Comparator interface to define a custom comparison strategy based on the heuristiv value calculated from a contraint object. 
public class MinimumRemainingValueComparator implements IComparator{

    /**
     * Heuristic which returns -1 if the Constraint's field2 has no remaining values.
     * Or returns the domainsize as heuristic value if there are remaining values.
     */
    @Override
    public int getHeuristicValue(Constraint constraint) { 
        if (constraint.field2.getValue() != 0) { // Checks if the value of the second field is not equal to 0 (not finalized)
            return -1; // Return a high negative value, indicating that more finalized fields are preferable
        }
        return constraint.field2.getDomainSize(); // Returns the size of the domain of the second field as the heuristic value. 
    }
}
