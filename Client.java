/**
 * This class represents a client.
 */
public class Client extends Person {
    /**
     * These are the titles that the csv file will contain.
     */
    protected String jobTitle;
    /**
     * Employer name.
     */
    protected String employer;
    /**
     * creditorID.
     */
    protected String creditorID;
    /**
     * periodID.
     */
    protected String periodID;
    /**
     * loan list of a client.
     */
    protected ListInterface<Loan> loans = new LinkedList<>();
    /**
     * This is the constructor.
     * @param ssn client's ssn
     * @param firstName client's firstname
     * @param lastName client's lastname
     * @param job client's job
     * @param employer client's employer
     * @param creditorID client's creditorID
     * @param period client's period
     */
    public Client(String ssn, String firstName, String lastName, String job, String employer, String creditorID,
            String period) {
        // Your code HERE.
        super(ssn, firstName, lastName);

        if (job == "") {
            this.jobTitle = job;

            this.employer = employer;
            if (employer.length() < 4) {
                throw new IllegalArgumentException("Your employer name words' length has to be greater than 4!\n");
            }
        } else {
            this.jobTitle = job;
            if (jobTitle.length() < 4) {
                throw new IllegalArgumentException("Your job tittle words' length has to be greater than 4!\n");
            }
            this.employer = employer;

            if (employer == "") {
                throw new IllegalArgumentException("You have a job, what is your employer's name?\n");
            }
            if (employer.length() < 4) {
                throw new IllegalArgumentException("Your employer name words' length has to be greater than 4!\n");
            }
        }

        this.creditorID = creditorID;
        this.periodID = period;
        // You must reuse code from the parent class
        // Make sure all argument are valid according to the Validation rules in the
        // specification
        // Otherwise throw IllegalArgumentException
    }
    /**
     * This is another constructor used to call from outside function to create report.
     * @param ssn client's snn
     * @param creditorID client's creditorID
     */
    protected Client(String ssn, String creditorID) {
        // Your code HERE.
        super(ssn);
        this.creditorID = creditorID;
        jobTitle = "";
        employer = "";
        periodID = "";
        // No validation rules apply here.
    }
    /**
     * This compares 2 clients based on ssn and creditorID.
     * @param otherObject another client
     * @return true if 2 clients has the same ssn and creditorID
     */
    @Override
    public boolean equals(Object otherObject) {
        // Your code HERE.
        // Need to make sure that the type in argument is the same as the type we're
        // comparing,
        // if not, we just return false
        if ((!(otherObject instanceof Client)) || otherObject == null) {
            return false;
        }
        Client otherClient = (Client) otherObject;
        // Return false if : the 2 instances do not correspond to clients with same ssn
        // and creditor_id;
        // && or || ?
        return super.equals(otherObject) && this.creditorID.equals(otherClient.creditorID);
        // return this.ssn.equals(otherClient.ssn) &&
        // this.creditorID.equals(otherClient.creditorID);
    }
    /**
     * This method add a loan to the client's loan list.
     * @param loan the loan object
     */

    public void addLoan(Loan loan) {
        // Your code HERE.
        // Add this loan to the list of loans.
        // System.out.println(loan);
        if (loans.contains(loan)) {
            // System.out.println("Loan is already existed!\n");
            return;
        }
        // check if loan is valid first
        // Loan realLoan = loan;

        loans.add(loan, 0);
        // Do not allow duplicate. (2 loan instances are considered the same if they
        // have same loanID and from same creditorID)
        // it doesn't ask us to check the status, same loan Id same creditorID but
        // status different, so we still add them in
        // if loan is the same, do we update loan?
        // Check that client creditor id is the same as loan creditor id when adding a
        // new loan to the list of loans.
    }
    /**
     * This method add client's another name in.
     * @param firstName client's other first name
     * @param lastName client's other last name
     * @param periodID client's period ID
     * @return true if other is added
     */
    public boolean otherName(String firstName, String lastName, String periodID) {
        // Your code HERE.w
        // What do we need periodID for?
        return super.otherName(firstName, lastName);
        // You must reuse code from the parent class.
        // if the name provided as argument is different from the actual name, add this
        // provided new name to the list otherNames.
    }
    /**
     * This method prints out the client data in string format.
     * @return client data in string format.
     */

    public String toString() {
        // Your code HERE.
        String info = super.toString();

        double current = 0;
        int currentTrack = 0;
        double notCurrent = 0;
        int notCurrentTrack = 0;
        int chargeOff = 0;

        if (this.loans.getSize() != 0) {
            for (int i = 0; i < loans.getSize(); i++) {
                if (loans.get(i).status == 1) {
                    current += loans.get(i).amountUsed;
                    currentTrack++;
                    continue;
                } else if (loans.get(i).status == 2 || loans.get(i).status == 3 || loans.get(i).status == 4) {
                    notCurrent += loans.get(i).amountUsed;
                    notCurrentTrack++;
                    continue;
                } else if (loans.get(i).status == 5) {
                    chargeOff += 1;
                }
            }

        }
        double total = current + notCurrent;
        String loans = "Loans           : " + String.format("%d", this.loans.getSize())
                + " -- Total Used Amount: " + String.format("%.1f\n", total);
        String currentLoan = "- Current       : " + String.format("%d", currentTrack)
                + " -- Used Amount: " + String.format("%.1f\n", current);
        String notCurrentLoan = "- Not Current   : " + String.format("%d", notCurrentTrack)
                + " -- Used Amount: " + String.format("%.1f\n", notCurrent);
        String chargeOffLoan = "- ChargeOff     : " + String.format("%d\n", chargeOff);
        String creditorID = "  - Submitted by: " + String.format("%s\n", this.creditorID);
        String periodID = "  - Last Update: " + String.format("%s", this.periodID);
        return info + loans + currentLoan + notCurrentLoan + chargeOffLoan + "\n" + creditorID + periodID;
        // You must reuse code from the parent class.
        // See the output example in the project description.
    }
    /**
     * This method prints out ssn hashcode.
     * @return prints out ssn hashcode
     */
    public int hashCode() {
        // Your code HERE.
        return ssn.hashCode();
        // 2 client instances with same ssn should have the same hashcode.

    }
    /**
     * This is the testing method.
     * @param args just a thing
     */

    public static void main(String[] args) {
        // Add more tests...
        Client c = new Client("22", "Adrien", "Feldmann", "Sales Associate", "Jenkins Inc", "C090", "202408");
        System.out.println(c);
        // Client c1 = new Client("577-27-419", "Adrien", "Feldmann", "Sales Associate",
        // "Jenkins Inc", "C0902",
        // "202408");
        System.out.println(c.hashCode());
        // System.out.println(c1.hashCode());

    }
}
