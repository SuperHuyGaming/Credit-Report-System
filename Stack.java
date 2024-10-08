/**
 * This is a stack class.
 * @param <T> a generi type
 */
public class Stack<T> implements StackInterface<T> {
    /**
     * This is the stack.
     */
    LinkedList<T> myStack;
    /**
     *  Stack constructor.
     * */ 
    public Stack() {
        this.myStack = new LinkedList<>();
    }
    /**
     * Push item to stack.
     * @param item item to be pushed
     */
    public void push(T item) {
        myStack.add(item, 0);
    }
    /**
     * Retrieves first item in stack.
     * @return the item popped
     */
    public T pop() {
        return myStack.remove(0);
    }
    /**
     * Take a look at the top of the stack.
     * @return the item at the top
     */
    public T peek() {
        return myStack.get(0);
    }
    /**
     * Check if stack == 0.
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return (myStack.getSize() == 0);
    }
    /**
     * This is the testing method.
     * @param args just a thing
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Push items onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Peek at the top item
        System.out.println("Top item: " + stack.peek()); // Output: 30

        // Pop items from the stack
        System.out.println("Popped: " + stack.pop()); // Output: 30
        System.out.println("Popped: " + stack.pop()); // Output: 20

        // Check if the stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty()); // Output: false

        // Pop the last item
        System.out.println("Popped: " + stack.pop()); // Output: 10

        // Now the stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty()); // Output: true
    }

}
