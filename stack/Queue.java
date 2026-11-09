public class Queue {
    private static class QueueNode {
        String data;
        QueueNode next;

        QueueNode(String data) {
            this.data = data;
        }
    }

    private QueueNode front;
    private QueueNode rear;

    public void enqueue(String data) {
        QueueNode node = new QueueNode(data);
        if (isEmpty()) {
            front = node;
        } else {
            rear.next = node;
        }
        rear = node;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        String data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
