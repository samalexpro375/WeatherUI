module dev.samalexx.weatherui {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jsobject;
    requires transitive org.json;


    opens dev.samalexx.weatherui to javafx.fxml;
    exports dev.samalexx.weatherui;
}