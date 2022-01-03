
public class Person implements Comparable<Person>  {

    private String firstName, lastName;
    private int id;

    public Person(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + id + ")";
    }


    /*
    Person objects are ordered by last name (ignoring case/capitalization), then by first name (ignoring case/capitalization), then by id.
    In other words, if two people have the same last name, they should be ordered based on first name.
    If two people have the same last and first name, they should be ordered based on id.
    Use alphabetic ordering and ignore capitalization (meaning that "cat" is treated the same as "CAT").
     */
    @Override
    public int compareTo(Person obj){
        int lastNameComparison = this.getLastName().toLowerCase().compareTo(obj.getLastName().toLowerCase());
        int firstNameComparison = this.getFirstName().toLowerCase().compareTo(obj.getFirstName().toLowerCase());

        if (lastNameComparison==0){
            if(firstNameComparison==0){
                return Integer.compare(this.getId(),obj.getId());
            } else {
                return firstNameComparison;
            }
        } else {
            return lastNameComparison;
        }
    }


}
