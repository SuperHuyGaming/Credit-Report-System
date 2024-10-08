//import java.util.LinkedList;
/**
 * This is a linked list made by me.
 * @param <T> this is a generic type
 */

public class LinkedList<T> implements ListInterface<T> {
    // Your code HERE
    /**
     * This is the head of the linked list.
     * @param <T> a generic type
     */
    private Node<T> head;
    /**
     * This is the size of the linked list.
     */
    private int size = 0;
    /**
     * Initialize the linked list.
     */
    public LinkedList() {
        head = null;
    }
    /**
     * Set the item in the list index to be anther item.
     * @param item item to be set
     * @param index index of the item to be set
     * @return the item set
     */
    public T set(T item, int index) {
        Node<T> dummy = head;
        for (int i = 0; i < index; i++) {
            dummy = dummy.next;
        }
        dummy.item = item;
        return dummy.item;
    }
    /**
     * Get the item in that index.
     * @param index index of the item to be get
     * @return the item
     */
    public T get(int index) {

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Index is out of bounds " + index);
        }

        Node<T> dummy = head;
        for (int i = 0; i < index; i++) {
            dummy = dummy.next;
        }
        return dummy.item;
    }
    /**
     * Add item to that index, push the other items forward.
     * @param item item to be added
     * @param index index of item to be added
     */
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
        }
        if (index == 0) {
            head = new Node<>(item, head);
        } else {
            Node<T> dummy = head;
            for (int i = 0; i < index - 1; i++) {
                dummy = dummy.next;
            }
            Node<T> addItem = new Node<>(item, dummy.next);
            dummy.next = addItem;
        }
        size++;
        return;
    }
    /**
     * Remove item at that index, shifts other items inward.
     * @param index index of item to be removed
     * @return the item removed
     */
    public T remove(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Index is out of bounds " + index);
        }
        T removedItem;
        if (index == 0) {
            removedItem = head.item;
            head = head.next;
        } else {
            Node<T> dummy = head;
            for (int i = 0; i < index - 1; i++) {
                dummy = dummy.next;
            }
            removedItem = dummy.next.item;
            dummy.next = dummy.next.next;
        }
        size--;
        return removedItem;

    }
    /**
     * Check if linked list contains that item.
     * @param item the item to be cheecked
     * @return true if the linkedlist contains item
     */
    public boolean contains(T item) {
        Node<T> dummy = head;
        for (int i = 0; i < size; i++) {
            if (dummy.item.equals(item)) {
                return true;
            }
            dummy = dummy.next;
        }
        return false;
    }
    /**
     * check if linkedlist is empty.
     * @return true if linkedlist is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    /**
     * get the linkedlist size.
     * @return size of linkedlist
     */
    public int getSize() {
        return size;
    }
    /**
     * This is a node class used to create a linkedlist.
     * @param <E> a generic type
     */
    private static class Node<E> {
        /**
         * Item of generic type.
         */
        E item;
        /**
         * the next node of the current node.
         * @param <E> a generic type
         */
        Node<E> next;
        /**
         * The constructor.
         * @param element item of the node
         * @param next the next node the current node is pointing to
         */
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
    /**
     * This is the testing method.
     * @param args just a thing
     */
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(20, 0);
        list.add(30, 0);
        System.out.println(list.get(0)); // 20
        System.out.println(list.get(1)); // 30
    }

}
