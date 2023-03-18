package Home_Project;

import java.util.Scanner;

/**
 * Class 1/9
 * Main - runner
 */
public class Main {
    public static void main(String[] args) {
        String newLine = System.getProperty("line.separator");
        Scanner scii=new Scanner(System.in);
        PhoneBook phonebook = new PhoneBook();
        Sms sms = new Sms(phonebook.phoneBook);
        Diary diary=new Diary();
        boolean exit = false;
        int trys=0;
        int battarey= (int)(Math.random() * 61) + 10;
        while (!exit) {
            System.out.println(newLine+"Welcome to ur Iphone 18 Extra Pro Max Turbo:");
            System.out.println(newLine+"Wifi - on | Bluetooth - on | Battery - "+battarey+"%");
            System.out.println(newLine+"Please choose the app:");
            System.out.println(newLine+"[1] PhoneBook");
            System.out.println("[2] SMS");
            System.out.println("[3] Diary");
            System.out.println("[4] Exit"+newLine+newLine + "Enter your input here: ");
            String num=scii.nextLine();
            switch (num) {
                case "1" -> phonebook. runPB(diary);
                case "2" -> sms.runSMS();
                case "3" -> diary.runDiary(phonebook);
                case "4" -> exit=true;
                default -> {
                    if (trys == 3) {
                        exit = true;
                        System.out.println("Exiting the Phone");
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