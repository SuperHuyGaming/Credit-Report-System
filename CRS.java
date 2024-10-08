import java.io.*;
import com.opencsv.*;

// import java.util.*;
/**
 * This is the CRS.
 */
public class CRS {
    /**
     * this is the hashset used to store client's data.
     */
    private static HashSet<Client> hashSet = new HashSet<>(100);
    /**
     * This is the linked list of creditors.
     */
    private static LinkedList<String> listOfCreditors;

    // add creditors stack contents to the linkedlist listOfCreditors
    /**
     * this method add all creditors from the stack to the linked list creditors.
     * 
     * @param creditors stack of creditors
     */
    public static void setListOfCreditors(StackInterface<String> creditors) {
        // Your code HERE.
        LinkedList<String> mylistOfCreditors = new LinkedList<>();
        while (!creditors.isEmpty()) {
            mylistOfCreditors.add(creditors.pop(), 0);
        }

        listOfCreditors = mylistOfCreditors;

    }

    /**
     * This method get the name of all files in the folder.
     * 
     * @param folderName name of folder
     * @return the file lists
     */
    // return array of files (all files in the folder folderName)
    public static File[] getListOfFiles(String folderName) {
        // Your code HERE.
        File folder = new File(folderName);

        if (folder.exists() && folder.isDirectory()) {
            return folder.listFiles();
        } else {
            return new File[0];
        }

    }

    // load only data from the creditors added to the listOfCreditors to the hashSet
    // if a line contains invalid data (as per the validation rules provided, skip
    // this line, do not stop the loading process)
    // the method will add only valid data to the hashset
    // return true if some data from the file were added to the dataset, false
    // otherwise
    /**
     * Load data from file.
     * 
     * @param file the file to be loaded
     * @return true if some data was added
     */
    public static boolean loadData(File file) {

        // Your code HERE.
        if (file == null) {
            return false;
        }
        boolean dataAdded = false;
        String creditorID = file.getName().split("_")[0];
        String periodID = file.getName().split("_")[1].split("\\.")[0];

        try {
            // BufferedReader buffer = new BufferedReader(new FileReader(file));
            // did not read the other names
            // only prints out 1 loan instead of 3 for 577-27-4193
            CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()));

            String[] data;
            boolean endOfFile = false;

            // (loadData() should return false if the file creditor ID
            // is not in listOfCreditors)
            if (!listOfCreditors.contains(creditorID)) {
                return false;
            }

