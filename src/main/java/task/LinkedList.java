package task;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }


    private T add(T element) {
        if (head == null) {
            head = tail = new Node<>(element);
        }


        return element;
    }
}
