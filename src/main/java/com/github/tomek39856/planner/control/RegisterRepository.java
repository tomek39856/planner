package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.entity.Register;
import org.springframework.data.repository.CrudRepository;

interface RegisterRepository extends CrudRepository<Register, String> {
}
