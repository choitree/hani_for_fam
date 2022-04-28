package com.haniwon.repository.outcome;

import com.haniwon.domain.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long>, OutcomeRepositoryCustom {
}
