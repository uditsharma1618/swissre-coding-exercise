package com.company.service;

import com.company.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class OrganizationAnalyzerTest {

    @Test
    void testManagerEarnsMoreThanAllowed() {
        Employee manager = new Employee(1, "John", "Doe", 200000, null);

        Employee emp1 = new Employee(2, "Jane", "Smith", 50000, 1);
        Employee emp2 = new Employee(3, "Bob", "Johnson", 50000, 1);

        emp1.setManager(manager);
        emp2.setManager(manager);
        manager.addSubordinate(emp1);
        manager.addSubordinate(emp2);

        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(1, manager);
        employees.put(2, emp1);
        employees.put(3, emp2);

        OrganizationAnalyzer analyzer = new OrganizationAnalyzer();
        analyzer.analyze(employees);
    }
}
