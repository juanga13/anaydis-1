package anaydis.sort.practice.linkedSorters;

import java.util.Comparator;

public class LinkedInsertionSorter {

    public <T> Node<T> sort(Node<T> list, Comparator<T> comparator){
        Node<T> result = null;
        Node<T> current = list;

        while (current != null){
            Node<T> next = current.next;
            result = insertSorted(result, current, comparator);
            current = next;
        }

        return result;

    }

    public <T> Node<T> insertSorted(Node<T> list, Node<T> node, Comparator<T> comparator){
        Node<T> current = list;
        Node<T> previous = null;
        while (current != null && comparator.compare(node.value, current.value) > 0){
            previous = current;
            current = current.next;
        }
        node.next = current;
        if(current == list){
            return node;
        }
        previous.next = node;
        return list;
    }

}
