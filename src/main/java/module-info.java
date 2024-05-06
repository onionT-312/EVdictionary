module evapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.desktop;
    requires voicerss.tts;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens evapp to javafx.fxml;
    exports evapp;
    opens evapp.app to javafx.fxml;
    exports evapp.app;
    exports evapp.dict;
}