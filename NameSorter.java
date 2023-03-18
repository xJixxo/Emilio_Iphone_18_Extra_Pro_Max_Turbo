package Home_Project;

import java.util.Comparator;

/**
 * Class 4/9
 * Comparator class for sorting the contacts by the name
 */
public class NameSorter implements Comparator<Contact> {
    /**
     * @param contact first contact
     * @param t1 is being compared to the second one
     * @return the one that is alphabetically comes before the other
     */
    @Override
    public int compare(Contact contact, Contact t1) {
        return contact.getFullName().compareTo(t1.getFullName());
    }
}
