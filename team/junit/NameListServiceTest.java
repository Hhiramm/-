package exer130.team.junit;

import exer130.team.domain.Employee;
import exer130.team.servise.NameListService;

import exer130.team.servise.TeamException;
import org.junit.Test;
public class NameListServiceTest {
    @Test
    public void testGetAllEmployees(){
        NameListService nameListService = new NameListService();
        Employee[] employees = nameListService.getAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }
    @Test
    public void testGetEmployee(){
        try {
            NameListService nameListService = new NameListService();
            int id = 3;
            id = 13;
            Employee employee = nameListService.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            System.out.println(e.getMessage());
        }
    }
}
