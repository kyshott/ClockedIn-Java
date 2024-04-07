
package application ;

import org.json.JSONObject ;

import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.io.OutputStream ;
import java.net.HttpURLConnection ;
import java.net.URL ;
import java.nio.charset.StandardCharsets ;

/**
 * A class containing multiple methods for retrieving different pieces of data from
 * Salesforce via the integrated REST API.
 * 
 * @author Kyle Shott
 */
public class Queries
    {

    private static final String INSTANCE_URL = UserData.getInstance() ;
    private static final String ACCESS_TOKEN = UserData.getToken() ;

    /**
     * A method to submit an approval request for an employee scheduled day.
     * 
     * @param date
     *     - the date of the request.
     * @param shiftStart
     *     - the shift start of the request.
     * @param shiftEnd
     *     - the shift end of the request.
     * @param employeeUserId
     *     - the employee username.
     * @param jobTitle
     *     - the employee's job title.
     * @param weekday
     *     - the day of the week of the request.
     */
    @SuppressWarnings( "deprecation" )
    public static void createScheduledDayAndInitiateApproval(String date,
                                                             String shiftStart,
                                                             String shiftEnd,
                                                             String employeeUserId,
                                                             String jobTitle,
                                                             String weekday) {
        try {
            // Step 1: Create and POST a record for Scheduled_Day__c
            String url = INSTANCE_URL + "/services/data/v60.0/sobjects/Scheduled_Day__c/";

            String accessToken = ACCESS_TOKEN;

            String payload = "{\"Date__c\":\"" + date + "\",\"Shift_Start__c\":\"" + shiftStart +
                             "\",\"Shift_End__c\":\"" + shiftEnd + "\",\"Employee_User__c\":\"" +
                             employeeUserId + "\",\"Job_Title__c\":\"" + jobTitle +
                             "\",\"Weekday__c\":\"" + weekday + "\"}";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + accessToken);
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    String responseBody = response.toString();
                    JSONObject responseJSON = new JSONObject(responseBody);
                    String createdRecordId = responseJSON.getString("id");
                    System.out.println("Created Scheduled_Day__c record ID: " + createdRecordId);

                    // Step 2: Initiate the approval process for the created record
                    String approvalUrl = INSTANCE_URL + "/services/data/v60.0/process/approvals/";
                    String approvalPayload = "{\"actionType\":\"Submit\",\"contextActorId\":\"\"," +
                                              "\"contextId\":\"" + createdRecordId +
                                              "\",\"comments\":\"Please review and approve this scheduled day.\"," +
                                              "\"nextApproverIds\":[],\"processDefinitionNameOrId\":\"2F04aak0000003bKn\"," +
                                              "\"skipEntryCriteria\":true}";

                    URL approvalObj = new URL(approvalUrl);
                    HttpURLConnection approvalCon = (HttpURLConnection) approvalObj.openConnection();
                    approvalCon.setRequestMethod("POST");
                    approvalCon.setRequestProperty("Authorization", "Bearer " + accessToken);
                    approvalCon.setRequestProperty("Content-Type", "application/json");
                    approvalCon.setDoOutput(true);

                    try (OutputStream approvalOs = approvalCon.getOutputStream()) {
                        byte[] approvalInput = approvalPayload.getBytes(StandardCharsets.UTF_8);
                        approvalOs.write(approvalInput, 0, approvalInput.length);
                    }

                    int approvalResponseCode = approvalCon.getResponseCode();
                    System.out.println("Approval Response Code : " + approvalResponseCode);

                    if (approvalResponseCode == HttpURLConnection.HTTP_CREATED) {
                        System.out.println("Approval process initiated successfully for Scheduled_Day__c record");
                    } else {
                        // Read and print error response
                        try (BufferedReader approvalReader = new BufferedReader(new InputStreamReader(approvalCon.getErrorStream()))) {
                            StringBuilder approvalResponse = new StringBuilder();
                            String line;
                            while ((line = approvalReader.readLine()) != null) {
                                approvalResponse.append(line);
                            }
                            System.out.println("Approval Error Response: " + approvalResponse.toString());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                // Handle error cases for Scheduled_Day__c creation
                // Read and print error response
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.out.println("Error Response: " + errorResponse.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * An API call to retrieve the user's name from the User object in Salesforce.
     * 
     * @param sqlHeader
     *     - the interchangeable sql header
     * 
     * @return - the sql returned values
     * 
     * @throws IOException
     *     - the exception to throw
     */
    @SuppressWarnings( "deprecation" )
    public static String getQuery( String sqlHeader ) throws IOException
        {
        String restEndpoint = INSTANCE_URL + "/services/data/v60.0/" ;

        String soqlQuery = sqlHeader ;

        URL url = new URL( restEndpoint + soqlQuery ) ;

        HttpURLConnection connection = (HttpURLConnection) url.openConnection() ;
        connection.setRequestMethod( "GET" ) ;

        connection.setRequestProperty( "Authorization", "Bearer " + ACCESS_TOKEN ) ;

        try ( BufferedReader in = new BufferedReader( new InputStreamReader( connection.getInputStream() ) ) )
            {
            String inputLine ;
            StringBuilder response = new StringBuilder() ;

            while ( ( inputLine = in.readLine() ) != null )
                {
                response.append( inputLine ) ;

                }

            in.close() ;

            connection.disconnect() ;

            return response.toString() ;

            }

        }


    /**
     * A query to update the punch out time for the day.
     * 
     * @param timeStamp
     *     - the time stamp to update with.
     */
    @SuppressWarnings( "deprecation" )
    public static void punchOutQuery( String timeStamp )
        {
        try
            {
            String url = INSTANCE_URL + "/services/data/v60.0/sobjects/Scheduled_Day__c/" +
                         Scheduling.Today.getId() + "?_HttpMethod=PATCH" ;

            String accessToken = ACCESS_TOKEN ;

            String payload = "{\"ClockOutTime__c\":\"" + timeStamp + "\"}" ;

            URL obj = new URL( url ) ;
            HttpURLConnection con = (HttpURLConnection) obj.openConnection() ;

            con.setRequestMethod( "POST" ) ;
            con.setRequestProperty( "Authorization", "Bearer " + accessToken ) ;
            con.setRequestProperty( "Content-Type", "application/json" ) ;

            con.setDoOutput( true ) ;
            try ( OutputStream os = con.getOutputStream() )
                {
                byte[] input = payload.getBytes( StandardCharsets.UTF_8 ) ;
                os.write( input, 0, input.length ) ;

                }

            int responseCode = con.getResponseCode() ;
            System.out.println( "Response Code : " + responseCode ) ;

            if ( responseCode == HttpURLConnection.HTTP_OK )
                {
                System.out.println( "Record updated successfully" ) ;

                }

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }


    /**
     * A query to update the punch in time for the day.
     * 
     * @param timeStamp
     *     - the time stamp to update with.
     */
    @SuppressWarnings( "deprecation" )
    public static void punchInQuery( String timeStamp )
        {
        try
            {
            String url = INSTANCE_URL + "/services/data/v60.0/sobjects/Scheduled_Day__c/" +
                         Scheduling.Today.getId() + "?_HttpMethod=PATCH" ;

            String accessToken = ACCESS_TOKEN ;

            String payload = "{\"ClockInTime__c\":\"" + timeStamp + "\"}" ;

            URL obj = new URL( url ) ;
            HttpURLConnection con = (HttpURLConnection) obj.openConnection() ;

            con.setRequestMethod( "POST" ) ;
            con.setRequestProperty( "Authorization", "Bearer " + accessToken ) ;
            con.setRequestProperty( "Content-Type", "application/json" ) ;

            con.setDoOutput( true ) ;
            try ( OutputStream os = con.getOutputStream() )
                {
                byte[] input = payload.getBytes( StandardCharsets.UTF_8 ) ;
                os.write( input, 0, input.length ) ;

                }

            int responseCode = con.getResponseCode() ;
            System.out.println( "Response Code : " + responseCode ) ;

            if ( responseCode == HttpURLConnection.HTTP_OK )
                {
                System.out.println( "Record updated successfully" ) ;

                }

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }


    /**
     * A query to add add/send a message.
     * 
     * @param messageBody
     *     - the message body
     * @param messageHeader
     *     - the message header
     * @param senderUserId
     *     - the sender username
     * @param name
     *     - the sender's full name
     * 
     * @return - whether or not the call was successful.
     */
    @SuppressWarnings( "deprecation" )
    public static boolean addEmployeeReport( String messageBody,
                                             String messageHeader,
                                             String senderUserId,
                                             String name )
        {
        try
            {
            String url = INSTANCE_URL + "/services/data/v60.0/sobjects/EmployeeReport__c/" ;

            String accessToken = ACCESS_TOKEN ;

            String payload = "{\"messageBody__c\":\"" + messageBody + "\",\"Name\":\"" +
                             messageHeader + "\",\"senderName__c\":\"" + name +
                             "\",\"senderUser__c\":\"" + senderUserId + "\"}" ;

            URL obj = new URL( url ) ;
            HttpURLConnection con = (HttpURLConnection) obj.openConnection() ;

            con.setRequestMethod( "POST" ) ;
            con.setRequestProperty( "Authorization", "Bearer " + accessToken ) ;
            con.setRequestProperty( "Content-Type", "application/json" ) ;

            con.setDoOutput( true ) ;
            try ( OutputStream os = con.getOutputStream() )
                {
                byte[] input = payload.getBytes( StandardCharsets.UTF_8 ) ;
                os.write( input, 0, input.length ) ;

                }

            int responseCode = con.getResponseCode() ;
            System.out.println( "Response Code : " + responseCode ) ;

            if ( responseCode == HttpURLConnection.HTTP_CREATED )
                {
                System.out.println( "Record created successfully" ) ;
                return true ;

                }

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;
            return false ;

            }

        return false ;

        }

    }
