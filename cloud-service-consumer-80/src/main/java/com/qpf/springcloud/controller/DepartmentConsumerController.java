package com.qpf.springcloud.controller;

import com.qpf.springcloud.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DepartmentConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    @GetMapping("/dept/add")
    public boolean add(Department department) {
        System.out.println("department: " + department);
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", department, Boolean.class);
    }

    @GetMapping("/dept/get/{id}")
    public Department get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Department.class);
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/dept/list")
    public List<Department> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }
}
