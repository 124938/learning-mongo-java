package com.shrey.mongo.learning.dal;

import com.shrey.mongo.learning.document.Address;
import com.shrey.mongo.learning.document.Employee;
import com.shrey.mongo.learning.document.Job;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class EmployeeDalTest {

    public static void main(String[] args) throws Exception {
        // Create instance of dal
        EmployeeDal dal = new EmployeeDal("mongodb://localhost:27017", "sample_db", "employee");

        // Fetch all employees
        dal.list();

        // Insert new employee
        long identifier = System.currentTimeMillis();
        String id = dal.create(createRecord(identifier));

        // Fetch employee by id
        Employee e = dal.fetchById(id);
        log.info(e.toString());

        // Fetch history/job details of employee by id
        List<Job> historyCurrent = dal.fetchJobById(id);
        log.info(historyCurrent.toString());

        // Update job details of employee using company by id
        dal.updateJobUsingCompanyById(id, historyCurrent.get(0));

        // Fetch history/job details of employee by id
        List<Job> historyAfter = dal.fetchJobById(id);
        log.info(historyAfter.toString());
    }

    private static Employee createRecord(long identifier) {
        Address address = new Address();
        address.setCountry("India");
        address.setState("Maharashtra");
        address.setCity("Pune "+identifier);

        Job job1 = new Job();
        job1.setCompany("CTS "+identifier);
        job1.setStart("11-May-2005");
        job1.setEnd("15-Apr-2016");

        Job job2 = new Job();
        job2.setCompany("DB "+identifier);
        job2.setStart("05-May-2016");
        job2.setEnd("Till date");

        Employee employee = new Employee();
        employee.setFirstName("Shreyash "+identifier);
        employee.setLastName("Limbhetwala "+identifier);
        employee.setAddress(address);
        employee.setHistory(Arrays.asList(job1, job2));

        return employee;
    }
}
