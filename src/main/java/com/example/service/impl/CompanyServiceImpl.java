package com.example.service.impl;

import com.example.models.Company;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.addCompany(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.getCompanyById(id);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.updateCompany(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepository.deleteCompanyById(id);
    }
}
