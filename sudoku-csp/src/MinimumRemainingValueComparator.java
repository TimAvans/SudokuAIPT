

public class MinimumRemainingValueComparator implements Comparator{

    //In other words return a high value if there are more finalized fields. 
    @Override
    public int getHeuristicValue(Constraint constraint) {
        if (constraint.field2.getValue() != 0) {
            return -1;
        }
        return constraint.field2.getDomainSize();
    }
}
