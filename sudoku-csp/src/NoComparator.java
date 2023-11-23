public class NoComparator implements IComparator{
    //return a high value if there are more finalized fields. 
    @Override
    public int getHeuristicValue(Constraint constraint) {
        return 0; // Return a high value if there are more finalized fields (always 0 in this implementation)
    }
}
