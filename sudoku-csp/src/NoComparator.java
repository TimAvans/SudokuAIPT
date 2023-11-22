public class NoComparator implements Comparator{
    //In other words return a high value if there are more finalized fields. 
    @Override
    public int getHeuristicValue(Constraint constraint) {
        return 0;
    }
}
