package anaydis.sort.practice.tp1;

import anaydis.sort.InsertionSorter;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class NameSorter {
    public static void main(String[] args){
        final FullNameGenerator generator = new FullNameGenerator();
        final List<FullName> list = generator.createRandom(20);
        sortFullName(list);
    }


    private static void sortFullName(@NotNull List<FullName> list){
        final Comparator<FullName> firstNameComparator = Comparator.comparing(FullName::getFirstName);
        final Comparator<FullName> lastNameComparator = Comparator.comparing(FullName::getLastName);
        System.out.println("Not sorted: " + list);
        InsertionSorter insertionSorter = new InsertionSorter();
        insertionSorter.sort(firstNameComparator, list);
        System.out.println("\nFirst sort: " + list);
        insertionSorter.sort(lastNameComparator,list);
        System.out.println("\nSecond sort: " + list);
    }
}
