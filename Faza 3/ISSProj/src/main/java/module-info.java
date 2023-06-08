module com.example.issproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires hibernate.core;



    opens com.example.issproj to javafx.fxml;
    opens com.example.issproj.Domain to javafx.base;
    exports com.example.issproj;
}