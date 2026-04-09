package com.company;

import com.company.model.Employee;
import com.company.service.OrganizationAnalyzer;
import com.company.util.CsvReader;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar app.jar <file-path>");
            return;
        }

        try {
            Map<Integer, Employee> employees =
                    CsvReader.readEmployees(args[0]);

            OrganizationAnalyzer analyzer = new OrganizationAnalyzer();
            analyzer.analyze(employees);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
