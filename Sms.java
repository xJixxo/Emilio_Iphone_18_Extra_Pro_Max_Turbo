package Home_Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class 6/9
 * Sms class that extends phonebook class
 */
public class Sms extends PhoneBook {

    Scanner scanner;
    /**
     * the path that will be used by the runner to locate for chat history
     */
    String pathHistory="C:\\Users\\USER\\Desktop\\My_First_Project\\Home_Project\\text_files_examples\\Uploading_Existing_chat_history\\";

    /**
     * Main constructor
     * @param pb the phonebook that will be used in that object
     */
    public Sms(ArrayList<Contact> pb) {
        this.phoneBook = pb;
        this.scanner = new Scanner(System.in);
    }
    static String newLine = System.getProperty("line.separator");
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
     * method for understanding if the user wants to send another message
     */
    private static boolean askAnotherMessage() {
        Scanner dec = new Scanner(System.in);
        System.out.println("Do you want to send another message? (y/n)");
        String answer = dec.nextLine();
        return answer.equals("y");
    }
    /**
     * case 1 from the runner
     */
    void case1() {
        ArrayList<String> chatScreen = new ArrayList<>();
        boolean f = false;
        while (!f) {
            Scanner nameIn = new Scanner(System.in);
            System.out.println("Enter the name of the contact you would like to chat with: ");
            String name = nameIn.nextLine();
            ArrayList<Contact> founds = this.getContact(name);
            if (founds.isEmpty()) {
                System.out.println("Sorry the is no such contact by the name " + name);
                if (!(askTryAgain())) {
                    f = true;
                }
            } else {
                int i = 0;
                for (Contact c : founds) {
                    System.out.println("[" + (i + 1) + "] " + c.getFullName() + " - " + c.getPhoneNumber() + " - " + c.getCurrentCompany() + newLine)
                    ;
                    i++;
                }
                System.out.println("Enter the option of the contact you want to talk with: ");
                int option = nameIn.nextInt();
                Contact receiver = founds.get(option - 1);
                String contactChatHistoryPath = this.pathHistory + receiver.getPhoneNumber() + "_ChatHistory.txt";
                File chatHistory = new File(contactChatHistoryPath);
                if (chatHistory.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(chatHistory))) {
                        String line;
                        System.out.println("Your chat history with " + receiver.getFullName() + " - " + receiver.getPhoneNumber() + " - " + receiver.getCurrentCompany() + newLine);
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    boolean end = false;
                    while (!end) {
                        System.out.println("Enter your text here : ");
                        Scanner inpiit = new Scanner(System.in);
                        String line = "You : " + inpiit.nextLine();
                        System.out.println(line);
                        chatScreen.add(line);
                        if (!(askAnotherMessage())) {
                            end = true;
                        }
                    }

                } else {
                    boolean end2 = false;
                    while (!end2) {
                        System.out.println("Enter your text here : ");
                        Scanner inpiit2 = new Scanner(System.in);
                        String line = "You : " + inpiit2.nextLine();
                        System.out.println(line);
                        chatScreen.add(line);
                        if (!(askAnotherMessage())) {
                            end2 = true;
                        }
                    }
                }
                PrintWriter writer;
                try {
                    writer = new PrintWriter(new FileWriter(chatHistory, true));
                    for (String message : chatScreen) {
                        writer.println(message);
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("This chat was saved at : " + contactChatHistoryPath + newLine);
                f = true;
                /**start chatting*/

            }
        }
    }
    /**
     * case 2 from the runner
     */
    void case2() {
        boolean f = false;
        while (!f) {
            Scanner nameIn = new Scanner(System.in);
            System.out.println("Enter the name of the contact : ");
            String name = nameIn.nextLine();
            ArrayList<Contact> founds = this.getContact(name);
            if (founds.isEmpty()) {
                System.out.println("Sorry the is no such contact by the name " + name);
                if (!(askTryAgain())) {
                    f = true;
                }
            } else {
                int i = 0;
                for (Contact c : founds) {
                    System.out.println("[" + (i + 1) + "] " + c.getFullName() + " - " + c.getPhoneNumber() + " - " + c.getCurrentCompany() + newLine);
                    i++;
                }
                System.out.println("Enter the option of the contact : ");
                int option = nameIn.nextInt();
                Contact deletingChatContact = founds.get(option - 1);
                String contactChatHistoryPath = this.pathHistory + deletingChatContact.getPhoneNumber() + "_ChatHistory.txt";
                if (!(new File(contactChatHistoryPath).exists())) {
                    System.out.println("There is no chat history with " + deletingChatContact.getFullName());
                    if (!(askTryAgain())) {
                        f = true;
                    }
                } else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Are you sure you want to delete your chat history with " + deletingChatContact.firstName + " ? (y/n)");
                    String answer = scanner.nextLine();
                    if (answer.equals("y")) {
                        try {

                            if (new File(contactChatHistoryPath).delete()) {
                                System.out.println("Your chat with " + deletingChatContact.firstName + " was deleted successfully");   //getting and printing the file name
                                f = true;
                            } else {
                                System.out.println("failed to delete the file");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        }
    }
    /**
     * case 3 from the runner
     */
    void case3() {
        boolean f = false;
        while (!f) {
            Scanner nameIn = new Scanner(System.in);
            System.out.println("Enter the name of the contact : ");
            String name = nameIn.nextLine();
            ArrayList<Contact> founds = this.getContact(name);
            if (founds.isEmpty()) {
                System.out.println("Sorry the is no such contact by the name " + name);
                if (!(askTryAgain())) {
                    f = true;
                }
            } else {
                int i = 0;
                for (Contact c : founds) {
                    System.out.println("[" + (i + 1) + "] " + c.getFullName() + " - " + c.getPhoneNumber() + " - " + c.getCurrentCompany() + newLine);
                    i++;
                }
                System.out.println("Enter the option of the contact : ");
                int option = nameIn.nextInt();
                Contact viewingChatContact = founds.get(option - 1);
                String contactChatHistoryPath = this.pathHistory + viewingChatContact.getPhoneNumber() + "_ChatHistory.txt";
                if (!(new File(contactChatHistoryPath).exists())) {
                    System.out.println("There is no chat history with " + viewingChatContact.getFullName());
                    if (!(askTryAgain())) {
                        f = true;
                    }
                } else {
                    try (BufferedReader br = new BufferedReader(new FileReader(contactChatHistoryPath))) {
                        String line;
                        System.out.println("Your chat history with " + viewingChatContact.getFullName() + " - " + viewingChatContact.getPhoneNumber() + " - " + viewingChatContact.getCurrentCompany() + newLine);
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                        f = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        System.out.println(newLine + "Press ENTER to return to the main screen of the SMS app");
        Scanner inpt = new Scanner(System.in);
        inpt.nextLine();
    }
    /**
     * case 4 from the runner
     */
    void case4() {
        boolean f = false;
        while (!f) {
            Scanner inpet = new Scanner(System.in);
            System.out.println("Enter the text you would like to locate : ");
            String find = inpet.nextLine();
            ArrayList<Contact> foundContacts = new ArrayList<>();
            String contactChatHistoryPath2;
            for (Contact c : this.phoneBook) {
                contactChatHistoryPath2 = this.pathHistory + c.getPhoneNumber() + "_ChatHistory.txt";
                File file = new File(contactChatHistoryPath2);
                if (file.exists()) {
                    try {
                        Scanner fileScanner = new Scanner(file);
                        while ((fileScanner.hasNextLine()) && !(foundContacts.contains(c))) {
                            String line = fileScanner.nextLine();
                            if (line.contains(find)) {
                                foundContacts.add(c);
                            }
                        }
                    } catch (FileNotFoundException e) {

                    }
                }
            }
            if (!(foundContacts.isEmpty())) {
                System.out.println("Here are all the contacts that your chat history with them contains the text you just sent" + newLine);
                int i = 0;
                for (Contact c : foundContacts) {
                    System.out.println("[" + (i + 1) + "] " + c.getFullName() + " - " + c.getPhoneNumber() + " - " + c.getCurrentCompany() + newLine);
                    i++;
                }
                Scanner choice = new Scanner(System.in);
                System.out.println("Enter an option of the contacts below : ");
                int option = choice.nextInt();
                Contact viewingChatContact = foundContacts.get(option - 1);
                String contactChatHistoryPath = this.pathHistory + viewingChatContact.getPhoneNumber() + "_ChatHistory.txt";
                try (BufferedReader br = new BufferedReader(new FileReader(contactChatHistoryPath))) {
                    String line;
                    System.out.println("Your chat history with " + viewingChatContact.getFullName() + " - " + viewingChatContact.getPhoneNumber() + " - " + viewingChatContact.getCurrentCompany() + newLine);
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }

                    f = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(newLine + "Press ENTER to return to the main screen of the SMS app");
                Scanner inpkt = new Scanner(System.in);
                inpkt.nextLine();
            } else {
                System.out.println("sorry there is no chat history with that text : '" + find + "'." + newLine);
                if (!(askTryAgain())) {
                    f=true;
                }
            }
        }
    }
    /**
     * case 5 from the runner
     */
    void case5() {
        boolean fv = false;
        while (!fv) {
            ArrayList<Contact> foundContacts2 = new ArrayList<>();
            String contactChatHistoryPath3;
            for (Contact c : this.phoneBook) {
                contactChatHistoryPath3 = this.pathHistory + c.getPhoneNumber() + "_ChatHistory.txt";
                File file = new File(contactChatHistoryPath3);
                if (file.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(contactChatHistoryPath3))) {
                        String line;
                        System.out.println("Your chat history with " + c.getFullName() + " - " + c.getPhoneNumber() + " - " + c.getCurrentCompany() + newLine);
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                        System.out.println(newLine);
                        foundContacts2.add(c);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!(foundContacts2.isEmpty())) {
                fv = true;
            } else System.out.println("Sorry you have no chat history");
            System.out.println(newLine + "Press ENTER to return to the main screen of the SMS app");
            Scanner inpkit = new Scanner(System.in);
            inpkit.nextLine();
            fv = true;

        }
    }

    /**
     * running method
     */
    public void runSMS() {
        boolean Exit = false;
        int trys = 0;
        while (!Exit) {
            System.out.println("S M S" +
                    newLine + newLine+"Please enter the number of one of the options below :" +
                    newLine + newLine+"[1] Send a message to someone" +
                    newLine + "[2] Delete a chat with someone" +
                    newLine + "[3] View the chat with someone" +
                    newLine + "[4] Locate chats with sentence that have been send" +
                    newLine + "[5] View every chat you have" +
                    newLine + "[6] Exit" +
                    newLine+newLine + "Enter your input here: ");
            String num = scanner.nextLine();
            switch (num) {
                case "1" -> {
                    this.case1();
                }
                case "2" -> {
                    this.case2();
                }
                case "3" -> {
                    this.case3();
                }
                case "4" -> {
                    this.case4();
                }
                case "5" -> {
                    this.case5();
                }
                case "6" -> {
                    Exit = true;
                    System.out.println("Exiting the SMS");
                }
                default -> {
                    if (trys == 3) {
                        Exit = true;
                        System.out.println("Exiting SMS");
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