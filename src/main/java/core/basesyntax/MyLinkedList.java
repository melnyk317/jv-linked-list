package core.basesyntax;

import java.util.List;

import org.w3c.dom.Node;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private int size;
    private Node first;
    private Node last;

    public MyLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public void add(T value) {
        Node newNode = new Node<T>(last, value, null);
        if (size == 0) {
            first = newNode;
        }
        last.next = newNode;
        last = newNode;
        size++;

    }

    @Override
    public void add(T value, int index) {
        Node theNode = findNode(index);
        if (index == size) {
            add(value);
            return;
        }
        Node newNode = new Node<T>(theNode.prev, value, theNode);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return (T) findNode(index).item;
    }

    @Override
    public T set(T value, int index) {
        Node xNode = findNode(index);
        T oldValue = (T) xNode.item;
        xNode.item = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Node xNode = findNode(index);
        T theItem = (T) xNode.item;
        unlink(xNode);
        return theItem;
    }

    @Override
    public boolean remove(T object) {
        for (Node node = first; node != null; node = node.next) {
            if (object == null ? node.item == null : object.equals(node.item)) {
                unlink(node);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private Node findNode(int index) {
        Node xNode = first;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index < size / 2) {
            for (int i = 0; i < index; i++) {
                xNode = xNode.next;
            }
        } else {
            for (int i = size - 1; i > index; i--) {
                xNode = xNode.prev;
            }
        }
        return xNode;
    }

    private void unlink(Node node) {
        if (node.prev == null) {
            first = node.next;
        } else {
            node.prev.next = node.next;
            node.prev = null;
        }
        if (node.next == null) {
            last = node.prev;
        } else {
            node.next.prev = node.prev;
            node.next = null;
        }
        node.item = null;
        size--;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
