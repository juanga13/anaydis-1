package anaydis.sort.practice.linkedSorters;

import java.util.Comparator;

public class LinkedBubbleSorter {

    public <T> Node<T> sort(Node<T> list, Comparator<T> comparator){
        Node<T> previous = new Node<>();
        Node<T> listEnd = null;
        Node<T> current;

        while (listEnd != list) {
            current = list;
            while (current.next != listEnd) {
                if (comparator.compare(current.value, current.next.value) > 0) {
                    Node<T> aux = current.next;
                    current.next = aux.next;
                    aux.next = current;
                    if (current == list)
                        list = aux;
                    else
                        previous.next = aux;
                    current = aux;
                }
                previous = current;
                current = current.next;
            }
            listEnd = current;
        }
        return list;
    }



}
