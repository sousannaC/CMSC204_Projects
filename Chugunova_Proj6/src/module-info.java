/**
 * 
 */
/**
 * 
 */
module Chugunova_Proj6 {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.junit.jupiter.api;
    opens main to javafx.fxml;

	requires junit;
	requires java.desktop;
    exports main;
}