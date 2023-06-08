package com.example.issproj.Repo;

import com.example.issproj.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepo implements  Repository<User>{
    private JdbcUtils dbUtils;

    public UserRepo(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<User> getall() {
        Connection con=dbUtils.getConnection();
        List<User> users=new ArrayList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from Users"))
        {
            try(ResultSet rs=ps.executeQuery())
            {
                while(rs.next())
                {
                    int id=rs.getInt("id");
                    String nume=rs.getString("nume");
                    String parola=rs.getString("parola");
                    String tip=rs.getString("tip");
                    User u=new User(id,nume,parola,tip);
                    u.setId(id);
                    users.add(u);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    @Override
    public boolean add(User e) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("insert into Users values (?,?,?,?)"))
        {
            ps.setInt(1,e.getId());
            ps.setString(2,e.getNume());
            ps.setString(3,e.getParola());
            ps.setString(4,e.getTip());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
    public boolean delete(User e) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("delete from Users where id=?"))
        {
            ps.setInt(1,e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
    public boolean upd(User e) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("update Users set nume=?,parola=?,tip=? where id=?"))
        {
            ps.setString(1,e.getNume());
            ps.setString(2,e.getParola());
            ps.setString(3,e.getTip());
            ps.setInt(4,e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }

    public User name_pass_get(String name,String pass)
    {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("select * from Users where nume=? and parola=?"))
        {
            ps.setString(1,name);
            ps.setString(2,pass);
            try(ResultSet rs=ps.executeQuery())
            {
                while(rs.next())
                {
                    int id=rs.getInt("id");
                    String nume=rs.getString("nume");
                    String parola=rs.getString("parola");
                    String tip=rs.getString("tip");
                    User u=new User(1,nume,parola,tip);
                    return u;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
