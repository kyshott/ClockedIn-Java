
package application ;

import org.json.JSONArray ;
import org.json.JSONException ;
import org.json.JSONObject ;

import java.io.IOException ;
import java.time.DayOfWeek ;
import java.time.LocalDate ;
import java.time.format.DateTimeFormatter ;

/**
 * @author Kyle Shott
 * 
 * @version 1.0.0 2024-03-28 Initial implementation
 */

@SuppressWarnings( "javadoc" )
public class Scheduling
    {

    public static LocalDate localDate = LocalDate.now() ;

    public static DayOfWeek weekDay = localDate.getDayOfWeek() ;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ;

    public static String formatToday = localDate.format( formatter ).toString() ;

    static LocalDate nextDay = localDate.plusDays( 1 ) ;
    static LocalDate thirdDay = localDate.plusDays( 2 ) ;
    static LocalDate fourthDay = localDate.plusDays( 3 ) ;
    static LocalDate fifthDay = localDate.plusDays( 4 ) ;
    static LocalDate sixthDay = localDate.plusDays( 5 ) ;
    static LocalDate seventhDay = localDate.plusDays( 6 ) ;
    static LocalDate eighthDay = localDate.plusDays( 7 ) ;

    public static Day Today = new Day() ;
    public static Day next = new Day() ;
    public static Day third = new Day() ;
    public static Day fourth = new Day() ;
    public static Day fifth = new Day() ;
    public static Day sixth = new Day() ;
    public static Day seventh = new Day() ;
    public static Day eighth = new Day() ;

    public static void clearAll()
        {
        next.clear() ;
        third.clear() ;
        fourth.clear() ;
        fifth.clear() ;
        sixth.clear() ;
        seventh.clear() ;
        Today.clear() ;
        eighth.clear() ;

        }


    public static void populateToday() throws JSONException, IOException
        {
        Today.setweekday( weekDay.toString() ) ;
        Today.setDate( localDate.toString() );

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Id,Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" +
                                                               localDate.toString() ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                Today.setjobTitle( record.getString( "Job_Title__c" ) ) ;
                Today.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                Today.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                Today.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                Today.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;
                Today.setOn( record.optString( "OnToday__c", "N/A" ) ) ;
                Today.setId( record.getString( "Id" ) ) ;

                }

            }

        }


    public static void populateNext() throws JSONException, IOException
        {
        next.setweekday( nextDay.getDayOfWeek().toString() ) ;
        next.setDate( nextDay.toString() ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" + nextDay ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                next.setjobTitle( record.getString( "Job_Title__c" ) ) ;
                next.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                next.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                next.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                next.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;

                }

            }

        }


    public static void populateThird() throws JSONException, IOException
        {
        third.setweekday( thirdDay.getDayOfWeek().toString() ) ;
        third.setDate( thirdDay.toString() ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" + thirdDay ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                third.setjobTitle( record.getString( "Job_Title__c" ) ) ;
                third.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                third.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                third.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                third.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;

                }

            }

        }


    public static void populateFourth() throws JSONException, IOException
        {
        fourth.setweekday( fourthDay.getDayOfWeek().toString() ) ;
        fourth.setDate( fourthDay.toString() ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" + fourthDay ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                fourth.setjobTitle( record.getString( "Job_Title__c" ) ) ;
                fourth.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                fourth.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                fourth.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                fourth.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;

                }

            }

        }


    public static void populateFifth() throws JSONException, IOException
        {
        fifth.setweekday( fifthDay.getDayOfWeek().toString() ) ;
        fifth.setDate( fifthDay.toString() ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" + fifthDay ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                fifth.setjobTitle( record.getString( "Job_Title__c" ) ) ;
                fifth.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                fifth.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                fifth.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                fifth.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;

                }

            }

        }


    public static void populateSixth() throws JSONException, IOException
        {
        sixth.setweekday( sixthDay.getDayOfWeek().toString() ) ;
        sixth.setDate( sixthDay.toString() ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" + sixthDay ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                sixth.setjobTitle( record.getString( "Job_Title__c" ) ) ;
                sixth.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                sixth.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                sixth.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                sixth.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;

                }

            }

        }


    public static void populateSeventh() throws JSONException, IOException
        {
        seventh.setweekday( seventhDay.getDayOfWeek().toString() ) ;
        seventh.setDate( seventhDay.toString() ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" + seventhDay ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                seventh.setjobTitle( record.getString( "Job_Title__c" ) ) ; 
                seventh.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                seventh.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                seventh.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                seventh.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;

                }

            }

        }
    
    public static void populateEighth() throws JSONException, IOException
        {
        seventh.setweekday( eighthDay.getDayOfWeek().toString() ) ;
        seventh.setDate( eighthDay.toString() ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+Date__c,Shift_Start__c,Shift_End__C,Job_Title__c,ClockInTime__c,ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Date__c+=+" + eighthDay ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < dayArray.length() ; i++ )
            {
            JSONObject record = dayArray.getJSONObject( i ) ;

                {
                seventh.setjobTitle( record.getString( "Job_Title__c" ) ) ; 
                seventh.setStartTime( record.optString( "Shift_Start__c", "OFF" ) ) ;
                seventh.setEndTime( record.optString( "Shift_End__c", "OFF" ) ) ;
                seventh.setPunchInTime( record.optString( "ClockInTime__c", "N/A" ) ) ;
                seventh.setPunchOutTime( record.optString( "ClockOutTime__c", "N/A" ) ) ;

                }

            }

        }

    }
// end class Scheduling