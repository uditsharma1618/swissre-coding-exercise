package com.company.service;

import com.company.model.Employee;

import java.util.List;
import java.util.Map;

public class OrganizationAnalyzer {

    public void analyze(Map<Integer, Employee> employees) {
        System.out.println("=== Salary Analysis ===");
        analyzeSalaries(employees);

        System.out.println("\n=== Reporting Line Analysis ===");
        analyzeReportingLines(employees);
    }

    private void analyzeSalaries(Map<Integer, Employee> employees) {
        for (Employee manager : employees.values()) {
            List<Employee> subs = manager.getSubordinates();
            if (subs.isEmpty()) continue;

            double avgSalary = subs.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0);

            double minAllowed = avgSalary * 1.20;
            double maxAllowed = avgSalary * 1.50;
            double managerSalary = manager.getSalary();

            if (managerSalary < minAllowed) {
                System.out.printf(
                        "%s earns %.2f LESS than required%n",
                        manager.getFullName(),
                        minAllowed - managerSalary
                );
            } else if (managerSalary > maxAllowed) {
                System.out.printf(
                        "%s earns %.2f MORE than allowed%n",
                        manager.getFullName(),
                        managerSalary - maxAllowed
                );
            }
        }
    }

    private void analyzeReportingLines(Map<Integer, Employee> employees) {
        for (Employee emp : employees.values()) {
            int depth = getManagementDepth(emp);

            if (depth > 4) {
                System.out.printf(
                        "%s has %d managers between them and the CEO (exceeds by %d)%n",
                        emp.getFullName(),
                        depth,
                        depth - 4
                );
            }
        }
    }

    private int getManagementDepth(Employee employee) {
        int depth = 0;
        Employee manager = employee.getManager();

        while (manager != null) {
            depth++;
            manager = manager.getManager();
        }
        return depth;
    }
}
