package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.entity.Transfer;
import org.springframework.data.repository.CrudRepository;

interface TransferRepository extends CrudRepository<Transfer, String> {
}
