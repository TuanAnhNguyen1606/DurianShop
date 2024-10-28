package com.example.exe201.service;

import com.example.exe201.model.SiteVisit;
import com.example.exe201.repository.VisitSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitSiteService {
    @Autowired
    private VisitSiteRepository visitSiteRepository;

    public void incrementVisitCount() {
        SiteVisit siteVisit = visitSiteRepository.findById(1L).orElse(new SiteVisit());
        siteVisit.setTotalVisits(siteVisit.getTotalVisits() + 1);
        visitSiteRepository.save(siteVisit);
    }
    public long getTotalVisits() {
        return visitSiteRepository.findById(1L).map(SiteVisit::getTotalVisits).orElse(0L);
    }
}
