package anaydis.sort.practice.linkedSorters;

import java.util.Comparator;

public class LinkedSelectionSorter {
    public <T> Node<T> sort(Node<T> list, Comparator<T> comparator){
        Node<T> greaterSorted = new Node<>();
        greaterSorted.next = list;
        boolean firstPass = true;
        while (greaterSorted.next != null){
            Node<T> current = greaterSorted.next;
            Node<T> previousToMin = null;
            Node<T> min = greaterSorted.next;
            while (current.next != null){
                if(comparator.compare(current.next.value, min.value) < 0){
                    previousToMin = current;
                    min = current.next;
                }
                current = current.next;
            }
            if(min != greaterSorted.next){
                previousToMin.next = min.next;
                min.next = greaterSorted.next;
                greaterSorted.next = min;
            }
            if(firstPass){
                firstPass = false;
                list = min;
            }
            greaterSorted = greaterSorted.next;
        }
        return list;
    }
}
