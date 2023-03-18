package Home_Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class 3/9
 * Phonebook class that contains arraylist of Contact objects which is the main phonebook of the user
 */
public class PhoneBook {
    /**
     * line breaker
     */
    String newLine = System.getProperty("line.separator");
    public ArrayList<Contact> phoneBook = new ArrayList<>();

    public PhoneBook(ArrayList<Contact> pb) {
        this.phoneBook=pb;
    }

    public PhoneBook() {

    }
    public Contact chooseContact() {

        boolean foundi = false;
        ArrayList<Contact> foundii;
        Contact receiver = new Contact();
        while (!foundi) {
            Scanner nameIn = new Scanner(System.in);
            System.out.println("Enter the name of the contact : ");
            String contactName = nameIn.nextLine();
            foundii = this.getContact(contactName);
            int i = 0;
            if (foundii.isEmpty()) {
                System.out.println("No contact by the name " + contactName + newLine + "Please try again");
            } else if (foundii.size() == 1) {
                receiver = foundii.get(0);
                foundi = true;
            } else {
                boolean valid = false;
                while (!valid) {
                    i = 0;
                    Scanner scanier=new Scanner(System.in);
                    for (Contact c : foundii) {
                        System.out.println("[" + (i + 1) + "] " + c.getFullName() + " - " + c.getPhoneNumber() + " - " + c.getCurrentCompany() + newLine)
                        ;
                        i++;
                    }
                    System.out.println("Enter the option of the contact: ");
                    try {
                        int option = scanier.nextInt();
                        receiver = foundii.get(option - 1);
                        foundi = true;
                    } catch (IndexOutOfBoundsException | InputMismatchException e) {
                        System.out.println("Enter a valid option"+newLine+newLine);
                        continue;
                    }
                    valid=true;
                }
            }
        }
        return receiver;
    }
        /**
         * method that add a new contact by the input of the user
         */

        public void addNewContact() {

            Scanner input = new Scanner(System.in);
            boolean added = false;
            String firstName = "";
            String middleName = "";
            String lastName = "";
            String phone = "";
            String company = "";

            while (!added) {
                System.out.println("Enter the first name of the new contact: ");
                if (input.hasNext("[A-Za-z]*")) {
                    firstName = input.next();
                } else {
                    System.out.println("Error, please try again");
                    continue;
                }
                Scanner middle=new Scanner(System.in);
                System.out.println("Enter the middle name of the new contact (if you don't have one just press ENTER): ");
                middleName = middle.nextLine();
                if (middleName.equals("")) {
                } else if (middleName.matches("[A-Za-z]*")) {
                } else {
                    System.out.println("Error, please try again");
                    continue;
                }
                System.out.println("Enter the last name of the new contact: ");
                if (input.hasNext("[A-Za-z]*")) {
                    lastName = input.next();
                } else {
                    System.out.println("Error, please try again");
                    continue;
                }
                System.out.println("Enter the phone of the new contact: ");
                if (input.hasNext()) {
                    int num = input.nextInt();
                    if (num > 0) {
                        phone = String.valueOf(num);
                    }
                } else {
                    System.out.println("Error, please try again");
                    continue;
                }
                System.out.println("Enter the current company of the new contact: ");
                if (input.hasNext("[A-Za-z]*")) {
                    company = input.next();
                } else {
                    System.out.println("Error, please try again");
                    continue;
                }
                int i = 0;
                for (Contact contact : this.phoneBook) {
                    if (contact.getFullName().equals(firstName + " " + middleName + " " + lastName)) {
                        System.out.println("Error, there is already a contact by that full name");
                        break;
                    } else if (contact.getPhoneNumber().equals(phone)) {
                        System.out.println("Error, there is already a contact by that phone number");
                        break;
                    } else i++;
                    ;
                }
                if (i == this.phoneBook.size()) {
                    Contact c1 = new Contact(firstName, middleName, lastName, phone, company);
                    this.phoneBook.add(c1);
                    added = true;
                } else if (!askTryAgain()) {
                    added = true;
                }

            }
        }


