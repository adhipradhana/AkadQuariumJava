package itb.akadquarium;

/**
 * Class itb.akadquarium.Node.
 * This is a template class for itb.akadquarium.Node.
 *
 * @param <T> is the parameter type
 */
 public class Node<T> {
    /**
     * data.
     */
    private T data;
    /**
     * pointer to next.
     */
    private Node<T> next;
    /**
      * Instantiate itb.akadquarium.Node.
     *
      * @param pData is the T
      */
    public Node(final T pData) {
        this.data = pData;
    }

    /**
     * Set next.
     *
     * @param pNext is the itb.akadquarium.Node of T
     */
    public void setNext(final Node<T> pNext) {
        this.next = pNext;
    }

    /**
     * Set data.
     *
     * @param pData is the T
     */
    public void setData(final T pData) {
        this.data = pData;
    }

     /**
      * Get data.
      *
      * @return T
      */
     public T getData() {
         return (this.data);
     }

     /**
      * Get next.
      *
      * @return the next itb.akadquarium.Node
      */
    public Node<T> getNext() {
        return this.next;
    }
}
