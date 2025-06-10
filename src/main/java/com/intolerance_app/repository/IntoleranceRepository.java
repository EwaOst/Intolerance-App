package com.intolerance_app.repository;

import com.intolerance_app.model.IntoleranceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntoleranceRepository extends JpaRepository<IntoleranceModel, Long> {
}
