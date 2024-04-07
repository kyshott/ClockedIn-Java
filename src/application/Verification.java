
package application ;

import java.io.* ;
import java.net.HttpURLConnection ;
import java.net.URL ;
import java.util.Base64 ;

/**
 * The class to verify user credentials and establish a valid connection to
 * Salesforce via OAuth.
 * 
 * @author Kyle Shott
 */
public class Verification
    {

    /**
     * Very important - the OAuth authentication token that allows for API calls to
     * Salesforce after verification has been performed via salesForceOAuth().
     */
    protected String authKey ;

    /**
     * A getter method to return the OAuth authentication token to perform REST API
     * calls.
     * 
     * @return - the OAuth authentication token.
     */
    public String getKey()
        {
        return this.authKey ;

        }


    /**
     * A method that takes a username and password and checks it against the user
     * accounts in Salesforce and verifies login via OAuth. OAuth authentication is
     * performed through the ClockedIn connected app API in the Salesforce org.
     * 
     * @param USERNAME
     *     - the username to log in with.
     * @param PASSWORD
     *     - the password to log in with.
     * 
     * @return - true or false, depending on whether or not verification was
     *     complete.
     */
    @SuppressWarnings( "deprecation" )
    public static boolean salesForceOAuth( String USERNAME,
                                           String PASSWORD )
        {
        final String CLIENT_ID = "Replace this with Salesforce connected app client ID." ;
        final String CLIENT_SECRET = "Replace this with Salesforce connected app client secret." ;
        final String TOKEN_URL = "https://login.salesforce.com/services/oauth2/token" ;

        try
            {
            String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET ;
            String encodedCredentials = Base64.getEncoder()
                                              .encodeToString( clientCredentials.getBytes() ) ;

            String requestBody = "grant_type=password" + "&client_id=" + CLIENT_ID +
                                 "&client_secret=" + CLIENT_SECRET + "&username=" + USERNAME +
                                 "&password=" + PASSWORD ;

            URL url = new URL( TOKEN_URL ) ;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection() ;
            connection.setRequestMethod( "POST" ) ;
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" ) ;
            connection.setRequestProperty( "Authorization", "Basic " + encodedCredentials ) ;
            connection.setDoOutput( true ) ;

            OutputStream outputStream = connection.getOutputStream() ;
            try ( BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( outputStream,
                                                                                      "UTF-8" ) ) )
                {
                writer.write( requestBody ) ;
                writer.flush() ;
                writer.close() ;

                }

            outputStream.close() ;

            try ( BufferedReader reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) ) )
                {
                StringBuilder response = new StringBuilder() ;
                String line ;
                while ( ( line = reader.readLine() ) != null )
                    {
                    response.append( line ) ;

                    }

                reader.close() ;

                System.out.println( "Response: " + response.toString() ) ;

                // Parse JSON response
                String responseBody = response.toString() ;
                String authToken = extractValue( responseBody, "access_token" ) ;
                String instanceUrl = extractValue( responseBody, "instance_url" ) ;

                // Set the auth token and instance URL
                UserData.setToken( authToken ) ;
                UserData.setInstance( instanceUrl ) ;

                return true ;

                }

            }
        catch ( IOException e )
            {
            e.printStackTrace() ;
            return false ;

            }

        }


// Extracts the value associated with a key from a JSON formatted string
    private static String extractValue( String json,
                                        String key )
        {
        int startIndex = json.indexOf( "\"" + key + "\"" ) + key.length() + 4 ;
        int endIndex = json.indexOf( "\"", startIndex ) ;
        return json.substring( startIndex, endIndex ) ;

        }

    }