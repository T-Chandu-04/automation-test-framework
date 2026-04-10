package com.test.regressionframework.repository;

import com.test.regressionframework.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestExecutionRepository extends JpaRepository<TestResult, Long> {
}