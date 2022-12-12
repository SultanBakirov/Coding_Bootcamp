package com.example.service;

import com.example.models.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    void addCompany(Company company);

    Company getCompanyById(Long id);

    Company updateCompany(Company company);

    void deleteCompanyById(Long id);

}
