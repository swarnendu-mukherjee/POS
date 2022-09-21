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

    private static final String DELETEBYID = "delete from BrandPojo P where id=:id";
    private static final String SELECTBYID = "select p from BrandPojo p where id=:id";
    private static final String SELECTBYNAMEANDCATEGORY = "select p from BrandPojo p where brandName=:brandName and categoryName=:categoryName";
    private static final String SELECTALL = "select p from BrandPojo p";

    @PersistenceContext
    private EntityManager em;

    public List<BrandPojo> getAll() {
        List<BrandPojo> brandPojo = new ArrayList<>();
        TypedQuery<BrandPojo> query = em.createQuery(SELECTALL, BrandPojo.class);
        return query.getResultList();
    }

    public void add(BrandPojo brandPojo) {
        em.persist(brandPojo);
    }

    public void delete(long id) {
        Query query = em.createQuery(DELETEBYID);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void update(BrandPojo brandPojo) {
    }

    public BrandPojo getByID(Long id) {
        TypedQuery<BrandPojo> query = em.createQuery(SELECTBYID, BrandPojo.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public BrandPojo getByNameAndCategory(String brandName, String categoryName) {
        TypedQuery<BrandPojo> query = em.createQuery(SELECTBYNAMEANDCATEGORY, BrandPojo.class);
        query.setParameter("brandName", brandName);
        query.setParameter("categoryName", categoryName);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
