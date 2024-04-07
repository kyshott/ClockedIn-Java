
package application ;

/**
 * @author Kyle
 * 
 * @version 1.0.0 2024-04-03 Initial implementation
 */
public class Notification
    {

    private String name ;

    /**
     * Default constructor for a notification.
     * 
     * @param setname
     *     - the name to set.
     */
    public Notification( String setname )
        {
        this.name = setname ;

        }


    /**
     * Returns the notification "Name".
     * 
     * @return - this.name, the object name.
     */
    public String getName()
        {
        return this.name ;

        }

    }