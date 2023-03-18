package Home_Project;

import java.util.Comparator;

/**
 * Class 5/9
 * Comparator class for sorting the contacts by the phone number
 */
public class PhoneRevSorter implements Comparator<Contact> {
    int returnVal=0;
    /**
     * @param one first contact
     * @param another is being compared to the second one
     * @return 1 if another is bigger than one - -1 if one is bigger that another - 0 if they are equal
     */
    @Override
    public int compare(Contact one, Contact another) {
    if(Integer.valueOf(one.getPhoneNumber()) < Integer.valueOf(another.getPhoneNumber())) {
        returnVal=1;
    }
        if(Integer.valueOf(one.getPhoneNumber()) > Integer.valueOf(another.getPhoneNumber())) {
            returnVal=-1;
        }
        if(Integer.valueOf(one.getPhoneNumber()) == Integer.valueOf(another.getPhoneNumber())) {
            returnVal=0;
        }
        return returnVal;
    }
}
