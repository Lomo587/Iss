package com.example.issproj.Service;

import com.example.issproj.Domain.Bug;
import com.example.issproj.Domain.User;
import com.example.issproj.Repo.BugRepo;
import com.example.issproj.Repo.ProjectRepo;
import com.example.issproj.Repo.UserRepo;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Service {
    private BugRepo bugs;
    private UserRepo users;
    private ProjectRepo projs;

    public Service()
    {
        Properties props = new Properties();
        try {
            props.load(new FileReader("bd.cfg"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.cfg "+e);
        }
        bugs=new BugRepo(props);
        users=new UserRepo(props);
        projs=new ProjectRepo(props);
    }
    public List<Bug> getBugs()
    {
        return bugs.getall();
    }
    public int user_exists(String name,String pass)
    {
        User u=users.name_pass_get(name,pass);
        if(u!=null)
        {
            String tip=u.getTip();
            if(tip.equals("admin"))
                return 1;
            else if (tip.equals("programator")) {
                return 2;
            }
            else return 3;
        }
        return 0;
    }
}
