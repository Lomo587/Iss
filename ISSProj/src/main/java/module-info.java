module com.example.issproj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.issproj to javafx.fxml;
    exports com.example.issproj;
}