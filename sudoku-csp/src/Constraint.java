public class Constraint implements Comparable {
    
    private Field field1;
    private Field field2;

    public Constraint(Field field1, Field field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public boolean checkConstraint()
    { 
        if (field1.getValue() != 0 && field2.getValue() != 0) {
            if (field2.getDomainSize() > 1) {
                if (field1.getValue() != field2.getValue()) {
                    return true;
                } 
                field2.removeFromDomain(field1.getValue());
            }
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }



}
