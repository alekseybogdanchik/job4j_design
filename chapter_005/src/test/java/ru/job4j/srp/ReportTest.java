package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;


public class ReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportHTML(store);
        String rsl = engine.generate(em -> true);
        System.out.println(rsl);
        boolean result = rsl.contains("<html lang=\"ru\">");
        assertThat(result, is(true));
    }

    @Test
    public void whenNewSalaryGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 10000);
        store.add(worker);
        Report engine = new ReportNewSalary(store);
        boolean result = engine.generate(em -> true).contains("10.0 тыс. руб.");
        assertThat(result, is(true));
    }

    @Test
    public void whenDescBySalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 10000);
        Employee worker2 = new Employee("Semen", now, now, 20000);
        Employee worker3 = new Employee("Lisa", now, now, 15000);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Pavel", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJSON(store);
        String rsl = engine.generate(em -> true);
        //System.out.println(rsl);
        boolean result = rsl.contains("{");
        assertThat(result, is(true));
    }

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Pavel", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportXML(store);
        String rsl = engine.generate(em -> true);
        //System.out.println(rsl);
        boolean result = rsl.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        assertThat(result, is(true));
    }
}
