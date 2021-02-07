package ru.job4j.srp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("{").append(System.lineSeparator());
        List<Employee> filtered = store.findBy(filter);
        int size = filtered.size();
        int count = 0;
        for (Employee employee : filtered) {
            count++;
            text.append("\"Name\": \"")
                .append(employee.getName()).append("\", ")
                .append("\"Hired\": \"")
                .append(dateFormat(employee.getHired())).append("\", ")
                .append("\"Fired\": \"")
                .append(dateFormat(employee.getFired())).append("\", ")
                .append("\"Salary\": \"")
                .append(employee.getSalary()).append("\"");
            if (count != size) {
                    text.append(", ");
            }
                text.append(System.lineSeparator());
        }
        text.append("}").append(System.lineSeparator());
        return text.toString();
    }

    private String dateFormat(Calendar calendar) {
        if (calendar == null) {
            return " ";
        }
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        return df.format(calendar.getTime());
    }
}
