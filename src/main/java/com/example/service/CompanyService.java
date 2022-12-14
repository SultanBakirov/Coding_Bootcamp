package com.example.service;

import com.example.models.Company;

import java.io.IOException;
import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    void addCompany(Company company) throws IOException;

    Company getCompanyById(Long id);

    Company updateCompany(Company company) throws IOException;

    void deleteCompany(Company company);

}
