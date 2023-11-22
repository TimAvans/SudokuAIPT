

public class DegreeComparator  implements Comparator{

    @Override
    public int getHeuristicValue(Constraint constraint) {
        //The amount of constraints on a field are equal to the amount of neighbours have a value
        int heuristicValue = 0;
        for (Field field : constraint.field1.getNeighbours()) {
            if (field.getValue() == 0) {
                heuristicValue++;
            }
        }
        return heuristicValue;
    }
}
