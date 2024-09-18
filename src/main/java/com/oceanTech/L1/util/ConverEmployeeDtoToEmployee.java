package com.oceanTech.L1.util;

import com.oceanTech.L1.dto.request.EmployeeCreateRequest;
import com.oceanTech.L1.entity.Commune;
import com.oceanTech.L1.entity.District;
import com.oceanTech.L1.entity.Employee;
import com.oceanTech.L1.entity.Province;

public class ConverEmployeeDtoToEmployee {
    public Employee convert(EmployeeCreateRequest request) {
        Employee employee = new Employee();
        employee.setCode(request.getCode());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAge(request.getAge());

        Province province = new Province();
        province.setName(request.getProvince());
        employee.setProvince(province);

        District district = new District();
        district.setName(request.getDistrict());
        employee.setDistrict(district);

        Commune commune = new Commune();
        commune.setName(request.getCommune());
        employee.setCommune(commune);
        return employee;
    }
}
