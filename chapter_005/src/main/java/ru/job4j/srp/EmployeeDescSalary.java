package ru.job4j.srp;

import java.util.Comparator;


public class EmployeeDescSalary implements Comparator<Employee> {

    @Override
    public int compare(Employee em1, Employee em2) {
        return Double.compare(em2.getSalary(), em1.getSalary());
    }
}
