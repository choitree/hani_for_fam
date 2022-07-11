package com.haniwon;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.domain.QIncome;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@PropertySource("classpath:application.properties")
class HaniwonApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Income income = new Income();
		em.persist(income);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QIncome qIncome = QIncome.income;
//
		Patient patient = new Patient();

		Long count = query.selectFrom(QIncome.income)
				.where(QIncome.income.patient.eq(patient))
				.fetchCount();

		System.out.println("count = " + count);
	}

}
