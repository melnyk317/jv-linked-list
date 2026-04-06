package core.basesyntax;

import java.util.List;

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
        last = newNode;
        size++;

    }

    @Override
    public void add(T value, int index) {
        Node theNode = findNode(index);
        if (index == size) {
            add(value);
        }
        Node newNode = new Node<T>(theNode.prev, value, theNode);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        return (T) findNode(index).item;
    }

    @Override
    public T set(T value, int index) {
        T oldValue = (T) findNode(index).item;
        findNode(index).item = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Node xNode = findNode(index);
        T theItem = (T) xNode.item;
        if (xNode.prev == null) {
            first = xNode.next;
        } else {
            xNode.prev.next = xNode.next;
            xNode.prev = null;
        }
        if (xNode.next == null) {
            last = xNode.prev;
        } else {
            xNode.next.prev = xNode.prev;
            xNode.next = null;
        }
        xNode.item = null;
        size--;
        return theItem;
    }

    @Override
    public boolean remove(T object) {
        for (Node node = first; node != null; node = node.next) {
            if (object.equals(node.item)) {
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
        for (int i = 0; i <= index; i++) {
            xNode.next = xNode;
        }
        return xNode;
    }
}
