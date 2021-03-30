package com.ptit.demo.repository;

import com.ptit.demo.domain.Fullname;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Fullname entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FullnameRepository extends JpaRepository<Fullname, Long> {
}
