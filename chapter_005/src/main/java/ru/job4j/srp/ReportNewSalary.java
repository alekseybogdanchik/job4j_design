package ru.job4j.srp;

import java.util.function.Predicate;


public class ReportNewSalary implements Report {
    private Store store;

    public ReportNewSalary(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            String salary = (employee.getSalary() / 1000) + " тыс. руб.";
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
