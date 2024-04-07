
package application ;

/**
 * @author Kyle Shott
 * 
 * @version 1.0.0 2024-03-28 Initial implementation
 */

@SuppressWarnings( "javadoc" )
public class Day
    {

    public String onToday ;

    public String weekday ;

    public String date ;

    public String startTime ;

    public String endTime ;

    public String jobTitle ;

    public String punchInTime ;

    public String punchOutTime ;

    public String identification ;

    public void clear()
        {
        this.weekday = null ;
        this.date = null ;
        this.startTime = null ;
        this.endTime = null ;
        this.jobTitle = null ;
        this.punchInTime = null ;
        this.punchOutTime = null ;

        }


    public void setId( String set )
        {
        this.identification = set ;

        }


    public String getId()
        {
        return this.identification ;

        }


    public void setOn( String set )
        {
        this.onToday = set ;

        }


    public void setweekday( String set )
        {
        this.weekday = set ;

        }


    public String getWeekday()
        {
        return this.weekday ;

        }


    public void setDate( String set )
        {
        this.date = set ;

        }


    public String getDate()
        {
        return this.date ;

        }


    public void setStartTime( String set )
        {
        this.startTime = set ;

        }


    public String getStartTime()
        {
        return this.startTime ;

        }


    public void setEndTime( String set )
        {
        this.endTime = set ;

        }


    public String getEndTime()
        {
        return this.endTime ;

        }


    public void setjobTitle( String set )
        {
        this.jobTitle = set ;

        }


    public String getJobTitle()
        {
        return this.jobTitle ;

        }


    public void setPunchInTime( String set )
        {
        this.punchInTime = set ;

        }


    public String getPunchInTime()
        {
        return this.punchInTime ;

        }


    public void setPunchOutTime( String set )
        {
        this.punchOutTime = set ;

        }


    public String getPunchOutTime()
        {
        return this.punchOutTime ;

        }

    }
// end class Day