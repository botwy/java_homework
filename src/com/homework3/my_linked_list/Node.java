package com.homework3.my_linked_list;

public class Node<E> {
    private E element;
    private Node<E> next;
    private Node<E> prev;

    public Node(E element) {
        this.element = element;
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

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getPrev() {

        return prev;
    }
}
