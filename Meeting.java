package Home_Project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * Class 9/9
 * Meeting class that extends event class
 * contains contact object,start-time,end-time,duration,
 * string converters for day form,time form,date form,full date form
 */
public class Meeting extends Event {
    String newLine = System.getProperty("line.separator");
    Contact contact;
    SimpleDateFormat fulldateForm = new SimpleDateFormat("dd MMMM yyyy  -  hh:mm");
    SimpleDateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat timeForm = new SimpleDateFormat("HH:mm");
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
    Date start_time;
    Date end_time;
    String duration;

    /**
     * main constructor
     * @param c contact for the meeting
     * @param start start time of meeting
     * @param end end time of meeting
     */
    Meeting(Contact c ,Date start,Date end) {
        super(start,end,"");
        this.contact=c;
        this.start_time=start;
        this.end_time=end;

        long durationInMilliseconds = Math.abs(end.getTime() - start.getTime());
        long hours = TimeUnit.MILLISECONDS.toHours(durationInMilliseconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMilliseconds) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMilliseconds) % 60;
        this.duration = String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);

    }

    /**
     * getting full info about the planned meeting
     * @return string with all the data about the meeting
     */
    String getMeetinginfo() {
        return "Meeting with "+this.contact.getFullName()+","+newLine+
        "on "+this.dateForm.format(start_time)+","+newLine+
        "at "+this.timeForm.format(start_time)+","+newLine+
        "that will take "+this.duration;
    }
    /**
     * getting full info about the planned meeting for the weekdays print
     * @return string with all the data about the meeting for the weekdays print
     */
    String getMeetingOnWeekday() {
        return this.fulldateForm.format(this.start_time)+newLine+"Meeting with "+this.contact.getFullName()+newLine+"will take "+this.duration;
    }


    }

