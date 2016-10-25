package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by helangovan on 9/14/16.
 */

/**
 *
 * Table generator
 create table ID_GEN(
 gen_name VARCHAR(80),
 gen_val integer,
 constraint pk_id_gen primary key(gen_name)
 );

 insert into id_gen(gen_name,gen_val) values ('Emp_Gen',0);
 insert into id_gen(gen_name,gen_val) values ('Acct_Gen',1000);


 //add foreign key constraint
 alter table employee add constraint fk_dep foreign key (dept_id) references Department(id);

 alter table employee add column pspace_id integer;
 alter table employee add constraint fk_pspace foreign key (pspace_id) references parkingspace(id);

 //Many to many
 create table emp_proj(
 emp_id bigint,
 proj_id integer,
 constraint fk_emp foreign key (emp_id) references employee(id),
 constraint fk_proj foreign key (proj_id) references project(id)
 );

 create table employee_nicknames(
 employee_id bigint,
 nickname varchar(40),
 foreign key (employee_id) references Employee(id)
 );

 create table emp_phone(
 employee_id bigint,
 phone_type varchar(30),
 phone_num varchar(70),
 constraint fk_emp_phone foreign key(employee_id)  references Employee(id),
 primary key (employee_id,phone_type)
 )


 */
@Entity
public class Employee {

    @JsonProperty
    @TableGenerator(name = "Emp_Gen",table = "ID_GEN",
     pkColumnName = "GEN_NAME",
     valueColumnName = "GEN_VAL",
     pkColumnValue = "Emp_Gen",
     initialValue = 2000,
    allocationSize = 100)
    @Id @GeneratedValue(generator = "Emp_Gen",strategy = GenerationType.TABLE)
    private int id;

    @JsonProperty
    @Column(name="email")
    private String email;

    @JsonProperty
    @Column(name="first_name")
    private String firstName;

    @JsonProperty
    @Column(name="last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne //(fetch = FetchType.LAZY) single valued load are eager by default
    @JoinColumn(name="pspace_id")
    private ParkingSpace parkingSpace;

    @ManyToMany
    @JoinTable(name = "emp_proj",
              joinColumns = @JoinColumn(name="emp_id"),
              inverseJoinColumns = @JoinColumn(name = "proj_id"))
    private Collection<Project> projects;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "state",column = @Column(name="PROVINCE")),
        @AttributeOverride(name = "zip",column = @Column(name = "POSTAL_CODE"))
    })
    private Address address;

    @ElementCollection
    @CollectionTable(name = "VACATION",
     joinColumns = @JoinColumn(name="EMP_ID"))
    @AttributeOverride(name="daysTaken",
    column =@Column(name="DAYS_ABS"))
    private Collection<VacationEntry> vacationBookings;

    @ElementCollection
    private Set<String> nicknames;

    @ElementCollection
    @CollectionTable(name = "EMP_PHONE")
    @MapKeyColumn(name = "PHONE_TYPE")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "PHONE_NUM")
    private Map<PhoneType,String> phoneNUmbers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<VacationEntry> getVacationBookings() {
        return vacationBookings;
    }

    public void setVacationBookings(Collection<VacationEntry> vacationBookings) {
        this.vacationBookings = vacationBookings;
    }

    public Set<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(Set<String> nickNames) {
        this.nicknames = nickNames;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
