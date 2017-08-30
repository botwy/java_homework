package com.homework3.my_linked_list;

public class Node<E> {
    private E element;
    private Node<E> next;

    public Node(E data) {
        this.element = data;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setElement(E data) {
        this.element = data;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
