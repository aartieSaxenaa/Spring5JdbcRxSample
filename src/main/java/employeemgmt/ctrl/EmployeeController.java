package employeemgmt.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import employeemgmt.model.Employee;
import employeemgmt.service.EmployeeService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rx")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public Mono<Employee> getEmployeeById(@PathVariable int id){
		return employeeService.findEmployeeById(id);
	}
	
	@GetMapping("/all")
	public Flux<Employee> getAllEmployee(){
		return employeeService.getAllEmployee();
	}
	//http://localhost:9000/rx/add/?empName=angie
	@PostMapping("/add")
	public Mono<Employee> addEmployee(@RequestBody String empName){
		return employeeService.addEmployee(empName);
	}
	
	@PostMapping("/remove")
	public String removeEmployee(@RequestBody String name){
		return employeeService.removeEmployee(name);
	}

}
