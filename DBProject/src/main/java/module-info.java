module com.example.dbproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;
    requires miglayout;
    requires javafx.base;

    opens com.example.dbproject to javafx.fxml;
    exports com.example.dbproject;
    exports com.example.dbproject.controllers;
    opens com.example.dbproject.controllers to javafx.fxml;
    exports com.example.dbproject.controllers.VboxControllers;
    exports com.example.dbproject.controllers.AddProductController;
    opens com.example.dbproject.controllers.VboxControllers to javafx.base, javafx.graphics, javafx.fxml;
    opens com.example.dbproject.controllers.AddDocumentController to javafx.base, javafx.graphics, javafx.fxml;
    opens com.example.dbproject.Config to javafx.base;
    opens com.example.dbproject.controllers.AddProductController to javafx.base, javafx.graphics, javafx.fxml;
//    opens com.example.dbproject.Config.POJO to javafx.base;
    exports com.example.dbproject.controllers.AddTastController;
    opens com.example.dbproject.controllers.AddTastController to javafx.fxml;
    opens com.example.dbproject.Config.POJO.Document to javafx.base;
    opens com.example.dbproject.Config.POJO.ProductAndOffice to javafx.base;
    opens com.example.dbproject.Config.POJO.Task to javafx.base;
    opens com.example.dbproject.Config.POJO.User to javafx.base;

}