    /**
     * method for locating a specific contact from the arraylist
     * @param name the first object out of the arraylist with that name will be returned
     * @return the info about that contact
     */
    public ArrayList<Contact> getContact(String name) {
        ArrayList<Contact> founds=new ArrayList<>();
        for (Contact contact:this.phoneBook){
            if (contact.firstName.equals(name)||contact.middleName.equals(name)||contact.lastName.equals(name)){
                founds.add(contact);
            }
        }
        return founds;
    }
/**
 * method for adding a new contact object to the phonebook arraylist
 * @param contact the contact that wished to be added
 */
    public void addContact(Contact contact) {
        this.phoneBook.add(contact);
    }
    /**
     * method for printing the phonebook in this object
     */
    public void printPhoneBook() {
        String newLine = System.getProperty("line.separator");
        for (Contact contact : this.phoneBook) {
            System.out.println(contact.getFullName() + " - " + contact.phoneNumber + " - " + contact.currentCompany);
        }
    }
    /**
     * method for printing a phonebook that is given
     * @param pb the wished to be printed
     */
    public static void printPhoneBookEntered(ArrayList<Contact> pb) {
        String newLine = System.getProperty("line.separator");
        for (Contact contact : pb) {
            System.out.println(contact.getFullName() + " - " + contact.phoneNumber + " - " + contact.currentCompany + newLine);
        }

    }
    /**
     * method for understanding if the user wants to try the action again or not
     */
    private static boolean askTryAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to try again? (y/n)");
        String answer = scanner.nextLine();
        return answer.equals("y");
    }

    /**
     * used in case 10 to validate the data from a text file with contact's data
     * @param s parameter from the file about the contact
     * @return true if the parameter is a string
     */
    private static boolean isString(String s) {
        return s.matches("[a-zA-Z]+");
    }
    /**
     * used in case 10 to validate the data from a text file with contact's data
     * @param s parameter from the file about the contact
     * @return true if the parameter is a numeric
     */
    private static boolean isNumeric(String s) {
        return s.matches("\\d+");
    }
    /**
     * running method
     * in case 2 there is a validator for if a contact that is about to be deleted has chat history with you, the validation will be in the path :
     * 'C:\Users\USER\Desktop\My First Project\Home_Project\text_files_examples\Uploading_Existing_chat _history\' - if that path contains a file with the phone number with '..._ChatHistory.txt' for example '0548567589_ChatHistory.txt'
     * if you want to change the path, jump to line 255
     * @param diary the diary arraylist that is used for deleting contact and checking if he had a meeting with you
     */
    public void runPB(Diary diary) {
        boolean Exit = false;
        int trys = 0;
        Scanner input = new Scanner(System.in);
        String newLine = System.getProperty("line.separator");
        while (!Exit) {
            System.out.println("P H O N E  -  B O O K" +
                    newLine + newLine+"Please enter the number of one of the options below :" +
                    newLine + newLine+"[1] Add a new Contact." +
                    newLine + "[2] Delete a contact by his name" +
                    newLine + "[3] Show me all the contacts by order" +
                    newLine + "[4] Locate a contact" +
                    newLine + "[5] Sort the Phone Book by name in ascending order" +
                    newLine + "[6] Sort the Phone Book by phone number in descending order" +
                    newLine + "[7] Arrange the phone book in reverse order" +
                    newLine + "[8] Remove duplicates (Contacts with the same name phone number)" +
                    newLine + "[9] Save the data of the phone book in a text file" +
                    newLine + "[10] Load data of a phone book from a text file" +
                    newLine + "[11] Exit" +
                    newLine+newLine + "Enter your input here: ");
            String num = input.nextLine();
            switch (num) {
                case "1" -> {
                    this.addNewContact();
                    System.out.println("Contact Has Been Added Succesfully");

                }
                case "2" -> {
                    Scanner nameIn = new Scanner(System.in);
                    String contactChatPath;
                    Contact delete=chooseContact();
                    this.phoneBook.remove(delete);
                    System.out.println("Contact Has Been Deleted Succesfully"+newLine);
                    contactChatPath="C:\\Users\\USER\\Desktop\\My_First_Project\\Home_Project\\text_files_examples\\Uploading_Existing_chat_history\\" + delete.getPhoneNumber() + "_ChatHistory.txt";
                    if (new File(contactChatPath).exists()) {
                        new File(contactChatPath).delete();
                        System.out.println("Chat history with that contact has been deleted as well" + newLine);
                    }
                    for (Object event:diary.diary) {
                        if (event instanceof Meeting) {
                            ((Meeting) event).contact.equals(delete);
                            System.out.println("Deleting"+newLine+((Meeting) event).getMeetinginfo()+newLine);
                        }
                    }
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner scan = new Scanner(System.in);
                    scan.nextLine();
                    }

                case "3" -> {
                    this.printPhoneBook();
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner scan = new Scanner(System.in);
                    scan.nextLine();
                }

                case "4" -> {
                    boolean f = false;
                    Scanner nameIn = new Scanner(System.in);
                    while (!f) {
                        System.out.println("Enter the name of the contact you would like to locate: ");
                        String name = nameIn.nextLine();
                        ArrayList<Contact> founds = this.getContact(name);
                        if (founds.isEmpty()) {
                            System.out.println("Sorry the is no such contact by the name " + name);
                            if (askTryAgain()) {
                                continue;
                            } else {
                                f = true;
                            }
                        } else {
                            printPhoneBookEntered(founds);
                            System.out.println(newLine + "Press ENTER to return to main menu");
                            Scanner scanner = new Scanner(System.in);
                            scanner.nextLine();
                            f = true;
                        }
                    }
                }
                case "5" -> {

                    Collections.sort(this.phoneBook, new NameSorter());
                    this.printPhoneBook();
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner scan = new Scanner(System.in);
                    scan.nextLine();


                }
                case "6" -> {
                    Collections.sort(this.phoneBook, new PhoneRevSorter());
                    this.printPhoneBook();
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner scan = new Scanner(System.in);
                    scan.nextLine();
                }
                case "7" -> {

                    System.out.println("Before the reversion: " + newLine);
                    this.printPhoneBook();
                    Collections.reverse(this.phoneBook);
                    System.out.println(newLine+newLine);
                    System.out.println("After the reversion: " + newLine);
                    this.printPhoneBook();
                    System.out.println(newLine+newLine);
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner inpt = new Scanner(System.in);
                    inpt.nextLine();
                }
                case "8" -> {
                    System.out.println("Looking for Duplicates");
                    ArrayList<Contact> dups = new ArrayList<>();
                    this.phoneBook.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > 1L).map(e -> e.getKey()).collect(Collectors.toList()).forEach(contact -> dups.add(contact));
                    printPhoneBookEntered(dups);
                    for (Contact c : dups) {
                        this.phoneBook.remove(c);
                    }
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner inpit = new Scanner(System.in);
                    inpit.nextLine();
                }
                case "9" -> {
                    Scanner input1 = new Scanner(System.in);
                    System.out.println("Enter the path of the folder you would like the file to be saved");
                    String path = input1.nextLine();
                    System.out.println("Enter the name you would like the file to be called(for a text file add .txt at the end) : ");
                    String fileName = input1.nextLine();
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(new FileOutputStream(path + "\\" + fileName));
                        for (Contact cont : this.phoneBook)
                            writer.println(cont.firstName + "," + cont.middleName + "," + cont.lastName + "," + cont.phoneNumber + "," + cont.currentCompany + newLine);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner inpt = new Scanner(System.in);
                    inpt.nextLine();
                }
                case "10" -> {
                    Scanner path = new Scanner(System.in);
                    System.out.println("Enter the path for the file: ");
                    String filePath = path.nextLine();
                    Contact contact;
                    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] parts = line.split(",");
                            if (parts.length == 5 && isString(parts[0]) && isString(parts[1]) && isString(parts[2]) && isNumeric(parts[3]) && isString(parts[4])) {
                                contact = new Contact(parts[0], parts[1], parts[2], parts[3], parts[4]);
                            } else if (parts.length == 4 && isString(parts[0]) && isString(parts[1]) && isNumeric(parts[2]) && isString(parts[3])) {
                                contact = new Contact(parts[0],parts[1], parts[2], parts[3]);
                            } else {
                                System.err.println("Invalid format: " + line);
                                continue; // skip to next line
                            }
                            this.addContact(contact);
                            System.out.println(contact.firstName + " has been added to the phonebook");
                        }
                        System.out.println(newLine + "Press ENTER to return to the main menu");
                        Scanner inpiit = new Scanner(System.in);
                        inpiit.nextLine();
                    } catch (IOException | ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }

                case "11" -> {
                    Exit = true;
                    System.out.println("Exiting the Phone Book");
                }
                default -> {
                    if (trys == 3) {
                        Exit = true;
                        System.out.println("Exiting the Phone Book");
                    } else {
                        System.out.println("Sorry the option you've chosen is not available");
                        trys++;
                        System.out.println("Press ENTER to return to the main menu");
                        Scanner inpat = new Scanner(System.in);
                        inpat.nextLine();
                    }
                }
            }

        }

    }
}


