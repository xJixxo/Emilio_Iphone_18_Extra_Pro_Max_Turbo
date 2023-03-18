package Home_Project;
import java.io.Serializable;
import java.util.Objects;
/**
 * Class 2/9
 * Contact class that contains name and phone number
 */
public class Contact  implements Serializable {
    private static final long serialVersionUID = 1L;
    public String firstName;
    public String middleName="";
    public String lastName;
    public String phoneNumber;
    public String currentCompany;

    /**
     * @param firstn the first name of the new contact
     * @param lastN the last name of the new contact
     * @param phone the phone of the new contact
     * @param workplace the current workplace of the contact
     */
    Contact(String firstn,String lastN, String phone,String workplace) {
        this.firstName=firstn;
        this.lastName=lastN;
        this.phoneNumber=phone;
        this.currentCompany=workplace;
    }
         /** @param middleN the middle name of the new contact (not mandatory) **/
    public Contact(String firstN, String middleN, String lastN, String phone, String workplace) {
        this.firstName=firstN;
        this.middleName=middleN;
        this.lastName=lastN;
        this.phoneNumber=phone;
        this.currentCompany=workplace;
    }
    public Contact() {
    }
    /**
     * @return Converts the contact class into a string
     */
    @Override
    public String toString() {
        return new StringBuffer(" Contact's full name : ").append(this.firstName).append(this.middleName).append(this.lastName).append(", Contact's phone number : ").append(this.phoneNumber).append(", Contact's current company : ").append(this.currentCompany).toString();
    }

    /**
     * @return the name of the contact
     */
    public String getFullName(){
            return this.firstName+" "+this.middleName+" "+this.lastName;
    }
    public String getName(){
        return this.firstName+"_"+this.lastName;
    }

    /**
     * @return return the phone number of the contact
     */
    String getPhoneNumber(){
            return this.phoneNumber;
    }
    /**
     * @return return the company of the contact
     */
    String getCurrentCompany(){return  this.currentCompany;}

    /**
     * method for comparing 2 contacts
     * used by case 8 in main class - removing duplicates
     * @param o the second contact that is being compared to the current contact
     * @return true if they are equal - false if they aren't equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact cont = (Contact) o;
        return phoneNumber.equals(cont.phoneNumber) &&
                getFullName().equals(cont.getFullName());
    }

    /**
     * used by case 8 in main class - removing duplicates
     * @return I actually have no idea what this method does - but at least there is a java doc above every method ;)
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFullName(),phoneNumber);
    }
    }

