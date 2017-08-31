package anaydis.sort.practice.tp1;

import anaydis.sort.data.DataSetGenerator;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class FullNameGenerator implements DataSetGenerator{
    private final List<String> firstNameDictionary;
    private final List<String> lastNameDictionary;

    public FullNameGenerator(){
        firstNameDictionary = initNameDictionary();
        lastNameDictionary = initLastNameDictionary();
    }

    public FullName createRandomFullName(){
        final Random r = new Random();
        int firstNameIndex = r.nextInt(firstNameDictionary.size());
        int lastNameIndex = r.nextInt(lastNameDictionary.size());
        return new FullName(firstNameDictionary.get(firstNameIndex),
                lastNameDictionary.get(lastNameIndex));
    }

    private List<String> initNameDictionary(){
        return Arrays.asList("ALICE", "AMANDA",
                             "AMY", "ANDREA", "ANGELA", "ANN", "ANNA", "ANNE", "ANNIE",
                             "ASHLEY", "BARBARA", "BETTY", "BEVERLY", "BONNIE", "BRENDA",
                             "CARMEN", "CAROL", "CAROLYN", "CATHERINE", "CHERYL",
                             "CHRISTINA", "CHRISTINE", "CINDY", "CONNIE", "CRYSTAL",
                             "CYNTHIA", "DAWN", "DEBORAH", "DEBRA", "DENISE", "DIANA",
                             "DIANE", "DONNA", "DORIS", "DOROTHY", "EDITH", "EDNA",
                             "ELIZABETH", "EMILY", "EVELYN", "FLORENCE", "FRANCES",
                             "GLADYS", "GLORIA", "GRACE", "HEATHER", "HELEN", "IRENE",
                             "JACQUELINE", "JANE", "JANET", "JANICE", "JEAN", "JENNIFER",
                             "JESSICA", "JOAN", "JOYCE", "JUDITH", "JUDY", "JULIA", "JULIE",
                             "KAREN", "KATHERINE", "KATHLEEN", "KATHRYN", "KATHY", "KELLY",
                             "KIM", "KIMBERLY", "LAURA", "LILLIAN", "LINDA", "LISA", "LOIS",
                             "LORI", "LOUISE", "MARGARET", "MARIA", "MARIE", "MARILYN",
                             "MARTHA", "MARY", "MELISSA", "MICHELLE", "MILDRED", "NANCY",
                             "NICOLE", "NORMA", "PABLIUS", "PAMELA", "PATRICIA", "PAULA", "PEDRO",
                             "PEGGY", "PHYLLIS", "RACHEL", "REBECCA", "RITA", "ROBIN", "ROSA",
                             "ROSE", "RUBY", "RUTH", "SANDRA", "SARA", "SARAH", "SHARON", "SHIRLEY",
                             "STEPHANIE", "SUSAN", "TAMMY", "TERESA", "THERESA", "TIFFANY",
                             "TINA", "TRACY", "VICTORIA", "VIRGINIA", "WANDA", "WENDY");
    }

    private List<String> initLastNameDictionary(){
        return Arrays.asList(
                "Abbott", "Acevedo", "Acosta", "Adams", "Adkins", "Aguilar", "Aguirre", "Albert",
                "Barrera", "Berry", "Best", "Bird", "Bishop", "Black", "Bond", "Bonner", "Boyle",
                "Camacho", "Cameron", "Carter", "Carver", "Collier", "Cox", "Dean", "Dominguez", "Eaton",
                "Edwards", "Elliott", "Farrell", "Flynn", "Foley", "Frost", "Gentry", "Goff", "Griffin",
                "Hancock", "Haney", "Henson", "Hodges", "Hoffman", "Howell", "Hyde", "Jordan", "Joseph",
                "Kerr", "Key", "Kline", "Larsen", "Morales", "Orr", "Peters", "Prince", "Rivera", "Rivers",
                "Ryan", "Sharp", "Suarez", "Turner", "Wallace", "Woodward", "Wooten", "York", "Young",
                "Zamora", "Zimmerman");
    }

    @NotNull
    @Override
    public List createAscending(int length) {
        throw new NotImplementedException();
    }

    @NotNull
    @Override
    public List createDescending(int length) {
        throw new NotImplementedException();
    }

    @NotNull
    @Override
    public List createRandom(int length) {
        final List<FullName> result = new ArrayList<>();
        for (int i = 0; i<length; i++){
            result.add(createRandomFullName());
        }
        return result;
    }

    @NotNull
    @Override
    public Comparator getComparator() {
        throw new NotImplementedException();
    }
}
