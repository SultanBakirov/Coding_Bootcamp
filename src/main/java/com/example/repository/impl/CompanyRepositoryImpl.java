package com.example.repository.impl;

import com.example.models.Company;
import com.example.repository.CompanyRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Company> getAllCompanies() {
        return entityManager.createQuery("select c from Company c", Company.class).getResultList();
    }

    @Override
    public void addCompany(Company company) {
        entityManager.persist(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public Company updateCompany(Company company) {
        return entityManager.merge(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        entityManager.remove(entityManager.find(Company.class, id));
    }
}
