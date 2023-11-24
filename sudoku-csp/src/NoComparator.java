public class NoComparator implements IComparator{
    
    /**
     * Return 0 because we do not want to use the heuristic values in the queue when using this Comparator.
     */
    @Override
    public int getHeuristicValue(Constraint constraint) {
        return 0; 
    }
}
