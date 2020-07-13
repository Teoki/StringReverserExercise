package de.check24.teo.springproject.spring.project.core;

import de.check24.teo.springproject.spring.project.extern.out.database.mydatabase.MyDatabaseImpl;

import java.util.List;

public class Bank { //Bank is set in config??

    public List<ATM> atmList;
    public MyDatabaseImpl database;
    public int id;

}
