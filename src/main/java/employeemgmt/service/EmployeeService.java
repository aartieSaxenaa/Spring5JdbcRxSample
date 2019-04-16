package employeemgmt.service;

import employeemgmt.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	Mono<Employee> findEmployeeById(int id);
	Flux<Employee> getAllEmployee();
	Mono<Employee> addEmployee(String empName);
	String removeEmployee(String name);
}
