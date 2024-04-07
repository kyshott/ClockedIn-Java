
package buttonSets ;

import java.io.IOException ;
import java.net.URL ;
import java.util.ResourceBundle ;

import javafx.collections.FXCollections ;
import javafx.collections.ObservableList ;
import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.control.Button ;
import javafx.scene.control.TableCell ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.TableView ;
import javafx.scene.control.TextArea ;
import javafx.scene.control.TextField ;
import javafx.scene.control.cell.PropertyValueFactory ;
import javafx.scene.text.Text ;
import javafx.scene.layout.StackPane ;

import application.Main ;
import application.Notification ;
import application.Queries ;
import application.UserData ;
import org.json.JSONArray ;
import org.json.JSONException ;
import org.json.JSONObject ;

/**
 * @author Kyle
 * 
 * @version 1.0.0 2024-04-02 Initial implementation
 */
@SuppressWarnings( "javadoc" )
public class MessagingController implements Initializable
    {

    private Main mainApp ;

    @FXML
    TextField headerBox ;
    @FXML
    TextArea messageBody ;
    @FXML
    Button sendButton ;
    @FXML
    Button backButton ;
    @FXML
    Text successCode ;
    @FXML
    Text failureCode ;
    @FXML
    TableView<Notification> notifTable ;
    @FXML
    TableColumn<Notification, String> notifColumn ;
    @FXML
    ObservableList<Notification> nots = FXCollections.observableArrayList() ;

    /**
     * This initialize method is longer than usual because it sets the property for
     * notification strings to wrap around the column when displayed.
     */
    @Override
    public void initialize( URL arg0,
                            ResourceBundle arg1 )
        {

        this.headerBox.clear() ;
        this.messageBody.clear() ;
        this.successCode.setVisible( false ) ;
        this.failureCode.setVisible( false ) ;

        this.notifColumn.setCellFactory( column ->
            {
            return new TableCell<>()
                {

                private final Text text ;
                private final StackPane stackPane ;

                    {
                    this.text = new Text() ;
                    this.text.wrappingWidthProperty()
                             .bind( MessagingController.this.notifColumn.widthProperty() ) ;
                    this.text.textProperty().bind( itemProperty() ) ;

                    this.stackPane = new StackPane() ;
                    this.stackPane.getChildren().add( this.text ) ;
                    setGraphic( this.stackPane ) ;

                    }

                @Override
                protected void updateItem( String item,
                                           boolean empty )
                    {
                    super.updateItem( item, empty ) ;
                    if ( empty || item == null )
                        {
                        setText( null ) ;
                        setGraphic( null ) ;

                        }
                    else
                        {
                        setGraphic( this.stackPane ) ;

                        }

                    }

                } ;

            } ) ;

        }


    /**
     * A method to retrieve notifications from the Salesforce org.
     * 
     * @throws JSONException
     * @throws IOException
     */
    @SuppressWarnings( "deprecation" )
    public void getNotifs() throws JSONException, IOException
        {
        JSONObject notifications = new JSONObject( Queries.getQuery( "query?q=SELECT+Name+FROM+Notification__c" ) ) ;

        System.out.println( notifications ) ;

        JSONArray notifArray = notifications.getJSONArray( "records" ) ;

        for ( int i = 0 ; i < notifArray.length() ; i++ )
            {
            JSONObject notification = notifArray.getJSONObject( i ) ;
            String name = notification.getString( "Name" ) ;

            Notification not = new Notification( name ) ;

            this.nots.add( not ) ;

            }

        this.notifTable.setItems( this.nots ) ;
        this.notifColumn.setCellValueFactory( new PropertyValueFactory<>( "name" ) ) ;
        this.notifTable.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY ) ;

        }


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
     * Sends the user back to the previous GUI screen
     */
    public void goBack()
        {
        this.mainApp.loadHome() ;

        }


    /**
     * Creates a message record for managers/system admins to view in the Salesforce
     * org.
     */
    public void sendMessage()
        {
        if ( !this.headerBox.getText().isEmpty() && !this.messageBody.getText().isEmpty() )
            {
            Queries.addEmployeeReport( this.messageBody.getText(),
                                       this.headerBox.getText(),
                                       UserData.getUser(),
                                       UserData.getName() ) ;

            this.failureCode.setVisible( false ) ;
            this.successCode.setVisible( true ) ;
            this.headerBox.clear() ;
            this.messageBody.clear() ;

            }
        else
            {
            this.successCode.setVisible( false ) ;
            this.failureCode.setVisible( true ) ;

            }

        }

    }
// end class MessagingController