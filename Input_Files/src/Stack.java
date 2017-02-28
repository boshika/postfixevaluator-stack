import java.util.EmptyStackException;
import java.nio.BufferOverflowException;

/**
 *
 *
 *
 * The stack class has a max size of 100 items
 * Any expressions ove 100 will throw an overflow stack error
 *
 *
 *
 * @author Boshika Tara
 */

public abstract class Stack implements ADT{
    private final int max_size = 100;
    private String[] data = new String[max_size];
    private int top = -1;    //empty stack

    public boolean isEmpty() {
        return (top == -1) ? true : false;
    }

    public void push(String value) throws BufferOverflowException {
        if(top == max_size -1) {
            throw new BufferOverflowException();
        }

        top++;
        data[top] = value;
    }

    public int size() {

        return top + 1;
    }

    public String peek() throws EmptyStackException {
        String tmp;

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        tmp = pop();
        push(tmp);

        return tmp;
    }

    public String pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        top--;
        return data[top+1];
    }

}
