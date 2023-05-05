package ldlinkedlist;

import java.util.AbstractList;
import java.util.Objects;

/**
 * This class implements a linked list data structure that supports lazy
 * deletion
 *
 * @param <E> the type of elements in this list
 */
public class LDLinkedList<E> extends AbstractList<E> {

    private Node<E> head;
    private int size;
    private int deletedCount;

    /**
     * Constructs an empty linked list.
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        boolean isDeleted;

        Node(E data) {
            this.data = data;
            this.next = null;
            this.isDeleted = false;
        }
    }

    public LDLinkedList() {
        head = null;
        size = 0;
        deletedCount = 0;
    }

    /**
     * Returns the number of non-deleted elements in this list
     *
     * @return the number of non-deleted elements in this list
     */

    @Override
    public int size() {
        int count = 0;
        Node<E> curr = head;
        while (curr != null) {
            if (curr.isDeleted == false) {
                count++;
            }
            curr = curr.next;
        }

        return count;
    }

    /**
     * Returns the element at the specified position in this list
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * 
     */
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        Node<E> curr = head;
        int count = 0;
        while (curr != null) {
            if (curr.isDeleted == false) {
                if (count == index) {
                    return curr.data;
                }
                count++;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * Add element to the end
     *
     * @param element the element to be added to this list
     * @return true
     */
    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    /**
     * Delete the specified element
     *
     *
     * @param element the element to be deleted from this list
     * @return true if the element was found
     */
    @Override
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        }

        @SuppressWarnings("unchecked")
        E e = (E) element; // Type casting to E

        Node<E> current = head;
        boolean deleted = false;

        while (current != null) {
            if (Objects.equals(current.data, e)) { // Use equals for object comparison
                current.isDeleted = true;
                deleted = true;
                break;
            }
            current = current.next;
            deletedCount++;
        }

        if (deleted && deletedCount >= 2) {
            removeLDNodes();
            size--;
        }

        return deleted;
    }

    /**
     * Removes all nodes marked as deleted that have no remaining references.
     */

    public void removeLDNodes() {
        Node<E> current = head;
        Node<E> prev = null;

        while (current != null) {
            if (current.isDeleted) {

                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                current = current.next;
                size--;
            } else {
                prev = current;
                current = current.next;
            }
        }

    }

    public Comment[] getComments() {
        return null;
    }

}
