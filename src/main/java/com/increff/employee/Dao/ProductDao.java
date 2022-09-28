package com.increff.employee.Dao;

import com.increff.employee.pojo.ProductPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDao {

    @PersistenceContext
    private EntityManager em;
    private static final String SELECTBYID="select p from ProductPojo p where id=:id";

    private static final String SELECTBYBARCODE="select p from ProductPojo P where barcode=:barcode";
    //private static final String SELETBYPARAMETERS="select p from ProductPojo p where barcode=:barcode";
    private static final String SELECTALL="select p from ProductPojo p";
    private static final String DELETEBYID="delete from ProductPojo p where id=:id";
    public List<ProductPojo> getAll(){
        TypedQuery<ProductPojo> query=em.createQuery(SELECTALL, ProductPojo.class);
        return query.getResultList();
    }
    public void delete(Long id){
        Query query=em.createQuery(DELETEBYID);
        query.setParameter("id",id);
        query.executeUpdate();
    }
    public void update(Long id, ProductPojo productPojo) {
    }
    public void add(ProductPojo productPojo) {
        em.persist(productPojo);
    }
    public ProductPojo getByID(Long id) {
        TypedQuery<ProductPojo> query=em.createQuery(SELECTBYID, ProductPojo.class);
        query.setParameter("id",id);
        return query.getResultStream().findFirst().orElse(null);
    }
    public ProductPojo getByBarcode(String barcode){
        TypedQuery<ProductPojo> query=em.createQuery(SELECTBYBARCODE, ProductPojo.class);
        query.setParameter("barcode",barcode);
        return query.getResultStream().findFirst().orElse(null);
    }

}
