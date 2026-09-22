package com.flm.staff.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flm.staff.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>{

}
