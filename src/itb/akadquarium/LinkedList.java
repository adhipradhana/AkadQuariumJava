package itb.akadquarium;

/**
 * Template class itb.akadquarium.LinkedList.
 * This is a template class for itb.akadquarium.LinkedList.
 *
 * @param <T> is the parameter type
 */
public class LinkedList<T> {
    private int count;
    private Node<T> first;

    /**
     * Instantiate itb.akadquarium.LinkedList.
     */
    public LinkedList() {
        this.count = 0;
    }

    /**
     * Get the object.
     *
     * @param idx is the index
     * @return is the T
     */
    public T get(final int idx) {
        Node<T> it = first;
        for (int i = 0; i < idx; i++) {
            it = it.getNext();
        }
        return it.getData();
    }

    /**
     * Get the total object in the itb.akadquarium.LinkedList.
     *
     * @return integer that represents
     * the total number of object in the itb.akadquarium.LinkedList
     */
    public int getCount() {
        return this.count;
    }

    /**
     * This methods is used for finding val in the list.
     *
     * @param pVal is target
     * @return index of element in list if found.
     */
    private int find(final T pVal) {
        Node<T> it = first;
        int i = 0;
        while (it.getNext() != null && !it.getData().equals(pVal)) {
            it = it.getNext();
            i++;
        }

        if (i == count) {
            return -1;
        } else {
            return i;
        }
    }

    /**
     * This method is used to check whether the
     * itb.akadquarium.LinkedList is empty or not.
     *
     * @return TRUE if the list is empty, otherwise FALSE
     */
    public boolean isEmpty() {
        return (getCount() == 0);
    }

    /**
     * This method will add a new object to the
     * itb.akadquarium.LinkedList of this object.
     *
     * @param pVal is the object.
     */
    public void add(final T pVal) {
        Node<T> node = new Node<>(pVal);
        count++;
        node.setNext(first);
        first = node;
    }

    /**
     * This method will remove the object
     * that is passed in its itb.akadquarium.LinkedList.
     *
     * @param node is the T.
     */
    public void remove(final T node) {
        int i = find(node);
        Node<T> it = first;

        for (int j = 0; j < i - 1; j++) {
            it = it.getNext();
        }

        if (i == 0) {
            count--;
            first = it.getNext();
        } else {
            count--;
            it.setNext(it.getNext().getNext());
        }
    }
}
