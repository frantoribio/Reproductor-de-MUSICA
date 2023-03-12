module es.frantoribio.reproductor {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;
    requires org.json;
    requires static lombok;
    requires retrofit2;
    requires okhttp3;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    requires org.jetbrains.annotations;
    requires javafx.media;


    opens es.frantoribio.reproductor to javafx.fxml;
    exports es.frantoribio.reproductor;
    exports es.frantoribio.reproductor.controllers;
    opens es.frantoribio.reproductor.controllers to javafx.fxml;
    exports es.frantoribio.reproductor.models;
    opens es.frantoribio.reproductor.models to gson, javafx.fxml;

}