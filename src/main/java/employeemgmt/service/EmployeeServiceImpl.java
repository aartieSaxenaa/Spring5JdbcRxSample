package employeemgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.reactive.function.server.ServerResponse;

import employeemgmt.model.Employee;
import employeemgmt.repo.EmployeeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("jdbcScheduler")
	private Scheduler jdbcScheduler;
	
	@Autowired
	private TransactionTemplate transactionTemplate;

	@Override
	public Mono<Employee> findEmployeeById(int id) {
		Mono<Employee> emp = Mono
				.fromCallable(() -> employeeRepository.findByEmpId(id))
				.subscribeOn(jdbcScheduler);
		return emp;
	}

	@Override
	public Flux<Employee> getAllEmployee() {
		Flux<Employee> emps = Flux.defer(
				()->Flux.fromIterable(employeeRepository.findAll())
				.subscribeOn(jdbcScheduler));
		return emps;
	}

	@Override
	public Mono<Employee> addEmployee(String name) {
		return Mono.fromCallable(()->transactionTemplate.execute(status->{
			Employee emp = new Employee(name);
			return employeeRepository.save(emp);
		})).subscribeOn(jdbcScheduler);
	}

	@Override
	public String removeEmployee(String name) {
		Employee emp= new Employee(name);
		    employeeRepository.delete(emp);
		    return "Success";
		
	}
	
	
	
	
}
