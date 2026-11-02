public class Stack {
    private Node top;

    public void push(char data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    public char pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty.");
        }
        char data = top.data;
        top = top.next;
        return data;
    }

    public char peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty.");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
