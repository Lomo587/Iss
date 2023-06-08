package com.example.issproj.Repo;

import com.example.issproj.Domain.Bug;
import com.example.issproj.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BugRepo implements Repository<Bug>{
    private JdbcUtils dbUtils;

    public BugRepo(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Bug> getall() {
        Connection con=dbUtils.getConnection();
        List<Bug> users=new ArrayList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from Bugs"))
        {
            try(ResultSet rs=ps.executeQuery())
            {
                while(rs.next())
                {
                    int id=rs.getInt("id");
                    String nume=rs.getString("descriere");
                    String parola=rs.getString("status");
                    int idp=rs.getInt("proiect");
                    Bug u=new Bug(id,idp,nume,parola);
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
    public boolean add(Bug e) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("insert into Bugs (descriere,status,proiect) values (?,?,?)"))
        {
            ps.setString(1,e.getDescriere());
            ps.setString(2,e.getStatus());
            ps.setInt(3,e.getProiect());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
    public void solve(Bug e) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("update Bugs set status=? where id=?"))
        {
            ps.setString(1,"solved");
            ps.setInt(2,e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }


}
