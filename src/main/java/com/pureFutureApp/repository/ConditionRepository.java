package com.pureFutureApp.repository;

import com.pureFutureApp.model.entity.Condition;
import com.pureFutureApp.model.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, UUID> {
    Condition findByName(ConditionName condition);
}
