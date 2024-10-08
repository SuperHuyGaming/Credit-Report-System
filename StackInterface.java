/**
 * This is a stack class.
 * @param <T> a generi type
 */
public interface StackInterface<T> {
    //Your code HERE.
    /**
     * Push item to stack.
     * @param item item to be pushed
     */
    public void push(T item);
    /**
     * Retrieves first item in stack.
     * @return the item popped
     */
    public T pop();
    /**
     * Take a look at the top of the stack.
     * @return the item at the top
     */
    public T peek();
    /**
     * Check if stack == 0.
     * @return true if stack is empty
     */
    public boolean isEmpty();
}
