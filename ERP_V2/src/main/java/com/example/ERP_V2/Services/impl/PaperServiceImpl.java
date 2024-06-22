package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Paper;
import com.example.ERP_V2.Repository.PaperRepo;
import com.example.ERP_V2.Services.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepo paperRepository; // assuming PaperRepository is a Spring Data JPA repository

    @Override
    public List<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    @Override
    public void createPaper(Paper paper) {
        paperRepository.save(paper);
    }

    @Override
    public void updatePaper(String id, Paper updatedPaper) {
        Paper existingPaper = paperRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paper not found"));

        // Update fields if they are not null in the updatedPaper object
        if (updatedPaper.getPaperType() != null) {
            existingPaper.setPaperType(updatedPaper.getPaperType());
        }
        if (updatedPaper.getRate() != null) {
            existingPaper.setRate(updatedPaper.getRate());
        }

        // Save the updated paper
        paperRepository.save(existingPaper);
    }

    @Override
    public void deletePaper(String paperId) {
        paperRepository.deleteById(paperId);
    }


}