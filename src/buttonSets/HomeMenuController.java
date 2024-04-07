
package buttonSets ;

import java.net.URL ;
import java.util.ResourceBundle ;

import javafx.collections.FXCollections ;
import javafx.collections.ObservableList ;
import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.TableView ;
import javafx.scene.control.cell.PropertyValueFactory ;
import javafx.scene.text.Text ;
import application.Main ;
import application.Scheduling ;
import application.UserData ;
import application.Day ;
import java.time.* ;

/**
 * @author Kyle Shott
 */

@SuppressWarnings( "javadoc" )
public class HomeMenuController implements Initializable
    {

    @FXML
    Text localDateTime ;

    @FXML
    TableColumn<Day, String> quickViewWeekdayColumn ;

    @FXML
    TableColumn<Day, String> quickViewStartColumn ;

    @FXML
    TableColumn<Day, String> quickViewEndColumn ;

    @FXML
    TableColumn<Day, String> quickViewDateColumn ;

    @FXML
    TableView<Day> quickView ;

    @FXML
    ObservableList<Day> days = FXCollections.observableArrayList() ;

    @FXML
    Text test ;

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


    public void switchToScheduling()
        {
        this.mainApp.loadScheduling() ;

        }


    public void switchToTimeSheet()
        {
        this.mainApp.loadTimeSheet() ;

        }


    public void switchToMessaging()
        {
        this.mainApp.loadMessaging() ;

        }

    /**
     * The "welcome" message displayed at the top of the GUI upon successful login.
     */
    @FXML
    Text welcomeHeader ;

    @Override
    public void initialize( URL arg0,
                            ResourceBundle arg1 )
        {
        LocalDate localDate = LocalDate.now() ;

        this.welcomeHeader.setText( "Welcome back, " + UserData.getName() ) ;

        this.localDateTime.setText( localDate.getDayOfWeek() + ", " + localDate.toString() ) ;

        this.days.addAll( Scheduling.Today,
                          Scheduling.next,
                          Scheduling.third,
                          Scheduling.fourth,
                          Scheduling.fifth,
                          Scheduling.sixth,
                          Scheduling.seventh,
                          Scheduling.eighth ) ;

        this.quickView.setItems( this.days ) ;

        this.quickViewWeekdayColumn.setCellValueFactory( new PropertyValueFactory<>( "weekday" ) ) ;
        this.quickViewStartColumn.setCellValueFactory( new PropertyValueFactory<>( "startTime" ) ) ;
        this.quickViewEndColumn.setCellValueFactory( new PropertyValueFactory<>( "endTime" ) ) ;
        this.quickViewDateColumn.setCellValueFactory( new PropertyValueFactory<>( "date" ) ) ;

        }


    /**
     * Logs the user out of the application and clears all local data taken from
     * Salesforce SOQL
     */
    public void logout()
        {
        this.mainApp.loadLogin() ;

        Scheduling.clearAll() ;
        UserData.setUser( null ) ;
        UserData.setToken( null ) ;
        UserData.setName( null ) ;
        UserData.setInstance( null ) ;

        }

    }

// end class HomeMenuController