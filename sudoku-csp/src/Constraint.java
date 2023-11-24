public class Constraint {
    
    public Field field1;
    public Field field2;

    /**
     * Constraint ctor
     * @param field1
     * @param field2
     */
    public Constraint(Field field1, Field field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    /**
     * Check constraint function checks if for this constraint the domain of Field1 has to be altered.
     * @return True if constraint's field has been altered
     */
    public boolean checkConstraint()
    { 
        // Check if field1 has no assigned value, and field2 has an assigned value
        if (field1.getValue() == 0 && field2.getValue() != 0) {
            // Alter the domain of field1 by removing the value of field2
            alterDomain();
            return true;
        }
        return false;
    }

    /**
     * Alter domain of field1 by removing value of field2
     */
    private void alterDomain()
    {
        //Remove the value of field2 from the domain of field1
        field1.removeFromDomain(field2.getValue()); 
    }

    /**
     * Override of the equals function because comparing objects does not work well in Java.
     * returns True if the objects are indeed equal else false.
     */
    @Override
    public boolean equals(Object other) {  
        // Check if the object being compared is an instance of Constraint                                       
        if(other instanceof Constraint)
        {
            //Compare the fields of the current instance with the fields of the other instance
            if(((Constraint)other).field1 == this.field1 
            && ((Constraint)other).field2 == this.field2)
            {
                return true;
            }
        }
        return false;
    }
}
