
package com.lnrs.repository;


import com.lnrs.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findAllByCompanyNumber(String company_number);
}

