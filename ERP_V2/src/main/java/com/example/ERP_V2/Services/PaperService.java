package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.Paper;

import java.util.List;

public interface PaperService {
    List<Paper> getAllPapers();
    void createPaper(Paper paper);
    void updatePaper(String id, Paper updatedPaper);

    void deletePaper(String paperId);

}