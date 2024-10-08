/**
 * This is the hashSet.
 * 
 * @param <V> a generic type
 */
public class HashSet<V> {
    /**
     * This is the hashTable.
     */
    private LinkedList<V>[] hashTable;
    /**
     * This is the cap.
     */
    private int cap;
    /**
     * This is the size.
     */
    private int size;

    /**
     * This is the constructor.
     * 
     * @param cap the capacity
     */
    @SuppressWarnings("unchecked")
    public HashSet(int cap) {
        // Your code HERE.
        this.cap = cap;
        hashTable = new LinkedList[cap];
        size = 0;
    }

    /**
     * Puts items (client) in hashset.
     * 
     * @param value the client's data
     */
    @SuppressWarnings("unchecked")
    public void put(V value) {
        // check if load factor > 1
        // check if value is already in hashset
        for (int i = 0; i < size; i++) {
            if (hashTable[i] == null) {
                continue;
            }
            for (int j = 0; j < hashTable[i].getSize(); j++) {
                if (hashTable[i].isEmpty()) {
                    break;
                }
                if (hashTable[i].get(j).equals(value)) {
                    return;
                }
            }
        }
        if (size >= cap) {
            // double hashTable size
            cap *= 2;
            LinkedList<V>[] hashTable2 = new LinkedList[cap];
            for (int i = 0; i < size; i++) {
                // create new hashcode for existing element
                int newIndex = Math.abs(hashTable[i].hashCode() % (cap));
                hashTable2[newIndex] = hashTable[i];
            }
            hashTable = hashTable2;

            int index = Math.abs(value.hashCode() % (cap));

            if (hashTable[index] == null) {
                hashTable[index] = new LinkedList<>();
            }
            ListInterface<V> myLinkedList = hashTable[index];
            myLinkedList.add(value, myLinkedList.getSize());
        } else {
            int index = Math.abs(value.hashCode() % cap);

            if (hashTable[index] == null) {
                hashTable[index] = new LinkedList<>();
            }
            ListInterface<V> myLinkedList = hashTable[index];
            myLinkedList.add(value, myLinkedList.getSize());
        }

        // check if value is already in set

        // Time complexity: O(1)
        // Your code HERE.
    }

    /**
     * Get the value in the hashset.
     * 
     * @param value the value to get
     * @return the value's data
     */
    public V get(V value) {
        int index = Math.abs(value.hashCode() % cap);
        ListInterface<V> myLinkedList = hashTable[index];
        if (hashTable[index] == null) {
            return null;
        }
        for (int i = 0; i < myLinkedList.getSize(); i++) {
            if (myLinkedList.get(i).equals(value)) {
                // tại vì ssn giống nhưng creditorID khác nên 2 thằng ssn giống vi
                // creditorID khác sẽ bị nhốt vô khác chỗ
                return myLinkedList.get(i);
            }
        }
        return null;

        // Time complexity: O(1)
        // Your code HERE.
    }

    /**
     * get the size of hashset.
     * 
     * @return size of hashset
     */
    protected int getSize() {
        return size;
    }

    /**
     * This is the testing method.
     * 
     * @param args just a thing
     */
    public static void main(String[] args) {
        HashSet<Client> myClientHashSet = new HashSet<>(10);
        myClientHashSet.put(new Client("577-27-419", "C090"));
        Client c = new Client("577-27-4193", "Adrien", "Feldmann", "Sales Associate", "Jenkins Inc", "C090", "202408");
        myClientHashSet.put(c);
        Client c1 = new Client("577-27-4192", "Adrien", "Feldmann", "Sales Associate", "Jenkins Inc", "C0901",
                "202408");
        myClientHashSet.put(c1);
        System.out.println(myClientHashSet.get(c1));
        System.out.println(myClientHashSet.get(c));

    }
}