package Home_Project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * Class 8/9
 * Event class that contains start-time,end-time,duration,note
 * string converters for day form,time form,date form,full date form
 */
public class Event {
    String newLine = System.getProperty("line.separator");
    SimpleDateFormat fulldateForm = new SimpleDateFormat("dd MMMM yyyy  -  hh:mm");
    SimpleDateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat timeForm = new SimpleDateFormat("HH:mm");
    Date start_time;
    Date end_time;
    String duration;
    String note;

    /**
     * main constructor
     * @param start start time of event
     * @param end end time of event
     * @param Note not about the event
     */
    Event(Date start,Date end,String Note) {
        this.start_time=start;
        this.end_time=end;
        this.note=Note;

        long durationInMilliseconds = Math.abs(end.getTime() - start.getTime());
        long hours = TimeUnit.MILLISECONDS.toHours(durationInMilliseconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMilliseconds) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMilliseconds) % 60;
        this.duration = String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);
    }

    /**
     * getting full info about the planned event
     * @return string with all the data about the event
     */
    public String getEventinfo() {
        return "Event on: "+dateForm.format(this.start_time)+newLine+
                "will start at: "+timeForm.format(this.start_time)+newLine+
                "and will take: "+this.duration+newLine+
                this.note;
    }

    /**
     * getting full info about the planned event for the weekdays print
     * @return string with all the data about the event for the weekdays print
     */
    public String getInfoOnWeekday() {
        return this.fulldateForm.format(this.start_time)+newLine+this.note+newLine+"will take "+ this.duration;
    }
}
