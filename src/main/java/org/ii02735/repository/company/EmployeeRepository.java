package org.ii02735.repository.company;


import org.ii02735.entity.company.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findFirstByName(String name);
}
