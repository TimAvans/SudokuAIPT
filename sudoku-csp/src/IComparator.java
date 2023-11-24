public interface IComparator {

    /**
     * Calculates the heuristic value for a given constraint.
     * @param constraint
     * @return
     */
    public int getHeuristicValue(Constraint constraint);

}
