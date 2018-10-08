package com.qpf.springcloud.controller;

import com.qpf.springcloud.entities.Department;
import com.qpf.springcloud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentProviderController {
    @Autowired
    private DepartmentService service;

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Department department) {
        return service.add(department);
    }

    @GetMapping("/dept/get/{id}")
    public Department get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping("/dept/list")
    public List<Department> list() {
        System.out.println("/dept/list");
        return service.list();
    }
}