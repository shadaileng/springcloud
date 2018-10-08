package com.qpf.springcloud.service;

import com.qpf.springcloud.entities.Department;

import java.util.List;

public interface DepartmentService {
    public boolean add(Department department);
    public Department get(Long id);
    public List<Department> list();
}
