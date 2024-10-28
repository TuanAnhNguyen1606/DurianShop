package com.example.exe201.repository;

import com.example.exe201.model.SiteVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitSiteRepository extends JpaRepository<SiteVisit, Long> {
}
