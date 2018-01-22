package domain;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Address;
import entity.Employee;
import entity.Project;
import repository.AddressRepository;
import repository.EmployeeRepository;
import repository.ProjectRepository;

public class domain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		AddressRepository addressRepository = context.getBean(AddressRepository.class);
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

		Address address = new Address();
		address.setId(1L);
		address.setCountry("DC");
		address.setCity("Gotham City");
		address.setStreet("Arkham street 1");
		address.setPostCode("12345");

		Project project = new Project();
		project.setId(1L);
		project.setTitle("Gotham PD");

		Employee employee = new Employee();
		employee.setId(1L);
		employee.setFirstName("James");
		employee.setLastName("Gordon");

		Calendar calendar = Calendar.getInstance();
		calendar.set(1939, Calendar.MAY, 1);

		employee.setBirthday(new Date(calendar.getTime().getTime()));
		employee.setAddress(address);

		Set<Employee> employees = new HashSet<>();
		employees.add(employee);
		project.setEmployees(employees);

		Set<Project> projects = new HashSet<>();
		projects.add(project);
		employee.setProjects(projects);

		addressRepository.save(address);
		employeeRepository.save(employee);
		projectRepository.save(project);
		
		System.out.println("*******************");
		System.out.println("findAll");
		List<Employee> employeeList = employeeRepository.findAll();
		
		for(Employee e : employeeList) {
			System.out.println(e);
		}
		
		System.out.println("*******************");
		System.out.println("findOne");		
		Employee employee2 = employeeRepository.findOne(1L);
		
		System.out.println("*******************");		
		System.out.println("findByFirstNameAndlastName");
		System.out.println(employeeRepository.findByFirstNameAndlastName("James", "Gordon"));
		
		System.out.println("*******************");		
		System.out.println("findByLastName");
		System.out.println(employeeRepository.findByLastName("Gordon"));
		
	}

}