            while (!endOfFile) {
                try {
                    data = reader.readNext();
                    // System.out.println(Arrays.toString(data));
                    // String[] data = line.split(",");
                    // add basic data in first
                    Client dummyClient = new Client(data[0], data[1], data[2], data[3], data[4], creditorID, periodID);
                    Client dummyAddClient = new Client(data[0], creditorID);
                    // convert String to double and int, throw inconvertable error if can't convert

                    double limit = Double.parseDouble(data[7]);
                    double amountUsed = Double.parseDouble(data[8]);
                    int status = Integer.parseInt(data[9]);
                    // System.out.println("This is status.");
                    // System.out.println(status);

                    // create a loan object of a client to add in
                    Loan dummyLoan = new Loan(data[5], data[6], limit, amountUsed, status, creditorID, periodID);

                    // merge people in
                    if (hashSet.get(dummyClient) != null) {
                        hashSet.get(dummyClient).addLoan(dummyLoan);
                        // when I use hashSet.get() it will get the client based on their ssn and
                        // creditorID so guy from C090 and guy from
                        // C099 will be 2 different objects although same ssn. But I want to merge all
                        // names from all creditorID into 1 guy
                        // how do I do that?
                        // khi lấy thằng client ra thì nó sẽ lấy theo ssn và creditorID nên thằng
                        // creditorID C090 và C099 sẽ khác nhau
                        // và khi add othername vô thì 2 thằng bên C099 sẽ add thằng thứ hai vô thay vì
                        // add 2 thằng bên C099 vô C090
                        hashSet.get(dummyClient).otherName(dummyClient.firstName,
                                dummyClient.lastName);
                    } else {
                        dummyClient.addLoan(dummyLoan);
                        dummyClient.otherName(data[1], data[2]);
                        hashSet.put(dummyClient);
                    }

                    // hashSet.get(dummyClient).otherName(dummyClient.firstName,
                    // dummyClient.lastName);
                    // set.get(...) contains a value, merge the added value in value already in set

                    // set.get(...) returns null
                    // hashSet.put(dummyClient);

                    dataAdded = true;
                } catch (IllegalArgumentException e) {
                    // validation not right, skip loading the data
                    continue;
                }

            }
        }

        catch (Exception e) {// this should be end of file exception
            return true;
        }

        return dataAdded;

    }

    /**
     * create a string of the client's data.
     * 
     * @param ssn the client's data
     * @return a string of the client's data
     */
    public static String createReport(String ssn) {
        // Your code HERE.
        // String title = "FULL Credit Report\n";

        // return title + hashSet.get(new Client(ssn, title)).toString();

        // String creditorID = listOfCreditors.get(0);

        // String anotherCreditorID = listOfCreditors.get(1);
        // System.out.println(anotherCreditorID);
        // Client anotherClient = new Client(ssn, listOfCreditors.get(1));

        // Create a dummy client with SSN for search
        int track = 0;
        Client client = hashSet.get(new Client(ssn, listOfCreditors.get(track)));
        while (client == null && track < listOfCreditors.getSize() - 1) {
            client = hashSet.get(new Client(ssn, listOfCreditors.get(track)));
            track++;
        }
        // use the latest creditor ID
        client.creditorID = listOfCreditors.get(0);
        for (int i = 0; i < listOfCreditors.getSize(); i++) {
            // if (hashSet.get(client) != null) {
            // System.out.println(new Client(ssn, listOfCreditors.get(i)).loans.getSize());
            // System.out.println(hashSet.get(new Client(ssn,
            // listOfCreditors.get(i))).toString());
            // hashSet.get(client).addLoan(hashSet.get(new Client(ssn,
            // listOfCreditors.get(i))).loans.get(0));
            // hashSet.get(client).addLoan(hashSet.get(new Client(ssn,
            // listOfCreditors.get(1))).loans.get(1));
            Client dummClient = hashSet.get(new Client(ssn, listOfCreditors.get(i)));
            if (dummClient == null) {
                continue;
            }

            int trackLoan = dummClient.loans.getSize();
            int trackOtherNames = dummClient.otherNames.getSize();
            for (int j = 0; j < trackLoan; j++) {
                hashSet.get(client).addLoan(dummClient.loans.get(j));
            }

            client.otherName(dummClient.firstName, dummClient.lastName);

            for (int j = 0; j < trackOtherNames; j++) {

                String[] name = dummClient.otherNames.get(j).split(" ");
                hashSet.get(client).otherName(name[0], name[1]);
                // client.otherName(dummClient.firstName, dummClient.lastName);

            }

        }
        // Client dummClient = hashSet.get(new Client(ssn, listOfCreditors.get(0)));
        // int trackOtherNames = dummClient.otherNames.getSize();
        // for (int j = 0; j < trackOtherNames; j++) {
        // String[] name = dummClient.otherNames.get(j).split(" ");

        // hashSet.get(client).otherName(name[0], name[1]);
        // }
        // hashSet.get(client).otherNames.remove(hashSet.get(client).otherNames.getSize()
        // - 1);
        // hashSet.get(client).otherNames.remove(hashSet.get(client).otherNames.getSize()
        // - 1);

        // Client clien2 = hashSet.get(new Client(ssn, anotherCreditorID));
        // System.out.println(clien2.toString());
        // System.out.println(client.toString());
        // for (int i = 1; i < listOfCreditors.getSize(); i++) {
        // for (int j = 0; j < new Client(ssn, listOfCreditors.get(i)).loans.getSize();
        // j++) {
        // client.addLoan(new Client(ssn, listOfCreditors.get(i)).loans.get(j));
        // client.creditorID = listOfCreditors.get(1);
        // }
        // }
        // change creditorID to the latest
        // for (int i = 1; i < listOfCreditors.getSize(); i++) {
        // client.creditorID = listOfCreditors.get(i);
        // }

        if (client == null) {
            return "No report available for SSN: " + ssn;
        }
        String title = "FULL Credit Report\n";

        // client.addLoan();
        return title + client.toString();
    }

    /**
     * This is the testing method.
     * 
     * @param args just a thing
     */
    public static void main(String[] args) {

        // ONLY FOR TESTING PURPOSE...
        // Refer to the project description for expected ouput of createReport().

        // Provide the list of creditors to consider
        Stack<String> creditors = new Stack<>();
        // creditors.push("C007");
        creditors.push("C090");
        creditors.push("C099");
        creditors.push("C007");
        // Move the creditors code from the stack to the list of creditors using the
        // method setListOfCreditors
        setListOfCreditors(creditors);

        // Load data from the CSV files to the HashSet
        // 1- Get all the files (ignoring the list of creditors)
        File[] files = getListOfFiles("dataset");
        System.out.println("Files found in the dataset folder:" + files.length);
        int countFilesLoaded = 0;

        // 2-Read files content (loadData() should return false if the file creditor ID
        // is not in listOfCreditors)
        for (int i = 0; i < files.length; i++)
            if (loadData(files[i])) {
                countFilesLoaded++;
            }
        System.out.println("Files loaded into the hashset:" + countFilesLoaded);

        System.out.println("");

        // 3-create report for a client
        System.out.println(createReport("577-27-4193"));
        System.out.println(createReport("780-94-3100"));
        System.out.println(createReport("436-70-5107"));
        System.out.println(createReport("190-05-2005"));
        System.out.println(createReport("240-01-2005"));

        // System.out.println(createReport("57-27-4193"));w
        // why don't we check if ssn is in valid format, is it because
        // when you declare ssn you have to check but if you call it if it's not in
        // valit format, you return can't find it?
    }
}
