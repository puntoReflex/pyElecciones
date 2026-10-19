package elecciones.solucionB;

public class List {

    private Node first = null;

    public int size() {
        int count = 0;
        Node iterator = this.first;
        while (iterator != null) {
            count++;
            iterator = iterator.getNext();
        }
        return count;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void insertEnd(String value) {
        Node newNode = new Node(value);
        if (this.first == null) {
            this.first = newNode;
        } else {
            Node iterator = this.first;
            while (iterator.getNext() != null) {
                iterator = iterator.getNext();
            }
            iterator.setNext(newNode);
        }
    }

    public boolean contains(String value) {
        Node iterator = this.first;
        while (iterator != null) {
            if (iterator.getValue().equals(value)) {
                return true;
            }
            iterator = iterator.getNext();
        }
        return false;
    }

    public boolean delete(String value) {
        if (this.first == null) {
            return false;
        }
        if (this.first.getValue().equals(value)) {
            this.first = this.first.getNext();
            return true;
        }
        Node previous = this.first;
        Node iterator = this.first.getNext();
        while (iterator != null) {
            if (iterator.getValue().equals(value)) {
                previous.setNext(iterator.getNext());
                return true;
            }
            previous = iterator;
            iterator = iterator.getNext();
        }
        return false;
    }

    public String[] listAll() {
        String[] list = new String[this.size()];
        int count = 0;
        Node iterator = this.first;
        while (iterator != null) {
            list[count] = iterator.getValue();
            count++;
            iterator = iterator.getNext();
        }
        return list;
    }
}
