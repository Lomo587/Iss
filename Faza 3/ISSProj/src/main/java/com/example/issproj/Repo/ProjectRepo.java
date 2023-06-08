package com.example.issproj.Repo;

import com.example.issproj.Domain.Project;
import com.example.issproj.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProjectRepo implements Repository<Project> {
    private JdbcUtils dbUtils;

    public ProjectRepo(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Project> getall() {
        Connection con=dbUtils.getConnection();
        List<Project> users=new ArrayList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from Projects"))
        {
            try(ResultSet rs=ps.executeQuery())
            {
                while(rs.next())
                {
                    int id=rs.getInt("id");
                    String nume=rs.getString("nume");
                    Project u=new Project(id,nume);

                    users.add(u);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    @Override
    public boolean add(Project e) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("insert into Projects values (?)"))
        {
            ps.setString(1,e.getNume());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }


}
