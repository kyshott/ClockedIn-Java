
package application ;

import javafx.application.Application ;
import javafx.fxml.FXMLLoader ;
import javafx.stage.Stage ;
import javafx.scene.Parent ;
import javafx.scene.Scene ;
import buttonSets.LoginController ;
import buttonSets.MessagingController ;
import buttonSets.ScheduleController ;
import buttonSets.TimeSheetController ;
import buttonSets.HomeMenuController ;


/**
 * The main JavaFX GUI class that launches the user application.
 * 
 * @author Kyle Shott
 * 
 * @version 1.0.0 2024-03-18 Initial implementation
 */

public class Main extends Application
    {

    private static Stage guiStage ;

    /**
     * Getter method for the GUI stage so that the scene can be changed statically.
     * 
     * @return - the currently used JavaFX GUI stage
     */
    public static Stage getStage()
        {
        return guiStage ;

        }


    @Override
    public void start( Stage primaryStage )
        {
        guiStage = primaryStage ;
        
        guiStage.setTitle( "ClockedIn" );

        loadLogin() ;

        }
    
    /**
     * A class to load the schedule builder page.
     */
    public void loadScheduling()
        {
        try
            {

            FXMLLoader loader = new FXMLLoader( getClass().getResource( "/guis/scheduleBuilder.fxml" ) ) ;

            Parent root = loader.load() ;
            Scene scene = new Scene( root, 640, 400 ) ;
            guiStage.setScene( scene ) ;
            guiStage.setResizable( false ) ;
            guiStage.show() ;

            ScheduleController controller = loader.getController() ;

            controller.setMainApp( this ) ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }
    
    /**
     * A class to load the messaging page.
     */
    public void loadMessaging()
        {
        try
            {

            FXMLLoader loader = new FXMLLoader( getClass().getResource( "/guis/messaging.fxml" ) ) ;

            Parent root = loader.load() ;
            Scene scene = new Scene( root, 640, 400 ) ;
            guiStage.setScene( scene ) ;
            guiStage.setResizable( false ) ;
            guiStage.show() ;

            MessagingController controller = loader.getController() ;
            
            controller.getNotifs() ;

            controller.setMainApp( this ) ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }


    /**
     * A class to load the timesheet page.
     */
    public void loadTimeSheet()
        {
        try
            {

            FXMLLoader loader = new FXMLLoader( getClass().getResource( "/guis/timeSheet.fxml" ) ) ;

            Parent root = loader.load() ;
            Scene scene = new Scene( root, 640, 400 ) ;
            guiStage.setScene( scene ) ;
            guiStage.setResizable( false ) ;
            guiStage.show() ;

            TimeSheetController controller = loader.getController() ;

            controller.setMainApp( this ) ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }


    /**
     * A class to load the login page.
     */
    public void loadLogin()
        {
        try
            {

            FXMLLoader loader = new FXMLLoader( getClass().getResource( "/guis/login.fxml" ) ) ;

            Parent root = loader.load() ;
            Scene scene = new Scene( root, 640, 400 ) ;
            guiStage.setScene( scene ) ;
            guiStage.setResizable( false ) ;
            guiStage.show() ;

            LoginController controller = loader.getController() ;

            controller.setMainApp( this ) ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }


    /**
     * A class to load the home page.
     */
    public void loadHome()
        {
        try
            {

            FXMLLoader loader = new FXMLLoader( getClass().getResource( "/guis/homeScreen.fxml" ) ) ;

            Parent root = loader.load() ;
            Scene scene = new Scene( root, 640, 400 ) ;
            guiStage.setScene( scene ) ;
            guiStage.setResizable( false ) ;
            guiStage.show() ;

            HomeMenuController controller = loader.getController() ;

            controller.setMainApp( this ) ;

            }
        catch ( Exception e )
            {
            e.printStackTrace() ;

            }

        }


    /**
     * Main method that actually launches the start configurations.
     * 
     * @param args
     *     - unused
     */

    public static void main( String[] args )
        {
        launch( args ) ;

        }

    }
