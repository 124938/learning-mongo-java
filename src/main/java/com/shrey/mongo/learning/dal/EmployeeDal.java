package com.shrey.mongo.learning.dal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.shrey.mongo.learning.document.Address;
import com.shrey.mongo.learning.document.Job;
import com.shrey.mongo.learning.document.Employee;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.Arrays;
import java.util.function.Consumer;

@Slf4j
public class EmployeeDal {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final MongoCollection<Document> mongoCollection;

    public EmployeeDal(final String hostUri, final String databaseName, final String collection) {
        this.mongoClient = MongoClients.create(hostUri);
        this.mongoDatabase = mongoClient.getDatabase(databaseName);
        this.mongoCollection = mongoDatabase.getCollection(collection);
    }

    public void listCollection() {
        log.info("=== List collection - start ===");
        this.mongoDatabase.listCollectionNames().forEach((Consumer<String>) collection -> log.info(collection));
        log.info("=== List collection - end ===");
    }

    public void listRecords() {
        log.info("=== List records - start ===");
        this.mongoCollection.find().forEach((Consumer<Document>) record ->log.info(record.toJson()));
        log.info("=== List records - end ===");
    }

    public void createRecord(Employee employee) {
        log.info("=== Inserting record - start ===");
        try {
            Document employeeDocument = new Document(Document.parse(new ObjectMapper().writeValueAsString(employee)));
            this.mongoCollection.insertOne(employeeDocument);

            // TODO : get _id
        } catch (Exception e) {
            throw new RuntimeException("Unable to insert record into collection", e);
        }
        log.info("=== Inserting record - end ===");
    }

    public static void main(String[] args) throws Exception {
        EmployeeDal dal = new EmployeeDal("mongodb://localhost:27017", "sample_db", "employee");
        dal.listCollection();
        dal.listRecords();

        long identifier = System.currentTimeMillis();
        Employee employee = createRecord(identifier);

        dal.createRecord(employee);
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