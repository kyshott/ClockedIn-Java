
package buttonSets ;

import java.net.URL ;
import java.util.ResourceBundle ;

import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.control.Button ;
import javafx.scene.control.DatePicker ;
import javafx.scene.control.TextField ;
import javafx.scene.text.Text ;

import application.Main ;
import application.Queries ;
import application.UserData ;

/**
 * @author Kyle Shott
 * 
 * @version 1.0.0 2024-04-04 Initial implementation
 */

@SuppressWarnings( "javadoc" )
public class ScheduleController implements Initializable
    {

    private Main mainApp ;
    
    @FXML DatePicker datePick ;
    @FXML TextField jobTitle ;
    @FXML TextField startTime ;
    @FXML TextField endTime ;
    @FXML Button submit ;
    @FXML Text errorCode ;
    @FXML Text successCode ;
    @FXML Button back ;

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
     * Sends the user back to the home screen.
     */
    public void goBack()
        {
        this.mainApp.loadHome() ;

        }


    @Override
    public void initialize( URL arg0,
                            ResourceBundle arg1 )
        {
        this.successCode.setVisible( false ) ;
        this.errorCode.setVisible( false ) ;
        
        }
    
    /**
     * Submits the schedule as inputted by the user.
     * 
     */
    public void submitSchedule()
    {
    if ( this.datePick.getValue() == null || this.jobTitle.getText().isEmpty() || this.startTime.getText().isEmpty() || this.endTime.getText().isEmpty() || this.jobTitle.getText().isEmpty() )
        {
        this.errorCode.setVisible( false ) ;
        this.errorCode.setVisible( true ) ;
        return ;
        
        }
    
    Queries.createScheduledDayAndInitiateApproval( this.datePick.getValue().toString(), this.startTime.getText(), this.endTime.getText(), UserData.getUser(), this.jobTitle.getText(), this.datePick.getValue().getDayOfWeek().toString() );
   
    this.errorCode.setVisible( false ) ;
    
    this.successCode.setVisible( true ) ;
    
    this.startTime.clear() ;
    this.endTime.clear() ;
    this.jobTitle.clear() ;
    
    }

    }
// end class ScheduleController