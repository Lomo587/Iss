package com.example.issproj.Repo;

import java.util.List;

public interface Repository<T> {
    public List<T> getall();
    public boolean add(T t);
}
