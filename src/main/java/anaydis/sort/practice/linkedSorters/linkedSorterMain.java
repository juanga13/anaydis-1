package anaydis.sort.practice.linkedSorters;

public class linkedSorterMain {
    public static void main(String[] args) {

        LinkedSelectionSorter sorter = new LinkedSelectionSorter();
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node8 = new Node<>(8);
        Node<Integer> node9 = new Node<>(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node9;
        node9.next = node5;
        node5.next = node8;
        node8.next = node6;
        node6.next = node7;

        printList(node1);

        node1 = sorter.sort(node1, Integer::compareTo);

        printList(node1);
    }

    private static void printList(Node<Integer> node){
        Node<Integer> a = node;
        System.out.println("LIST");
        while (a != null){
            System.out.println(a.value);
            a = a.next;
        }
        System.out.println("\n");
    }
}
