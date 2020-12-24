package ru.job4j.srp;

import java.util.function.Predicate;


public class ReportHTML implements Report {
    private Store store;

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>").append(System.lineSeparator())
                .append("<html lang=\"ru\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("<title>ReportHTML</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<div>").append(System.lineSeparator())
                .append("<p>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</p>").append(System.lineSeparator())
                .append("</div>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return text.toString();
    }
}
