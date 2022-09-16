package com.increff.employee.Dao;

import com.increff.employee.pojo.BrandPojo;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BrandDao extends AbstractDao {

    public static final String deleteByID = "delete from BrandPojo P where id=:id";
    public static final String selectByID = "select p from BrandPojo p where id=:id";
    public static final String selectByNameAndCategory = "select p from BrandPojo p where brandName=:brandName and categoryName=:categoryName";
    public static final String selectAll = "select p from BrandPojo p";

    @PersistenceContext
    private EntityManager em;

    public List<BrandPojo> getAll() {
        List<BrandPojo> brandPojo = new ArrayList<>();
        TypedQuery<BrandPojo> query = em.createQuery(selectAll, BrandPojo.class);
        return query.getResultList();
    }

    public void add(BrandPojo brandPojo) {
        em.persist(brandPojo);
    }

    public void delete(int id) {
        Query query = em.createQuery(deleteByID);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void update(BrandPojo brandPojo) {
    }

    public BrandPojo getByID(int id) {
        TypedQuery<BrandPojo> query = em.createQuery(selectByID, BrandPojo.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public BrandPojo getByNameAndCategory(String brandName, String categoryName) {
        TypedQuery<BrandPojo> query = em.createQuery(selectByNameAndCategory, BrandPojo.class);
        query.setParameter("brandName", brandName);
        query.setParameter("categoryName", categoryName);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
