package com.homework3.my_linked_list;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int count;

    public void add (E element){
        Node<E> node = new Node<E>(element);
        if (head == null) {
            head=node;
        }else{
            tail.setNext(node);
        }

        tail = node;
        count++;
    }
    public void add (int index, E element){
        Node<E> node = new Node<E>(element);
        if (index>=count) {
            tail.setNext(node);
            tail=node;
        }else if (index==0){
            node.setNext(head);
            head=node;
        }
        else{
            Node<E> prev_node=head;
            for (int i = 1; i < index; i++)
                prev_node=prev_node.getNext();

            node.setNext(prev_node.getNext());
            prev_node.setNext(node);

        }
        count++;

    }
    public  E get (int index) {
        Node<E> cursor = head;
        for (int i = 1; i <= index; i++) {
            if (cursor.getNext()==null)
                throw new IndexOutOfBoundsException("Do not get, index "+index+" out of bounds");
            else
                cursor=cursor.getNext();
        }
        return cursor.getElement();
    }

public  E remove (int index) {
        Node<E> cursor=head;
        Node<E> prev_cursor =null;
        if(index==0){
            head=cursor.getNext();
        }else{
            for (int i = 1; i <= index ; i++){
                if (cursor.getNext()==null)
                    throw new IndexOutOfBoundsException("Do not remove, index " + index + " out of bounds");
                else {
                    prev_cursor=cursor;
                    cursor = cursor.getNext();
                }
                }

                if (cursor.getNext()==null)
                    tail=prev_cursor;

               prev_cursor.setNext(cursor.getNext());
        }
    cursor.setNext(null);
    count--;
    return cursor.getElement();
}

public Iterator<E> iterator() {
        return new Itr<E>(head);
}

public boolean addAll(Collection<? extends E> c) {
   boolean isChange=false;
    for (E element:c) {
        add(element);
        isChange=true;
    }
    return isChange;
    }

//public boolean copy(Collection<? super E> c) {}


    private class Itr<E> implements Iterator<E> {
   private Node<E> cursor;
   private Node<E> h;

   Itr(Node<E> head) {
       this.h = head;
       this.cursor=null;
   }
        @Override
        public boolean hasNext() {
       if (cursor==null)
           return h!=null;
       else
            return cursor.getNext()!=null;
        }

        @Override
        public E next() {
       if (cursor==null)
           cursor=h;
       else {
           if(!hasNext())
               throw new NoSuchElementException();

           cursor = cursor.getNext();
       }
            return cursor.getElement();
        }
    }
}
