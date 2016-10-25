package com.example;

import com.example.model.*;
import com.example.service.DepartmentRepository;
import com.example.service.EmployeeRepository;
import com.example.service.ParkingSpaceRepository;
import com.example.service.ProjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemohipApplicationTests {


	@Inject
	DepartmentRepository departmentRepository;

	@Inject
	EmployeeRepository employeeRepository;

	@Inject
	ParkingSpaceRepository parkingSpaceRepository;

	@Inject
	ProjectRepository projectRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSaveDepartment(){
		Department department = new Department();
		department.setName("People Success");
		Address address = new Address();
		address.setState("CA");
		address.setCity("SFO");
		address.setStreet("3rd st");
		address.setZip("94105");
		department.setAddress(address);
		department = departmentRepository.save(department);
		Assert.assertNotNull(department);
		Assert.assertNotNull(department.getId());
	}

	@Test
	public void testFindEmployee(){
		Employee employee = employeeRepository.findOne(3100);
		Assert.assertNotNull(employee);
		Assert.assertNotNull(employee.getId());
	}

	@Test
	@Transactional
	public void testFindDepartment(){
		Department department = departmentRepository.findOne(1);
		Assert.assertNotNull(department);
		Assert.assertNotNull(department.getId());
		Assert.assertTrue(department.getEmployees().size()==1);
	}

	@Test
	public void testSaveEmployee(){
		Employee employee = new Employee();
		employee.setEmail("testem08@gmail.com");
		employee.setFirstName("hlts08");
		employee.setLastName("elts08");

		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setLot(203);
		parkingSpace.setLocation("East Wing EW3");
        parkingSpaceRepository.save(parkingSpace);

		Address address = new Address();
		address.setState("CA");
		address.setCity("Dublin");
		address.setStreet("Avery");
		address.setZip("94568");
		employee.setAddress(address);

		Department department = departmentRepository.findOne(1);

		employee.setParkingSpace(parkingSpace);
		employee.setDepartment(department);
		employee = employeeRepository.save(employee);
		Assert.assertNotNull(employee);
		Assert.assertNotNull(employee.getId());
	}

	@Test
	public void testManyToManyJoinTable(){
		Employee employee = new Employee();
		employee.setEmail("testem07@gmail.com");
		employee.setFirstName("hlts07");
		employee.setLastName("elts07");

		Project project = new Project();
		project.setName("Medico");
		projectRepository.save(project);
        ArrayList projList = new ArrayList();
		projList.add(project);
		employee.setProjects(projList);

		employeeRepository.save(employee);
        Assert.assertNotNull(employee);
		Assert.assertNotNull(employee.getId());
	}

	@Test
	public void testManyToManyJoinTableAE(){
		Employee employee = employeeRepository.findOne(1000);
		Project project = projectRepository.findOne(1);
		ArrayList projList = new ArrayList();
		projList.add(project);
		employee.setProjects(projList);
		employeeRepository.save(employee);
		Assert.assertNotNull(employee);
		Assert.assertNotNull(employee.getId());
	}


	//wont work as project repository is not the owning side so it wont persist (guessing...didnt search net :( )
	@Test
	public void testManyToManyJoinTableAEInverse(){
		Employee employee = employeeRepository.findOne(2102);
		Project project = projectRepository.findOne(1);
		ArrayList empList = new ArrayList();
		empList.add(project);
		project.setEmployees(empList);
		projectRepository.save(project);
		Assert.assertNotNull(project);
		Assert.assertNotNull(project.getId());
	}

	@Test
	public void testEmployeeElementCollection(){
		Employee employee = new Employee();
		employee.setEmail("testem10@gmail.com");
		employee.setFirstName("hlts10");
		employee.setLastName("elts10");

		Set<String> nicknames = new HashSet<>();
		nicknames.add("hahaha");
		nicknames.add("boobooboo");
		employee.setNicknames(nicknames);

        VacationEntry vacationEntry = new VacationEntry();
        vacationEntry.setDaysTaken(10);
        vacationEntry.setStartdate(Calendar.getInstance());
        ArrayList<VacationEntry> vacationEntries = new ArrayList<>();
        vacationEntries.add(vacationEntry);
        employee.setVacationBookings(vacationEntries);

		employeeRepository.save(employee);
		Assert.assertNotNull(employee.getId());

	}

}
