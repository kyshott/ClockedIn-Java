
package buttonSets ;

import java.net.URL ;
import java.util.ResourceBundle ;
import javafx.fxml.Initializable ;
import javafx.fxml.FXML ;
import javafx.scene.control.Button ;
import javafx.scene.control.PasswordField ;
import javafx.scene.control.TextField ;
import application.Verification ;
import javafx.scene.text.* ;

import application.Main ;
import application.Scheduling ;
import application.UserData ;

/**
 * A class that handles all processes done from the user GUI end of OAuth login
 * authentication.
 * 
 * @author Kyle Shott
 */
public class LoginController implements Initializable
    {

    private Main mainApp ;

    /**
     * Sets the main application/scene to this controller.
     * 
     * @param changeApp
     *     - the instance of main that is set to our working JavaFX scene.
     */
    public void setMainApp( Main changeApp )
        {
        this.mainApp = changeApp ;

        }

    /**
     * Error code text field that will display an error when a bad request is made
     * (400) or when a connection is not valid.
     */
    @FXML
    Text errorCode ;

    /**
     * The FX button to confirm login.
     */
    @FXML
    Button loginButton ;

    /**
     * The text field for a user entered username (email).
     */
    @FXML
    TextField usernameField ;

    /**
     * The text field for a user entered password.
     */
    @FXML
    PasswordField passwordField ;

    @Override
    public void initialize( URL url,
                            ResourceBundle resourceBundle )
        {
        // Method body is empty here since there is no extra functionality required

        }


    /**
     * A method to check for valid login credentials and returns an error if not
     * valid.
     * 
     * @throws Exception
     *     - for when FXMLLoader fails to load or obtain resources
     */
    public void loginConfirmed() throws Exception
        {
        
        this.loginButton.setDisable( true ) ;

        Verification.salesForceOAuth( this.usernameField.getText(), this.passwordField.getText() ) ;

        if ( !Verification.salesForceOAuth( this.usernameField.getText(),
                                            this.passwordField.getText() ) )
            {
            this.loginButton.setDisable( false ) ;

            this.errorCode.setText( "Login failed. Ensure valid credentials and/or internet connection." ) ;

            return ;

            }

        UserData.setUser( this.usernameField.getText() ) ;
        
        UserData.initializeEmployeeResponse() ;
        
        Scheduling.populateToday() ;
        Scheduling.populateNext() ;
        Scheduling.populateThird() ;
        Scheduling.populateFourth() ;
        Scheduling.populateFifth() ;
        Scheduling.populateSixth() ;
        Scheduling.populateSeventh() ;
        Scheduling.populateEighth() ;
        
        System.out.println( "Login successful. Welcome to ClockedIn. Today's date is: " + Scheduling.Today.getDate() ) ;
        
        this.loginButton.setDisable( false ) ;

        this.mainApp.loadHome() ;

        }

    }
// end class LoginController