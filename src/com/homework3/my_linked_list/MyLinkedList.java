package com.homework3.my_linked_list;

import java.util.*;

public class MyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int count = 0;

    public void add(E element) {
        Node<E> node = new Node<E>(element);
        if (count == 0) {
            head = node;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
        }

        tail = node;
        count++;
    }

    public void add(int index, E element) {
        if (count == 0 || index>count) throw new IndexOutOfBoundsException("Do not add, index " + index + " out of bounds");
        Node<E> node = new Node<E>(element);
        if (index == 0) {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        } else if (index == count) {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        } else {
            Node<E> cursor = findNodeByIndex(index);

            node.setNext(cursor);
            node.setPrev(cursor.getPrev());
            node.getPrev().setNext(node);
            node.getNext().setPrev(node);
        }
        count++;

    }

    public E get(int index) {
        if (count == 0 || index>=count) throw new IndexOutOfBoundsException("Do not get, index " + index + " out of bounds");
        Node<E> cursor = findNodeByIndex(index);

        return cursor.getElement();
    }

    public E remove(int index) {
        if (count == 0 || index>=count) throw new IndexOutOfBoundsException("Do not remove, index " + index + " out of bounds");

        Node<E> cursor = null;
        if (index == 0) {
            cursor=head;
            head = cursor.getNext();
        }
        else if (index == count-1) {
            cursor=tail;
            tail = cursor.getPrev();
            tail.setNext(null);
        }
        else {
            cursor=findNodeByIndex(index);
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
        }
        cursor.setNext(null);
        cursor.setPrev(null);
        count--;
        return cursor.getElement();
    }

    public Iterator<E> iterator() {
        return new Itr<E>(head);
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean isChange = false;
        for (E element : c) {
            add(element);
            isChange = true;
        }
        return isChange;
    }

    public boolean copy(Collection<? extends E> c) {
        if (c.size() > count)
            throw new IndexOutOfBoundsException("Do not copy from this Collection, size of MyLinkedList < size of this Collection");
        if (count == 0) return false;

        Node<E> cursor = null;
        Iterator<? extends E> iterator = c.iterator();
        for (int i = 0; i < count; i++) {
            if (i == 0) cursor = head;
            else cursor = cursor.getNext();

            if (iterator.hasNext()) cursor.setElement(iterator.next());
        }

        return true;
    }


    private class Itr<E> implements Iterator<E> {
        private Node<E> cursor;
        private Node<E> h;

        Itr(Node<E> head) {
            this.h = head;
            this.cursor = null;
        }

        @Override
        public boolean hasNext() {
            if (cursor == null)
                return h != null;
            else
                return cursor.getNext() != null;
        }

        @Override
        public E next() {
            if (cursor == null)
                cursor = h;
            else {
                if (!hasNext())
                    throw new NoSuchElementException();

                cursor = cursor.getNext();
            }
            return cursor.getElement();
        }
    }

    private Node<E> findNodeByIndex(int index) {
        Node<E> cursor = null;
        if (index<count-index) {
            for (int i = 0; i <= index; i++)
                if (i == 0) cursor = head;
                else
                    cursor = cursor.getNext();
        }else{
            for (int i = 0; i < count-index; i++)
                if (i == 0) cursor = tail;
                else
                    cursor = cursor.getPrev();
        }
        return cursor;
    }
}
