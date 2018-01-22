package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value="select * from employee where first_name=?1 and last_name=?2", nativeQuery=true)
	Employee findByFirstNameAndlastName(String firstname, String lastName);
	
	Employee findByLastName(String lastName);

}
