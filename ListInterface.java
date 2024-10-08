/**
 * This is a linked list made by me.
 * @param <T> this is a generic type
 */
public interface ListInterface<T> {
    //Your code HERE.
    /**
     * Set the item in the list index to be anther item.
     * @param item item to be set
     * @param index index of the item to be set
     * @return the item set
     */
    public T set(T item, int index);
    /**
     * Get the item in that index.
     * @param index index of the item to be get
     * @return the item
     */
    public T get(int index);
    /**
     * Add item to that index, push the other items forward.
     * @param item item to be added
     * @param index index of item to be added
     */
    public void add(T item, int index);
    /**
     * Remove item at that index, shifts other items inward.
     * @param index index of item to be removed
     * @return the item removed
     */
    public T remove(int index);
    /**
     * Check if linked list contains that item.
     * @param item the item to be cheecked
     * @return true if the linkedlist contains item
     */
    public boolean contains(T item);
    /**
     * check if linkedlist is empty.
     * @return true if linkedlist is empty
     */
    public boolean isEmpty();
    /**
     * get the linkedlist size.
     * @return size of linkedlist
     */
    public int getSize();
}
