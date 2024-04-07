/**
 * @author Kyle Shott
 * 
 *
 * Module info class to declare required JavaFX classes for GUIs
 *
 */
module ClockedIn 
{
	requires javafx.controls;
	requires javafx.fxml ;
    requires javafx.graphics;
    requires org.json ;
    requires java.net.http;
    requires javafx.base;
    requires java.base ;
   
	opens application to javafx.graphics, javafx.base ;
	opens buttonSets to javafx.fxml ;
	
}
