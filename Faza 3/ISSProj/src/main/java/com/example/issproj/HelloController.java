package com.example.issproj;

import com.example.issproj.Domain.Bug;
import com.example.issproj.Domain.User;
import com.example.issproj.Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HelloController {
    private Service srv=new Service();
    private ObservableList<Bug> buguri;
    private List<Bug> bug;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField name_field,pass_field,project_field,filter_field,user_name,user_pass,user_type,user_id;
    @FXML
    private TextArea desc_area;
    @FXML
    private Button login_button,uab,udb,uub;
    @FXML
    private Button solve_button;
    @FXML
    private Button bug_button;
    @FXML
    public TabPane TabPane1;
    @FXML
    public Tab login_page;
    @FXML
    public Tab add_page;
    @FXML
    public Tab admin_page;
    @FXML
    public Tab bug_page;

    @FXML TableView bug_table,user_view;

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
                add_page.setDisable(false);
            } else if (res==2) {
                TabPane1.getSelectionModel().select(2);
                login_page.setDisable(true);
                bug_page.setDisable(false);
                load_bugs();
                filter_field.textProperty().addListener(o -> nameFilter());
            }
            else {
                TabPane1.getSelectionModel().select(3);
                login_page.setDisable(true);
                admin_page.setDisable(false);
                load_users();
            }
        }
    }
    private void nameFilter()
    {
        Predicate<Bug> p1= n -> n.getDescriere().startsWith(filter_field.getText());
        buguri.setAll(FXCollections.observableArrayList(bug).stream().filter(p1).collect(Collectors.toList()));
    }

    private void load_bugs()
    {
        List<Bug> o= srv.getBugs();

        bug=o;
        buguri= FXCollections.observableArrayList(bug);
        bug_table.getColumns().clear();
        TableColumn nume = new TableColumn("Descriere");
        nume.setMinWidth(250);
        nume.setCellValueFactory(new PropertyValueFactory<Bug,String>("Descriere"));
        TableColumn proj = new TableColumn("Proiect");
        proj.setMinWidth(100);
        proj.setCellValueFactory(new PropertyValueFactory<Bug, Integer>("Proiect"));
        TableColumn status = new TableColumn("Status");
        status.setMinWidth(100);
        status.setCellValueFactory(new PropertyValueFactory<Bug, String>("Status"));
        bug_table.setItems(buguri);
        bug_table.getColumns().addAll(nume,proj,status);
    }

    private void load_users()
    {
        List<User> o= srv.getUsers();


        user_view.getColumns().clear();
        TableColumn id=new TableColumn<>("Id");
        id.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("Id"));
        TableColumn nume = new TableColumn("Nume");
        nume.setMinWidth(100);
        nume.setCellValueFactory(new PropertyValueFactory<User,String>("Nume"));
        TableColumn pass = new TableColumn("Parola");
        pass.setMinWidth(100);
        pass.setCellValueFactory(new PropertyValueFactory<User,String>("Parola"));
        TableColumn status = new TableColumn("Tip");
        status.setMinWidth(100);
        status.setCellValueFactory(new PropertyValueFactory<User, String>("Tip"));
        user_view.setItems(FXCollections.observableArrayList( o));
        user_view.getColumns().addAll(id,nume,pass,status);
    }


    @FXML
    private void bug_function()
    {
        Bug b=new Bug(desc_area.getText(),"waiting",Integer.parseInt(project_field.getText()));
        srv.save_bug(b);
        desc_area.clear();
        project_field.clear();
    }

    @FXML
    private void solve_function() {
        try {
            Bug b = (Bug) bug_table.getSelectionModel().getSelectedItem();
            srv.solve_bug(b);
            load_bugs();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void id_select()
    {
        User u = (User) user_view.getSelectionModel().getSelectedItem();

        user_name.setText(u.getNume());
        user_pass.setText(u.getParola());
        user_type.setText(u.getTip());
        user_id.setText(String.valueOf(u.getId()));


    }

    @FXML
    private void uaf()
    {
        User u=new User(Integer.parseInt(user_id.getText()),user_name.getText(),user_pass.getText(),user_type.getText());
        srv.ad(u);
        load_users();
    }

    @FXML
    private void udf()
    {
        User u=new User(Integer.parseInt(user_id.getText()),user_name.getText(),user_pass.getText(),user_type.getText());
        srv.del(u);
        load_users();
    }

    @FXML
    private void uuf()
    {
        User u=new User(Integer.parseInt(user_id.getText()),user_name.getText(),user_pass.getText(),user_type.getText());
        srv.upd(u);
        load_users();
    }

}