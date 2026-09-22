package com.flm.staff.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flm.staff.model.DoctorSchedule;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long>{

}
