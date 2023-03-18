package Home_Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
/**
 * Class 7/9
 * Diary class that extends phonebook class
 * contains Arraylist of objects which is the diary holding the events,
 * string converters for day form,time form,date form,full date form
 */
public class Diary extends PhoneBook {

    SimpleDateFormat fulldateForm = new SimpleDateFormat("dd MMMM yyyy  -  hh:mm");
    SimpleDateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat timeForm = new SimpleDateFormat("HH:mm");
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
    ArrayList<Object> diary = new ArrayList<>();

    public Diary() {
    }

    /**
     * method for checking if a specific string of a date given in the right format that given
     * @param format the format of the date that should be 'dd MMMM yyyy'
     * @param value the string that was given as a date
     * @param locale current localization
     * @return true if the string is a date in the format that is given, false if the string is not a date in the format given
     */
    public static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                    System.out.println("error");
                }
            }
        }

        return false;
    }

    /**
     * method for sorting events by the order of their show
     * @param eventsMeetings arraylist of events that needs to be sorted
     * @return the sorted arraylist
     */
    public static ArrayList<Object> sortEventsMeetings(ArrayList<Object> eventsMeetings) {
        Collections.sort(eventsMeetings, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Event && o2 instanceof Meeting) {
                    return ((Event) o1).start_time.compareTo(((Meeting) o2).start_time);
                } else if (o1 instanceof Meeting && o2 instanceof Event) {
                    return ((Meeting) o1).start_time.compareTo(((Event) o2).start_time);
                } else if (o1 instanceof Event && o2 instanceof Event) {
                    return ((Event) o1).start_time.compareTo(((Event) o2).start_time);
                } else {
                    return ((Meeting) o1).start_time.compareTo(((Meeting) o2).start_time);
                }
            }
        });
        return eventsMeetings;
    }

    /**
     * case 1 from the runner
     * @param phoneBook the phonebook object that is used in the next actions
     */
    public void case1(PhoneBook phoneBook) {
    boolean validation = false;
    Scanner scan = new Scanner(System.in);
    while (!validation) {
        System.out.println("Does the event includes a meeting ? (Y/N) ");
        String answer = scan.nextLine();
        Date start_time = null;
        Date end_time = null;
        if (answer.equals("y")) {
            Contact cont = phoneBook.chooseContact();
            String date;
            boolean valid = false;
            while (!valid) {
                System.out.println("Enter the date of the meeting" + newLine + " (for example '31 May 2023')");
                date = scan.nextLine();
                if (isValidFormat("dd MMMM yyyy", date, Locale.ENGLISH) || isValidFormat("d MMMM yyyy", date, Locale.ENGLISH)) {
                    try {
                        Date parsedDate = this.dateForm.parse(date);

                        if (parsedDate.before(new Date())) {
                            System.out.println("Invalid date"+newLine+"(can't use a past time)");
                            continue;
                        }
                    } catch (ParseException e) {
                        System.out.println("Invalid Date format, try again");
                        continue;
                    }
                } else {
                    System.out.println("Invalid Date format, try again");
                    continue;
                }
                boolean validTime = false;
                while (!validTime) {
                    System.out.println("Enter the start time of the meeting"+newLine+"(for example '17:38')");
                    String startT = scan.nextLine();
                    try {
                        Date startTime = this.timeForm.parse(startT);
                        validTime = true;
                        start_time = new Date(date + " " + startT);
                    } catch (ParseException e) {
                        System.out.println("Invalid time format"+newLine+"(should be HH:mm)");
                    }
                }

                boolean valid2 = false;
                while (!valid2) {
                    System.out.println("Enter the end time of the meeting" + newLine +
                            "(for example '21:12'");
                    String endT = scan.nextLine();
                    end_time = new Date(date + " " + endT);
                    if (end_time.before(start_time)) {
                        System.out.println("invalid date"+newLine+"(the end time doesn't come before start time)" + newLine +
                                "Try again" + newLine);
                    } else {
                        valid2 = true;
                    }
                }
                Meeting meet = new Meeting(cont, start_time, end_time);
                this.diary.add(meet);
                System.out.println("Scheduled a meeting with " + cont.getFullName() + " - " + cont.getPhoneNumber() + " - " + cont.getCurrentCompany() + newLine +
                        "on " + this.dateForm.format(start_time) + ", at " + this.timeForm.format(start_time) + newLine +
                        "and the meeting will take " + meet.duration + newLine);
                valid = true;
            }
            validation = true;
        } else if (answer.equals("n")) {
            String date2 = null;
            boolean valid4 = false;
            while (!valid4) {
                System.out.println("Enter the date of the event" + newLine + " (for example '31 May 2023')");
                date2 = scan.nextLine();
                if (isValidFormat("dd MMMM yyyy", date2, Locale.ENGLISH) || isValidFormat("d MMMM yyyy", date2, Locale.ENGLISH)) {
                    try {
                        Date parsedDate = this.dateForm.parse(date2);
                        if (parsedDate.before(new Date())) {
                            System.out.println("Invalid date"+newLine+"(can't use a past time)");
                        } else valid4 = true;
                    } catch (ParseException | IllegalArgumentException e) {
                        System.out.println("Invalid Date format, try again");
                    }
                } else {
                    System.out.println("Invalid Date format, try again");
                }
            }
            boolean validTime2 = false;
            while (!validTime2) {
                System.out.println("Enter the start time of the event"+newLine+"(for example '17:38')");
                String startT = scan.nextLine();
                if (isValidFormat("HH:mm", startT, Locale.ENGLISH)) {
                    try {
                        Date startTime = this.timeForm.parse(startT);
                        validTime2 = true;
                        start_time = new Date(date2 + " " + startT);
                    } catch (ParseException | IllegalArgumentException e) {
                        System.out.println("Invalid time format"+newLine+"(should be HH:mm)");
                    }
                } else {
                    System.out.println("Invalid time format"+newLine+"(should be HH:mm)");
                }
            }


            boolean valid3 = false;
            while (!valid3) {
                System.out.println("Enter the end time of the event" + newLine +
                        "(for example '21:12'");
                String endT = scan.nextLine();
                if (isValidFormat("HH:mm", endT, Locale.ENGLISH)) {
                    end_time = new Date(date2 + " " + endT);
                    if (end_time.before(start_time)) {
                        System.out.println("invalid date"+newLine+"(the end time doesn't come before start time)" + newLine +
                                "Try again" + newLine);
                    } else {
                        valid3 = true;
                    }
                }
            }
            System.out.println("Enter a note about that event : ");
            String note = scan.nextLine();
            Event event = new Event(start_time, end_time, note);
            this.diary.add(event);
            System.out.println("Scheduled an event of " + event.note + newLine +
                    "on " + this.dateForm.format(start_time) + ", at " + this.timeForm.format(start_time) + newLine +
                    "and the event will take " + event.duration + newLine);
            validation = true;
        } else {
            System.out.println("Please enter a valid option");
        }
    }
    System.out.println(newLine + "Press ENTER to return to the main screen of the Diary app");
    Scanner inapt = new Scanner(System.in);
    inapt.nextLine();
}
    /**
     * case 2 from the runner
     */
    private void case2() {
        Scanner scuni = new Scanner(System.in);
        boolean run = false;
        Event event;
        Event deleteEvent;
        Meeting meet = null;
        while (!run) {
            System.out.println("Does the event includes a meeting ? (Y/N) ");
            String answer = scuni.nextLine();
            if (answer.equals("y") || answer.equals("Y")) {
                ArrayList<Meeting> meetings = new ArrayList<>();
                System.out.println("Enter the name of the contact :");
                String name = scuni.nextLine();
                for (Object obj : this.diary) {
                    if (obj instanceof Meeting) {
                        meet = (Meeting) obj;
                        if (meet.contact.getFullName().contains(name)) {
                            meetings.add(meet);
                        }
                    }

                }
                if (meetings.isEmpty()) {
                    System.out.println("You have no meetings with contact by the name " + name + newLine + "Please try again");
                } else {
                    for (int i = 0; i < meetings.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + newLine + meetings.get(i).getMeetinginfo() + newLine + newLine);
                    }
                    Scanner scanii = new Scanner(System.in);
                    boolean validatione = false;
                    while (!validatione) {
                        try {
                            System.out.println("Enter the option of the meeting you want to delete: ");
                            int option = scanii.nextInt();
                            meet = meetings.get(option - 1);
                            validatione = true;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Please choose a valid option");
                        }
                    }

                    Scanner scaniil = new Scanner(System.in);
                    System.out.println("Are you sure you want to delete this meeting ? " + newLine + "(Y/N)");
                    String yon = scaniil.nextLine();
                    if (yon.equals("y") || yon.equals("Y")) {
                        this.diary.remove(meet);
                        System.out.println("the meeting has been deleted");
                        System.out.println(newLine + "Press ENTER to return to the main screen of the Diary app");
                        Scanner inapt = new Scanner(System.in);
                        inapt.nextLine();
                        run = true;
                    } else if (!(yon.equals("n")) || !(yon.equals("N"))) {
                        System.out.println("please try again");
                    }
                }
            } else if (answer.equals("n") || answer.equals("N")) {
                Scanner scaniili = new Scanner(System.in);
                ArrayList<Event> events = new ArrayList<>();
                System.out.println("Please enter the note you left for that event you want to delete: ");
                String note = scaniili.nextLine();
                for (Object obj : this.diary) {
                    if (obj instanceof Event) {
                        event = (Event) obj;
                        if (event.note.contains(note)) {
                            events.add((Event) obj);
                        }
                    }
                }
                if (events.isEmpty()) {
                    System.out.println("You have no events with the note '" + note + "'" + newLine + "Please try again");
                } else {
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + newLine + events.get(i).getEventinfo() + newLine + newLine);
                    }
                    Scanner scanuu = new Scanner(System.in);
                    System.out.println("Enter the option of the event you want to delete: ");
                    int option = scanuu.nextInt();
                    if ((option) > events.size() || (option) < 1) {
                        System.out.println("you need to choose a valid option, try again");
                    } else {
                        deleteEvent = events.get(option - 1);
                        Scanner scanuui = new Scanner(System.in);
                        System.out.println("Are you sure you want to delete this event ? " + newLine + "(Y/N)");
                        String yon = scanuui.nextLine();

                        if (yon.equals("y") || yon.equals("Y")) {
                            this.diary.remove(deleteEvent);
                            System.out.println("the event has been deleted");
                            System.out.println(newLine + "Press ENTER to return to the main screen of the Diary app");
                            Scanner inapit = new Scanner(System.in);
                            inapit.nextLine();
                            run = true;

                        } else if (!(yon.equals("n")) || !(yon.equals("N"))) {
                            System.out.println("please try again");
                        }
                    }
                }
            } else {
                System.out.println("Enter a valid option");
            }
        }
    }
    /**
     * case 3 from the runner
     * @param phoneBook the phonebook object that is used in the next actions
     */
    private void case3(PhoneBook phoneBook) {
        String date;
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        Date parsedDate = null;
        ArrayList<Object> sameDayEvents = new ArrayList<>();
        while (!valid) {
            System.out.println("Enter the date you would like to see its events displayed" + newLine + " (for example '31 May 2023')");
            date = scan.nextLine();
            if (isValidFormat("dd MMMM yyyy", date, Locale.ENGLISH) || isValidFormat("d MMMM yyyy", date, Locale.ENGLISH)) {
                try {
                    parsedDate = this.dateForm.parse(date);
                    valid = true;
                } catch (ParseException | IllegalArgumentException e) {
                    System.out.println("Invalid Date format, try again");

                }
            } else {
                System.out.println("Invalid Date format, try again");

            }
        }
        for (Object obj : this.diary) {
            if (obj instanceof Meeting) {
                if (this.dateForm.format(parsedDate).equals(this.dateForm.format(((Meeting) obj).start_time))) {
                    sameDayEvents.add(obj);
                }
            } else if (obj instanceof Event) {
                if (this.dateForm.format(parsedDate).equals(this.dateForm.format(((Event) obj).start_time))) {
                    sameDayEvents.add(obj);
                }
            }
        }
        if (sameDayEvents.isEmpty()) {
            System.out.println("There are no events in " + dateForm.format(parsedDate) + newLine);
        } else {
            int meetings = 0;
            int events = 0;
            for (Object obj : sameDayEvents) {
                if (obj instanceof Meeting) {
                    meetings++;
                } else if (obj instanceof Event) {
                    events++;
                }
            }
            System.out.println("You have " + meetings + " Meetings and " + events + " Events on " + dateForm.format(parsedDate) + newLine);
            sortEventsMeetings(sameDayEvents);
            for (int i = 0; i < sameDayEvents.size(); i++) {
                if (sameDayEvents.get(i) instanceof Meeting) {
                    Contact cont = ((Meeting) sameDayEvents.get(i)).contact;
                    System.out.println(this.timeForm.format(((Meeting) sameDayEvents.get(i)).start_time) + newLine +
                            "Meeting with " + cont.getFullName() + newLine +
                            "Will take " + ((Meeting) sameDayEvents.get(i)).duration + newLine + newLine);
                } else if (sameDayEvents.get(i) instanceof Event) {
                    System.out.println(this.timeForm.format(((Event) sameDayEvents.get(i)).start_time) + newLine +
                            ((Event) sameDayEvents.get(i)).note + newLine +
                            "Will take " + ((Event) sameDayEvents.get(i)).duration + newLine + newLine);
                }
            }
        }
        System.out.println(newLine + "Press ENTER to return to the main screen of the Diary app");
        Scanner inapit = new Scanner(System.in);
        inapit.nextLine();
    }
    /**
     * case 4 from the runner
     * @param phoneBook the phonebook object that is used in the next actions
     */
    private void case4(PhoneBook phoneBook) {
        Contact contact = phoneBook.chooseContact();
        ArrayList<Object> meetingList = new ArrayList<>();
        for (Object obj : this.diary) {
            if (obj instanceof Meeting && ((Meeting) obj).contact.equals(contact)) {
                meetingList.add((Meeting) obj);
            }

        }
        sortEventsMeetings(meetingList);
        System.out.println("Here is your list of your scheduled meetings with " + contact.getFullName() + newLine);
        for (Object meet : meetingList) {
            if (meet instanceof Meeting) {
                Meeting meeting = (Meeting) meet;
                System.out.println(this.dateForm.format(meeting.start_time) + newLine +
                        "At " + this.timeForm.format(meeting.start_time) + newLine +
                        "Will take " + meeting.duration + newLine + newLine);
            }

        }
        System.out.println(newLine + "Press ENTER to return to the main screen of the Diary app");
        Scanner inapit = new Scanner(System.in);
        inapit.nextLine();
    }
    /**
     * case 5 from the runner
     * @param phoneBook the phonebook object that is used in the next actions
     */
    private void case5(PhoneBook phoneBook) {
        if (this.diary.size() < 2) {
            System.out.println("You don't have enough events to test if there are coincide ");
        } else {
            this.diary = sortEventsMeetings(this.diary);
            for (int i = 0; i < this.diary.size() - 1; i++) {
                Object currentEvent = this.diary.get(i);
                Date currentEventEndTime = null;
                Date currnetEventStartTime = null;
                if (currentEvent instanceof Meeting) {
                    currentEventEndTime = ((Meeting) currentEvent).end_time;
                    currnetEventStartTime = ((Meeting) currentEvent).start_time;
                } else if (currentEvent instanceof Event) {
                    currentEventEndTime = ((Event) currentEvent).end_time;
                    currnetEventStartTime = ((Event) currentEvent).start_time;
                }
                for (int j = i + 1; j < this.diary.size(); j++) {
                    Object nextEvent = this.diary.get(j);
                    Date nextEventStartTime = null;
                    if (nextEvent instanceof Meeting) {
                        nextEventStartTime = ((Meeting) nextEvent).start_time;
                    } else if (nextEvent instanceof Event) {
                        nextEventStartTime = ((Event) nextEvent).start_time;
                    }

                    if (dateForm.format(nextEventStartTime).equals(dateForm.format(currnetEventStartTime))
                            && nextEventStartTime.before(currentEventEndTime)) {
                        if (currnetEventStartTime.before(nextEventStartTime) || currnetEventStartTime.equals(nextEventStartTime)) {
                            this.diary.remove(nextEvent);
                            if (nextEvent instanceof Meeting) {
                                System.out.println("Deleted" + newLine + ((Meeting) nextEvent).getMeetinginfo() + newLine);
                            } else if (nextEvent instanceof Event) {
                                System.out.println("Deleted" + newLine + ((Event) nextEvent).getEventinfo() + newLine);
                            }
                        } else {
                            this.diary.remove(currentEvent);
                            if (currentEvent instanceof Meeting) {
                                System.out.println("Deleted" + newLine + ((Meeting) currentEvent).getMeetinginfo() + newLine);
                            } else if (currentEvent instanceof Event) {
                                System.out.println("Deleted" + newLine + ((Event) currentEvent).getEventinfo() + newLine);
                            }
                        }
                        j--;
                    }
                }
            }
        }
        System.out.println(newLine + "Press ENTER to return to the main screen of the Diary app");
        Scanner inapit = new Scanner(System.in);
        inapit.nextLine();
    }
    /**
     * case 6 from the runner
     * @param phoneBook the phonebook object that is used in the next actions
     */
    private void case6(PhoneBook phoneBook) {
        if (this.diary.size() == 0) {
            System.out.println("Your diary is empty!");
        } else {
            /**Enter logic here**/
            ArrayList<Object> sunday = new ArrayList<>();
            ArrayList<Object> monday = new ArrayList<>();
            ArrayList<Object> tuesday = new ArrayList<>();
            ArrayList<Object> wednesday = new ArrayList<>();
            ArrayList<Object> thursday = new ArrayList<>();
            ArrayList<Object> friday = new ArrayList<>();
            ArrayList<Object> saturday = new ArrayList<>();
            for (Object obj : this.diary) {
                String weekday = null;
                if (obj instanceof Meeting) {
                    weekday = dayFormat.format(((Meeting) obj).start_time);
                } else if (obj instanceof Event) {
                    weekday = dayFormat.format(((Event) obj).start_time);
                }
                switch (weekday) {
                    case "Sunday" -> {
                        sunday.add(obj);
                    }
                    case "Monday" -> {
                        monday.add(obj);
                    }
                    case "Tuesday" -> {
                        tuesday.add(obj);
                    }
                    case "Wednesday" -> {
                        wednesday.add(obj);
                    }
                    case "Thursday" -> {
                        thursday.add(obj);
                    }
                    case "Friday" -> {
                        friday.add(obj);
                    }
                    case "Saturday" -> {
                        saturday.add(obj);
                    }
                }

            }
            System.out.println("Sunday:" + newLine);
            if (sunday.isEmpty()) {
                System.out.println("You don't have any event on Sunday!" + newLine);
                System.out.println(newLine + newLine);
            } else {
                for (Object obj : sunday) {
                    if (obj instanceof Meeting) {
                        System.out.println(((Meeting) obj).getMeetingOnWeekday() + newLine);
                    } else if (obj instanceof Event) {
                        System.out.println(((Event) obj).getInfoOnWeekday() + newLine);
                    }
                }
                System.out.println(newLine + newLine);
            }
            System.out.println("Monday:" + newLine);
            if (monday.isEmpty()) {
                System.out.println("You don't have any event on Monday!" + newLine);
                System.out.println(newLine + newLine);
            } else {
                for (Object obj : monday) {
                    if (obj instanceof Meeting) {
                        System.out.println(((Meeting) obj).getMeetingOnWeekday() + newLine);
                    } else if (obj instanceof Event) {
                        System.out.println(((Event) obj).getInfoOnWeekday() + newLine);
                    }
                }
                System.out.println(newLine + newLine);
            }
            System.out.println("Tuesday:" + newLine);
            if (tuesday.isEmpty()) {
                System.out.println("You don't have any event on Tuesday!" + newLine);
                System.out.println(newLine + newLine);
            } else {
                for (Object obj : tuesday) {
                    if (obj instanceof Meeting) {
                        System.out.println(((Meeting) obj).getMeetingOnWeekday() + newLine);
                    } else if (obj instanceof Event) {
                        System.out.println(((Event) obj).getInfoOnWeekday() + newLine);
                    }
                }
                System.out.println(newLine + newLine);
            }
            System.out.println("Wednesday:" + newLine);
            if (wednesday.isEmpty()) {
                System.out.println("You don't have any event on Wednesday!" + newLine);
                System.out.println(newLine + newLine);
            } else {
                for (Object obj : wednesday) {
                    if (obj instanceof Meeting) {
                        System.out.println(((Meeting) obj).getMeetingOnWeekday() + newLine);
                    } else if (obj instanceof Event) {
                        System.out.println(((Event) obj).getInfoOnWeekday() + newLine);
                    }
                }
                System.out.println(newLine + newLine);
            }
            System.out.println("Thursday:" + newLine);
            if (thursday.isEmpty()) {
                System.out.println("You don't have any event on Thursday!" + newLine);
                System.out.println(newLine + newLine);
            } else {
                for (Object obj : thursday) {
                    if (obj instanceof Meeting) {
                        System.out.println(((Meeting) obj).getMeetingOnWeekday() + newLine);
                    } else if (obj instanceof Event) {
                        System.out.println(((Event) obj).getInfoOnWeekday() + newLine);
                    }
                }
                System.out.println(newLine + newLine);
            }
            System.out.println("Friday:" + newLine);
            if (friday.isEmpty()) {
                System.out.println("You don't have any event on Friday!" + newLine);
                System.out.println(newLine + newLine);
            } else {
                for (Object obj : friday) {
                    if (obj instanceof Meeting) {
                        System.out.println(((Meeting) obj).getMeetingOnWeekday() + newLine);
                    } else if (obj instanceof Event) {
                        System.out.println(((Event) obj).getInfoOnWeekday() + newLine);
                    }
                }
                System.out.println(newLine + newLine);
            }
            System.out.println("Saturday:" + newLine);
            if (saturday.isEmpty()) {
                System.out.println("You don't have any event on Thursday!");
                System.out.println(newLine + newLine);
            } else {
                for (Object obj : saturday) {
                    if (obj instanceof Meeting) {
                        System.out.println(((Meeting) obj).getMeetingOnWeekday() + newLine);
                    } else if (obj instanceof Event) {
                        System.out.println(((Event) obj).getInfoOnWeekday() + newLine);
                    }
                }
            }
        }
        System.out.println(newLine + "Press ENTER to return to the main screen of the Diary app");
        Scanner inapit = new Scanner(System.in);
        inapit.nextLine();
    }

    /**
     * running method
     * @param phoneBook the phonebook object that will be used while running
     */
    public void runDiary(PhoneBook phoneBook) {
        Scanner input = new Scanner(System.in);
        String newLine = System.getProperty("line.separator");
        boolean Exit = false;
        int trys = 0;
        while (!Exit) {
            System.out.println("D I A R Y" +
                    newLine + "Please enter the number of one of the options below :" +
                    newLine + "[1] Add a new event." +
                    newLine + "[2] Delete an event" +
                    newLine + "[3] Show me all the events in a specific day (ascending order)" +
                    newLine + "[4] Show me all the meetings with a specific contact (ascending order)" +
                    newLine + "[5] Find events that conflicts with other scheduled events" +
                    newLine + "[6] Show me all the events by their day in the week" +
                    newLine + "[7] Exit"+
            newLine+newLine + "Enter your input here: ");
            String num = input.nextLine();
            switch (num) {
                case "1" -> {
                this.case1(phoneBook);
                }
                case "2" -> {
                    this.case2();
                }
                case "3" -> {
                    this.case3(phoneBook);
                }
                case "4" -> {
                    this.case4(phoneBook);
                }
                case "5" -> {
                    this.case5(phoneBook);
                }
            case "6" -> {
                    this.case6(phoneBook);
            }
            case "7" -> {
                Exit = true;
                System.out.println("Exiting Diary");
            }
            default -> {
                if (trys == 3) {
                    Exit = true;
                    System.out.println("Exiting the Diary");
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
