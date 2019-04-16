package employeemgmt.web.client;

import org.springframework.web.reactive.function.client.WebClient;

import employeemgmt.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmployeeWebclient {
	WebClient client = WebClient.create("http://localhost:9000");
	public void consume() {
		// Retrieving a single resource
        Mono<Employee> employeeMono = client.get()
            .uri("/employees/{id}", "1")
            .retrieve()
            .bodyToMono(Employee.class);
        employeeMono.subscribe(System.out::println);
        
        // Retrieving multi employee
        Flux<Employee> employeeFlux = client.get()
        		.uri("/employees")
        		 .retrieve()
        		 .bodyToFlux(Employee.class);        
        employeeFlux.subscribe(System.out::println);

    }

}
