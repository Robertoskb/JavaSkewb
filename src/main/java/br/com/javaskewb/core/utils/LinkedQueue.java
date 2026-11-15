package br.com.javaskewb.core.utils;

public class LinkedQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public T dequeue() {
        if (head == null) {
            throw new IllegalStateException("Queue is empty");
        }

        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
