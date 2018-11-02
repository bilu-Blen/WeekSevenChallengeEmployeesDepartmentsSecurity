package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String showDepartmentAndEmployee(Model model){
        //creating  anew department
        Department department = new Department();
        department.setName("Accounting");

        //creating a new employee
        Employee employee = new Employee();
        employee.setName("Lee");
        employee.setJobTitle("Accountant");
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        employee.setName("Hanna");
        employee.setJobTitle("Data Clerk");
        employee.setDepartment(department);
        employees.add(employee);

        //setting th eemployees to the department
        department.setEmployees(employees);

        departmentRepository.save(department);

        Department department1 = new Department();
        department1.setName("IT");
        Employee employee1 = new Employee();
        employee1.setName("John");
        employee1.setJobTitle("Analyst");
        employee1.setDepartment(department1);
        employees  = new HashSet<>();
        employees.add(employee1);
        department1.setEmployees(employees);
        departmentRepository.save(department1);

        model.addAttribute("departments", departmentRepository.findAll());
        return "index";

    }

    @GetMapping("/adddepartment")
    public String addDepartment(Model model){
        model.addAttribute("department", new Department());
        return "adddepartment";
    }

    @PostMapping("/adddepartment")
    public String saveDepartemnt(@ModelAttribute("department") Department department){
        departmentRepository.save(department);
        return "redirect:/";
    }

    @GetMapping("/addemployee")
    public String addEmployee(Model model){

        Department department = new Department();
        department.setName("Accounting");

        //creating a new employee
        Employee employee = new Employee();
        employee.setName("Lee");
        employee.setJobTitle("Accountant");
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        employee.setName("Hanna");
        employee.setJobTitle("Data Clerk");
        employee.setDepartment(department);
        employees.add(employee);

        //setting th eemployees to the department
        department.setEmployees(employees);

        departmentRepository.save(department);

        Department department1 = new Department();
        department1.setName("IT");
        Employee employee1 = new Employee();
        employee1.setName("John");
        employee1.setJobTitle("Analyst");
        employee1.setDepartment(department1);
        employees  = new HashSet<>();
        employees.add(employee1);
        department1.setEmployees(employees);
        departmentRepository.save(department1);

        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentRepository.findAll());
        return "addemployee";
    }

    @PostMapping("/addemployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model){
        Set<Employee> employees = new HashSet<>();
        employeeRepository.save(employee);
        return "redirect:/";
     }
}

