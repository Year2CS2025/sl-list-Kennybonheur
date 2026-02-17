//package SLIST;

import java.util.NoSuchElementException;

public class SLinkedList<T> implements Iterable<T> {
    class Node{
        T data;
        Node next;
        Node(T data){
            this.data=data;
            next=null;
        }
    }

    private Node head;
    private Node tail;
    private int size=0;

    public SLinkedList(){
        head=null;
        size=0;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public void addFirst(T data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        tail=newNode;
        size++;
    }

    public void addLast(T data){
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail=newNode;
        } else {
            tail.next = newNode;
        }
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public T deleteFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        if (head == null) {
            tail = null; // If the list becomes empty, set tail to null
        }
        return data;
    }

    public T deleteLast(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (head.next == null) { // Only one element
            T data = head.data;
            head = null;
            tail = null;
            size--;
            return data;
        }
        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T data = tail.data;
        current.next = null;
        tail = current;
        size--;
        return data;
    }

    public void display(){
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public boolean contains(T data){
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear(){
        head = null;
        tail=null;
        size=0;
    }

    public T getFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    public T getLast(){
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }

    // Implement remove(T e)
    public void remove(T e) {
        if (head == null) return;

        if (head.data.equals(e)) {
            deleteFirst();
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(e)) {
                if (current.next == tail)
                    tail = current;
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    // Implement reverse()
    public void reverse(){
        Node prev = null;
        Node current = head;
        tail = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
    }

    // Implement deleteConsecutiveDuplicates()
    public void deleteConsecutiveDuplicates(){
        Node current = head;

        while (current != null && current.next != null) {
            if (current.data.equals(current.next.data)) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
    }

    //two lists are equal if they have the same 
    // size and the same elements in the same order

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SLinkedList<?> other = (SLinkedList<?>) obj;
        if (this.size != other.size) return false;

        java.util.Iterator<T> it1 = this.iterator();
        java.util.Iterator<?> it2 = other.iterator();
        while (it1.hasNext() && it2.hasNext()){
            T a = it1.next();
            Object b = it2.next();
            if (a == null){
                if (b != null) return false;
            } else {
                if (!a.equals(b)) return false;
            }
        }
        return !it1.hasNext() && !it2.hasNext();
    }

}
