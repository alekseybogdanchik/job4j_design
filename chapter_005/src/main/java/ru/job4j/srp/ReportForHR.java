package ru.job4j.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class ReportForHR implements Report {
    private Store store;

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        Comparator<Employee> comparator = new EmployeeDescSalary();
        list.sort(comparator);
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : list) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
