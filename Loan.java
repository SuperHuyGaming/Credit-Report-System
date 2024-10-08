/**
 * The client's loan.
 */
public class Loan {

    /**
     * Client's loanID.
     */
    protected String loanID;
    /**
     * Client's cardType.
     */
    protected String cardType;
    /**
     * Client's limit.
     */
    protected double limit;
    /**
     * Client's amountUsed.
     */
    protected double amountUsed;
    /**
     * CLient's status.
     */
    protected int status;
    /**
     * CLient's creditorID.
     */
    protected String creditorID;
    /**
     * Client's periodID.
     */
    protected String periodID;

    /**
     * Contructor of the Loan.
     * 
     * @param loanID     client's loanID
     * @param cardType   client's cardtype
     * @param limit      client's limit
     * @param amountUsed client's amountused
     * @param status     client's status
     * @param creditorID client's creditorID
     * @param periodID   client's periodID
     */

    public Loan(String loanID, String cardType, double limit, double amountUsed, int status, String creditorID,
            String periodID) {

        // Your code HERE.
        // Make sure all argument are valid according to the Validation rules in the
        // specification
        // Otherwise throw IllegalArgumentException
        this.loanID = loanID;

        this.cardType = cardType;
        if (this.cardType == "") {
            throw new IllegalArgumentException("You don't have your card type!\n");
        }
        this.amountUsed = amountUsed;
        this.status = status;
        if (status == 0) {
            throw new IllegalArgumentException("Status can not be empty!\n");
        }
        this.limit = limit;
        if (this.limit != 0 && this.status == 5) {
            throw new IllegalArgumentException("Limit must be 0 at status 5!\n");
        } else if (this.limit == 0 && this.status != 5) {
            throw new IllegalArgumentException("Limit can not be empty!\n");
        }

        this.creditorID = creditorID;
        this.periodID = periodID;
    }

    /**
     * This method compares loans by their loanID and CreditorID.
     * 
     * @param otherObject another object
     * @return true if 2 loans have same loanID and creditorID
     */
    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof Loan) || otherObject == null) {
            return false;
        }

        Loan otherLoan = (Loan) otherObject;

        return this.loanID.equals(otherLoan.loanID) && this.creditorID.equals(otherLoan.creditorID);

        // Read the addLoan method description in Client class.
    }

}
