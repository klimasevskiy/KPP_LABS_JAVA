import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Container<T> extends AbstractSequentialList<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    private Node<T> first;

    private Node<T> last;

    private int size;

    public Container() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.data = element;
        return element;
    }

    @Override
    public boolean contains(Object element) {
        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(element)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T element) {
        Node<T> node = new Node<>(element);
        if (size == 0) {
            first = node;
        } else {
            last.setNext(node);
            node.setPrev(last);
        }
        last = node;
        size++;
        return true;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.data;
    }


    private static class Node<E> implements Serializable {

        @Serial
        private static final long serialVersionUID = 21051220407L;

        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data) && Objects.equals(next, node.next) && Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next, prev);
        }
    }

    @Override
    public Iterator<T> iterator() {
        if (size == 0) {
            return Collections.emptyIterator();
        }
        return new Iterator<>() {
            private Node<T> currentNode = null;

            @Override
            public boolean hasNext() {
                return currentNode != last;
            }

            @Override
            public T next() {
                if (currentNode == null) {
                    currentNode = first;
                    return currentNode.data;
                }
                if (currentNode.next == null) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode.next;
                return currentNode.data;
            }

            @Override
            public void remove() {
                if (currentNode == null) {
                    throw new IllegalStateException();
                }
                if (currentNode.prev == null) {
                    first = currentNode.next;
                } else {
                    currentNode.prev.next = currentNode.next;
                }
                if (currentNode.next == null) {
                    last = currentNode.prev;
                } else {
                    currentNode.next.prev = currentNode.prev;
                }
                currentNode = currentNode.prev;
                size--;
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return new ListIterator<>() {
            private Node<T> currentNode = null;
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (currentNode == null) {
                    currentNode = first;
                    currentIndex++;
                    return currentNode.data;
                }
                if (currentNode.next == null) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode.next;
                currentIndex++;
                return currentNode.data;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public T previous() {
                if (currentNode == null) {
                    currentNode = last;
                    currentIndex--;
                    return currentNode.data;
                }
                if (currentNode.prev == null) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode.prev;
                currentIndex--;
                return currentNode.data;
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (currentNode == null) {
                    throw new IllegalStateException();
                }
                if (currentNode.prev == null) {
                    first = currentNode.next;
                } else {
                    currentNode.prev.next = currentNode.next;
                }
                if (currentNode.next == null) {
                    last = currentNode.prev;
                } else {
                    currentNode.next.prev = currentNode.prev;
                }
                currentNode = currentNode.prev;
                size--;
            }

            @Override
            public void set(T t) {
                if (currentNode == null) {
                    throw new IllegalStateException();
                }
                currentNode.data = t;
            }

            @Override
            public void add(T t) {
                Node<T> node = new Node<>(t);
                if (currentNode == null) {
                    first = node;
                    last = node;
                } else {
                    node.next = currentNode.next;
                    node.prev = currentNode;
                    if (currentNode.next == null) {
                        last = node;
                    } else {
                        currentNode.next.prev = node;
                    }
                    currentNode.next = node;
                }
                currentNode = node;
                size++;
            }
        };
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Container<?> container = (Container<?>) o;
        return size == container.size && Objects.equals(first, container.first) && Objects.equals(last, container.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), first, last, size);
    }
}