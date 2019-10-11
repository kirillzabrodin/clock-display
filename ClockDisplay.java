
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String display12HourString;// simulates the actual 12 hour display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplays();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }
    private void updateDisplays()
    {
        updateDisplay();
        update12HourDisplay();
    }
    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplays();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplays();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    public String get12HourTime()
    {
        return display12HourString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
    }
    
    private void update12HourDisplay()
    {
        // An easy waty to decide if it is AM or PM
        String meridiemQualifier = (hours.getValue()/12==0?"AM":"PM");
        // Gets the 12 hour value for the hour
        int hour = hours.getValue()%12;
        // If the value of the hour is 0 use 12 instead
        hour = (hour==0?12:hour);
        /**
         * We can avoid the above two lines by using the general formula
         * ((hours.getValue()+11)%12)+1
         * This will always get the correct values but as it is less 
         * easily identified by humans and thus I opted for the other 
         * solution.
         * 
         * With this solution the parts of the method above this comment
         * would look as follows:
         * String meridiemQualifier = (hours.getValue()/12==0?"AM":"PM");
         * int hour = ((hours.getValue()+11)%12)+1;
         */
        display12HourString = hour + ":" + minutes.getDisplayValue() +
                                meridiemQualifier;
    }
}
