public class Constraint{
    
    public Field field1;
    public Field field2;

    public Constraint(Field field1, Field field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public boolean checkConstraint()
    { 
        if (field1.getValue() == 0 && field2.getValue() != 0) {
            alterDomain();
            return true;
        }
        return false;
    }

    private void alterDomain()
    {
        field2.removeFromDomain(field2.getValue()); 
    }
}
