package com.example.issproj;

import com.example.issproj.Domain.Bug;
import com.example.issproj.Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HelloController {
    private Service srv=new Service();
    @FXML
    private Label welcomeText;
    @FXML
    private TextField name_field,pass_field;
    @FXML
    private Button login_button;
    @FXML
    public TabPane TabPane1;
    @FXML
    public Tab login_page;

    @FXML
    public Tab bug_page;

    @FXML TableView bug_table;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void login_function()
    {
        int res=srv.user_exists(name_field.getText(),pass_field.getText());
        if(res!=0)
        {
            if(res==3) {
                TabPane1.getSelectionModel().select(1);
                login_page.setDisable(true);
            } else if (res==2) {
                TabPane1.getSelectionModel().select(2);
                login_page.setDisable(true);
                bug_page.setDisable(false);
                load_bugs();
            }
            else {
                TabPane1.getSelectionModel().select(3);
                login_page.setDisable(true);
            }
        }
    }

    private void load_bugs()
    {
        List<Bug> o= srv.getBugs();


        o= FXCollections.observableArrayList(o);
        bug_table.getColumns().clear();
        TableColumn nume = new TableColumn("Descriere");
        nume.setMinWidth(250);
        nume.setCellValueFactory(new PropertyValueFactory<Bug,String>("Descriere"));
        TableColumn proj = new TableColumn("Proiect");
        proj.setMinWidth(100);
        proj.setCellValueFactory(new PropertyValueFactory<Bug, Integer>("Proiect"));
        bug_table.setItems((ObservableList) o);
        bug_table.getColumns().addAll(nume,proj);
    }

}