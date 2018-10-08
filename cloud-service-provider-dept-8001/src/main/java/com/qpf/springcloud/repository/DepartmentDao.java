package com.qpf.springcloud.repository;

import com.qpf.springcloud.entities.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentDao {
    public boolean addDepartment(Department department);
    public Department findById(Long id);
    public List<Department> findAll();
}
