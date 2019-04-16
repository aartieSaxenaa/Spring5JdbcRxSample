package employeemgmt.repo;

import org.springframework.data.repository.CrudRepository;

import employeemgmt.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>{
	Employee findByEmpId(int id);
	Employee findByName(String name);

}
