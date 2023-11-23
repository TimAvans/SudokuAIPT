

public class DegreeComparator  implements IComparator{

    @Override
    public int getHeuristicValue(Constraint constraint) {
        //The amount of constraints on a field are equal to the amount of neighbours have a value
        int heuristicValue = 0;
        for (Field field : constraint.field1.getNeighbours()) {  // Iterate through each neighbor of field1 in the constraint
            if (field.getValue() == 0) { // Increment heuristicValue for each neighbor with an unknown value
                heuristicValue++;
            }
        }
        return heuristicValue;
    }
}
