package com.qpf.springcloud.service.impl;

import com.qpf.springcloud.entities.Department;
import com.qpf.springcloud.repository.DepartmentDao;
import com.qpf.springcloud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public boolean add(Department department) {
        return departmentDao.addDepartment(department);
    }

    @Override
    public Department get(Long id) {
        return departmentDao.findById(id);
    }

    @Override
    public List<Department> list() {
        return departmentDao.findAll();
    }
}
