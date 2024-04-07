
package application ;

import java.io.IOException ;

import org.json.JSONArray ;
import org.json.JSONObject ;

/**
 * @author Kyle
 * 
 * @version 1.0.0 2024-03-20 Initial implementation
 */
public class UserData
    {

    /**
     * The employee object JSON object that can be accessed via the org.json
     * dependency
     */
    static String employeeJsonResponse ;

    /**
     * Initializes the employee object response.
     * 
     * @throws IOException
     *     - the exception to throw
     */
    public static void initializeEmployeeResponse() throws IOException
        {
        JSONObject queryResult = new JSONObject( Queries.getQuery( "query?q=SELECT+Name+FROM+Employee__c+WHERE+Username__c+IN+('" + UserData.getUser() + "')" )) ;
        
        JSONArray recordArray = queryResult.getJSONArray( "records" ) ;
        
        JSONObject firstRecord = recordArray.getJSONObject( 0 ) ;
        
        String name = firstRecord.getString( "Name" ) ;
        
        employeeJsonResponse = name ;

        }

    /**
     * The username email provided by the user.
     */
    private static String USER_NAME ;

    /**
     * Returns the user inputted email username.
     * 
     * @return - user entered user name.
     */
    public static String getUser()
        {
        return USER_NAME ;

        }


    /**
     * Stores the user entered username.
     * 
     * @param newUser
     *     - the new username.
     */
    public static void setUser( String newUser )
        {
        USER_NAME = newUser ;

        }

    /**
     * The access token returned by OAuth authentication.
     */
    private static String ACCESS_TOKEN ;

    /**
     * Returns the OAuth generated access token for Salesforce.
     * 
     * @return - the OAuth access token.
     */
    public static String getToken()
        {
        return ACCESS_TOKEN ;

        }


    /**
     * Sets the OAuth token.
     * 
     * @param token
     *     - the token to set.
     */
    public static void setToken( String token )
        {
        ACCESS_TOKEN = token ;

        }

    /**
     * The instance URL returned by OAuth authentication.
     */
    private static String INSTANCE_URL ;

    /**
     * Returns the OAuth generated Salesforce instance URL.
     * 
     * @return - the OAuth generated instance URL.
     */
    public static String getInstance()
        {
        return INSTANCE_URL ;

        }


    /**
     * Sets the Instance URL.
     * 
     * @param instance
     *     - the instance url to set.
     */
    public static void setInstance( String instance )
        {
        INSTANCE_URL = instance ;

        }

    /**
     * The full name of the user taken from the user object in Salesforce.
     */
    private static String fullName ;

    /**
     * Returns the user's name.
     * 
     * @return - the user's full name.
     */
    public static String getName()
        {
        fullName = employeeJsonResponse ;

        return fullName ;

        }


    /**
     * Sets the user's full name.
     * 
     * @param name
     *     - the name to set.
     */
    public static void setName( String name )
        {
        fullName = name ;

        }

    }
// end class UserData