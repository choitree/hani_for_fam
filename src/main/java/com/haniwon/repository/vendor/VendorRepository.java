package com.haniwon.repository.vendor;

import com.haniwon.domain.Vendor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>, VendorRepositoryCustom {

    public Optional<Vendor> findByCompanyName(String companyName);
}
