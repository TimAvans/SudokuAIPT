import java.util.ArrayList;
import java.util.List;

public class Field {
  private int value = 0;
  private List<Integer> domain;
  private List<Field> neighbours; //A list of all fields that this field is constrained by
  /*
   * ==============
   *  CONSTRUCTORS
   * ==============
   */

  /**
   * Constructor in case the field is unknown
   */
  Field() {
    this.neighbours = new ArrayList<>();
    this.domain = new ArrayList<>(9);
    for (int i = 1; i < 10; i++)
      this.domain.add(i);
  }

  /**
   * Constructor in case the field is known, i.e., it contains a value
   * @param initValue
   */ 
  Field(int initValue) {
    this.value = initValue;
    this.domain = new ArrayList<>();
    this.neighbours = new ArrayList<>();
  }

  /*
   * =================
   *  VALUE FUNCTIONS
   * =================
   */
  /**
   * Return the value of the field
   * @return integer value
   */
  public int getValue() {
    return value;
  }

  /**
   * Set the value of the field
   * @param value
   */
  public void setValue(int value) {
    this.value = value;
  }

  /*
   * =====================
   *  NEIGHBOUR FUNCTIONS
   * =====================
   */
  /**
   * Set the list of neighbours of this field
   * @param neighbours
   */
  public void setNeighbours(List<Field> neighbours) {
    this.neighbours = neighbours; // Set the list of neighbours for this field
  }

 /**
 * Add the neighbour given as parameter to the neighbour list of this field
 * @param neighbour
 */
  public void addNeighbour(Field neighbour)
  {
    this.neighbours.add(neighbour); // Add the specified field as a neighbour
  }

  /**
   * Get the list of neighbours
   * @return List of fields
   */
  public List<Field> getNeighbours() {
    return neighbours; // Return the list of neighbours for this field
  }

  /**
   * Get the list of neighbours minus the field given as parameter
   * @param b Field
   * @return List of fields
   */
  public List<Field> getOtherNeighbours(Field b) {
    List<Field> newNeighbours = new ArrayList<>(neighbours);
    newNeighbours.remove(b); // Remove the specified field from the list of neighbours
    return newNeighbours; // Return the modified list of neighbours
  }

  /*
   * ==================
   *  DOMAIN FUNCTIONS
   * ==================
   */
  /**
  * Return the domain 
  * @return List of integers
  */
  public List<Integer> getDomain() {
    return domain;
  }

  /**
   * Return the domain's size
   * @return The size of the list of integers
   */
  public int getDomainSize() {
    return domain.size();
  }

   /**
   * Removes the given value from the domain, and possibly assigns the last value to the field.
   * @param value
   * @return true if the value was removed
   */
  public boolean removeFromDomain(int value) {
    boolean b = this.domain.remove(Integer.valueOf(value));

    // If there is only one value left in the domain, sets the value of the field to the last domain value.
    if (domain.size() == 1) {
      setValue(domain.get(0));
    }
    
    return b;
  }

  /*
   * ================
   *  MISC FUNCTIONS
   * ================
   */
  /**
  * Override of the toString function. 
  */
  @Override
  public String toString() {
    return (value==0)? "." : String.valueOf(value);
  }

  /**
   * Override of the equals function because comparing objects does not work well in Java.
   * returns True if the objects are indeed equal else false.
   */
  @Override
  public boolean equals(Object other)
  {
    if (other instanceof Field) { // Compate the domain, neighbours and value of the two fields
      if (((Field)other).domain == this.domain 
      && ((Field)other).neighbours == this.neighbours 
      && ((Field)other).value == this.value) {
        return true; // The fiels are equal
      }
    }
    return false; // The fields are not equal
  }
}
