
package buttonSets ;

import org.json.JSONArray ;
import org.json.JSONException ;
import org.json.JSONObject ;

import java.io.IOException ;
import java.net.URL ;
import java.time.LocalTime ;
import java.time.format.DateTimeFormatter ;
import java.util.ResourceBundle ;

import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.control.Button ;
import javafx.scene.control.TextField ;

import application.Main ;
import application.Queries ;
import application.Scheduling ;
import application.UserData ;

/**
 * @author Kyle
 * 
 * @version 1.0.0 2024-04-01 Initial implementation
 */

@SuppressWarnings( "javadoc" )
public class TimeSheetController implements Initializable
    {

    @FXML
    Button submitButton ;
    @FXML
    Button outButton ;
    @FXML
    Button editButton ;
    @FXML
    Button inButton ;
    @FXML
    TextField dateText ;
    @FXML
    TextField startText ;
    @FXML
    TextField endText ;
    @FXML
    TextField titleText ;
    @FXML
    TextField startField ;
    @FXML
    TextField endField ;

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
     * Places the GUI into an "edit mode" state, that allows manual changing of punch
     * times.
     */
    public void editMode()
        {
        this.inButton.setDisable( true ) ;
        this.outButton.setDisable( true ) ;

        this.submitButton.setDisable( false ) ;

        this.startField.setMouseTransparent( false ) ;
        this.startField.setEditable( true ) ;

        this.endField.setMouseTransparent( false ) ;
        this.endField.setEditable( true ) ;

        this.editButton.setDisable( true ) ;

        }


    public void submitEdits()
        {
        Queries.punchInQuery( this.startField.getText() ) ;
        Queries.punchOutQuery( this.endField.getText() ) ;

        Scheduling.Today.setPunchInTime( this.startField.getText() ) ;
        Scheduling.Today.setPunchOutTime( this.endField.getText() ) ;

        this.editButton.setDisable( false ) ;

        this.initialize( null, null ) ;

        }


    /**
     * Updates the punch in time to the current timestamp.
     * 
     * @throws JSONException
     * @throws IOException
     */
    public void clockIn() throws JSONException, IOException
        {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "hh:mm a" ) ;

        String currentTime = LocalTime.now().format( dtf ).toString() ;

        Queries.punchInQuery( currentTime ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+ClockInTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Weekday__c+=+'" +
                                                               Scheduling.weekDay.toString() +
                                                               "'" ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        JSONObject stamp = dayArray.getJSONObject( 0 ) ;

        Scheduling.Today.setPunchInTime( stamp.getString( "ClockInTime__c" ) ) ;

        this.startField.setText( Scheduling.Today.getPunchInTime() ) ;

        if ( Scheduling.Today.getPunchInTime() != "N/A" )
            {
            this.inButton.setDisable( true ) ;
            this.outButton.setDisable( false ) ;

            }

        }


    /**
     * Updates the punch in time to the current timestamp.
     * 
     * @throws JSONException
     * @throws IOException
     */
    public void clockOut() throws JSONException, IOException
        {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "hh:mm a" ) ;

        String currentTime = LocalTime.now().format( dtf ).toString() ;

        Queries.punchOutQuery( currentTime ) ;

        JSONObject dayInfo = new JSONObject( Queries.getQuery( "query?q=SELECT+ClockOutTime__c+FROM+Scheduled_Day__c+WHERE+Employee_User__c+=+'" +
                                                               UserData.getUser() +
                                                               "'+AND+Weekday__c+=+'" +
                                                               Scheduling.weekDay.toString() +
                                                               "'" ) ) ;

        JSONArray dayArray = dayInfo.getJSONArray( "records" ) ;

        JSONObject stamp = dayArray.getJSONObject( 0 ) ;

        Scheduling.Today.setPunchOutTime( stamp.getString( "ClockOutTime__c" ) ) ;

        this.endField.setText( Scheduling.Today.getPunchOutTime() ) ;

        if ( Scheduling.Today.getPunchOutTime() != "N/A" )
            {
            this.outButton.setDisable( true ) ;
            this.inButton.setDisable( false ) ;

            }

        }


    public void goBack()
        {
        this.mainApp.loadHome() ;

        }


    @Override
    public void initialize( URL arg0,
                            ResourceBundle arg1 )
        {

        this.startField.setMouseTransparent( true ) ;
        this.startField.setEditable( false ) ;

        this.endField.setMouseTransparent( true ) ;
        this.endField.setEditable( false ) ;

        String dateHeader = Scheduling.weekDay.toString() + ", " + Scheduling.localDate.toString() ;

        this.dateText.setText( dateHeader ) ;
        this.startText.setText( Scheduling.Today.startTime ) ;
        this.endText.setText( Scheduling.Today.endTime ) ;
        this.titleText.setText( Scheduling.Today.jobTitle ) ;
        this.startField.setText( Scheduling.Today.getPunchInTime() ) ;
        this.endField.setText( Scheduling.Today.getPunchOutTime() ) ;

        if ( Scheduling.Today.punchInTime != null && Scheduling.Today.punchOutTime != null )
            {

            if ( Scheduling.Today.punchInTime.toString() != "N/A" &&
                 Scheduling.Today.punchOutTime.toString() == "N/A" )
                {
                this.inButton.setDisable( true ) ;
                this.outButton.setDisable( false ) ;

                }

            else
                {
                this.inButton.setDisable( false ) ;
                this.outButton.setDisable( true ) ;

                }

            }

        else
            {
            this.inButton.setDisable( true ) ;
            this.outButton.setDisable( true ) ;
            this.editButton.setDisable( true ) ;

            this.startText.setText( "N/A" ) ;
            this.endText.setText( "N/A" ) ;
            this.titleText.setText( "OFF" ) ;
            this.startField.setText( "N/A" ) ;
            this.endField.setText( "N/A" ) ;

            }

        }

    }
// end class TimeSheetController