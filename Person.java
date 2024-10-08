/**
 * This is a person class.
 */
public class Person {

    /**
     * A person ssn.
     */
    protected String ssn;
    /**
     * A person firstname.
     */
    protected String firstName;
    /**
     * A person lastname.
     */
    protected String lastName;
    /**
     * A list of a person other names.
     */
    protected ListInterface<String> otherNames = new LinkedList<>();
    /**
     * get the person's ssn from create report.
     * @param ssn a person ssn
     */
    protected Person(String ssn) {
        // Your code HERE.
        // No validation rules apply here.
        this.ssn = ssn;
    }
    /**
     * This is the constructor.
     * @param ssn a person ssn
     * @param firstName a person firstname
     * @param lastName a person lastname
     */
    public Person(String ssn, String firstName, String lastName) {
        // Your code HERE.

        // Make sure all argument are valid according to the Validation rules in the
        // specification
        String sample = "000-00-0000";
        // need to check if SSN is in this format
        for (int i = 0; i < ssn.length(); i++) {
            if (ssn.length() != 11) {
                throw new IllegalArgumentException("SSN is not in the correct format!\n");
            }
            if (ssn.charAt(i) != '-' && (i == 3 || i == 6)) {
                throw new IllegalArgumentException("SSN is not in the correct format!\n");
            }
            if (!isDigit(ssn.charAt(i)) && ssn.charAt(i) != '-') {
                throw new IllegalArgumentException("SSN is not in the correct format!\n");
            }
        }
        if (firstName.length() < 2) {
            throw new IllegalArgumentException("First name has to have at least length of 2!\n");
        }
        if (lastName.length() < 2) {
            throw new IllegalArgumentException("Last name has to have at least length of 2!\n");
        }
        if (ssn.length() <= 0) {
            throw new IllegalArgumentException("SSN can not be empty!\n");
        }

        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;

        // Otherwise throw IllegalArgumentException
    }
    /**
     * This method add client's another name in.
     * @param firstName client's other first name
     * @param lastName client's other last name
     * @return true if other is added
     */

    public boolean otherName(String firstName, String lastName) {
        // int nameTrack = 0;
        // Your code HERE.
        if (otherNames.contains(firstName + " " + lastName)
                || (firstName.equals(this.firstName) && lastName.equals(this.lastName))) {
            return false;
        }
        otherNames.add(firstName + " " + lastName, 0);
        return true;
        // for (int i = 0; i < otherNames.getSize(); i++) {
        // String nameToBeAdded = firstName + " " + lastName;
        // if (nameToBeAdded.equals(otherNames.get(i))) {
        // nameTrack++;
        // }
        // }
        // // if (nameTrack == 0) {
        // // otherNames.add(firstName + " " + lastName, 0);
        // // return true;
        // // }
        // if ((!firstName.equals(this.firstName) || !lastName.equals(this.lastName)) &&
        // nameTrack == 0) {
        // otherNames.add(firstName + " " + lastName, 0);
        // return true;
        // }
        // return false;
        // if the name provided as argument is different from the actual name, add this
        // provided new name to the list otherNames.
    }
    /**
     * This method compares 2 persons based on their ssn.
     * @param anotherObject another person object
     * @return true if 2 persons have same ssn
     */
    public boolean equals(Object anotherObject) {
        if (!(anotherObject instanceof Person) || anotherObject == null) {
            return false;
        }
        Person anotherPerson = (Person) anotherObject;
        return (this.ssn.equals(anotherPerson.ssn));
    }
    /**
     * This method prints out the client data in string format.
     * @return client data in string format.
     */

    public String toString() {
        // Your code HERE.
        // See the output example in the project description.
        String firstname = "First Name      : " + firstName + "\n";
        String lastname = "Last name       : " + lastName + "\n";
        String sampleSSN = "SSN             : " + ssn + "\n";
        String names = "";
        String othernames = "Other Names     : ";
        if (otherNames.getSize() != 0) {
            for (int i = 0; i < otherNames.getSize() - 1; i++) {
                names += otherNames.get(i) + ", ";
            }
            othernames += names + otherNames.get(otherNames.getSize() - 1) + "\n";
        } else {
            othernames += "\n";
        }

        return firstname + lastname + sampleSSN + othernames;
    }
    /**
     * This checks if a character is a digit.
     * @param a a character
     * @return true if a character is a digit
     */
    private boolean isDigit(char a) {
        return Character.isDigit(a);
    }
    /**
     * This is the testing method.
     * @param args just a thing
     */
    public static void main(String[] args) {

        // Add more tests

        Person p = new Person("577-27-4193", "Adrien", "Feldmann");
        p.otherName("Adrian", "Feldmann");
        p.otherName("Adrian", "Feldman");
        System.out.println(p);
    }

}
