package com.qpf.springcloud.controller;

import com.qpf.springcloud.entities.Department;
import com.qpf.springcloud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentProviderController {
    @Autowired
    private DepartmentService service;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Department department) {
        return service.add(department);
    }

    @GetMapping("/dept/get/{id}")
    public Department get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping("/dept/list")
    public List<Department> list() {
        return service.list();
    }

    @GetMapping("/dept/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for(String service: services) {
            System.out.println("---- " + service);
        }
        // spring.application.name
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-service-provider-dept-8001");
        for(ServiceInstance instance: instances) {
            System.out.println("-- " + instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri() + "\t" + instance.getMetadata());
        }

        return discoveryClient;
    }
}
