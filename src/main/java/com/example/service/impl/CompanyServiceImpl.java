package com.example.service.impl;

import com.example.models.Company;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public void addCompany(Company company) throws IOException{
        validator(company.getCompanyName(), company.getLocatedCountry());
        companyRepository.addCompany(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.getCompanyById(id);
    }

    @Override
    public Company updateCompany(Company company) throws IOException{
        validator(company.getCompanyName(), company.getLocatedCountry());
        return companyRepository.updateCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyRepository.deleteCompany(company);
    }

    private void validator(String companyName, String locatedCountry) throws IOException {
        if (companyName.length()>2 && locatedCountry.length()>2) {
            for (Character i : companyName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("Numbers cannot be inserted in the company name");
                }
            }
            for (Character i : locatedCountry.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("Numbers cannot be inserted in the country name");
                }
            }
        }else {
            throw new IOException("The name of the company or country must contain at least 2 letters");
        }
    }
}
