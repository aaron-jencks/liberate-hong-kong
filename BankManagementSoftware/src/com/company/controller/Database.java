package com.company.controller;

import java.io.*;

public class Database implements IDatabase {

    private String databasePath = "/database/";

    @Override
    public void updatePerson(Person p) {
        try {
            File file = getFile("person.json");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCustomer(Customer c) {
        try {
            File file = getFile("customer.json");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTeller(Teller t) {
        try {
            File file = getFile("teller.json");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateEmployee(Employee emp) {
        try {
            File file = getFile("employee.json");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File getFile(String fileName) throws IOException {
        File file = new File(databasePath+fileName);
        if(file.createNewFile()){
            System.out.println("File created");
            return file;
        }
        System.out.println("File found");
        return file;
    }

}