package ru.job4j.srp;

import java.util.Date;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            Date hired = employee.getHired().getTime();
            Date fired = employee.getFired().getTime();
            text.append("<employee>").append(System.lineSeparator())
                    .append(String.format("<%1$s>%2$s</%1$s>%n", "name", employee.getName()))
                    .append(String.format("<%1$s>%2$s</%1$s>%n", "hired", hired))
                    .append(String.format("<%1$s>%2$s</%1$s>%n", "fired", fired))
                    .append(String.format("<%1$s>%2$.2f</%1$s>%n", "salary", employee.getSalary()))
                    .append("</employee>").append(System.lineSeparator());
        }
        text.append("</employees>").append(System.lineSeparator());
        return text.toString();
    }
}
