package com.example.repository;

import com.example.models.Company;

import java.util.List;

public interface CompanyRepository {

    List<Company> getAllCompanies();

    void addCompany(Company company);

    Company getCompanyById(Long id);

    Company updateCompany(Company company);

    void deleteCompany(Company company);

}
