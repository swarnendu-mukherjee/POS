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
    public static String selectByID="select p from ProductPojo p where id=:id";
    public static String selectByParameters="select p from ProductPojo p where barcode=:barcode " +
            "and brand_category=:brand_category and name=:name and mrp=:mrp";
    public static String selectAll="select p from ProductPojo p";
    public static String deleteByID="delete from ProductPojo p where id=:id";
    public List<ProductPojo> getAll(){
        TypedQuery<ProductPojo> query=em.createQuery(selectAll, ProductPojo.class);
        return query.getResultList();
    }
    public void delete(int id){
        Query query=em.createQuery(deleteByID);
        query.setParameter("id",id);
        query.executeUpdate();
    }
    public void update(int id, ProductPojo productPojo) {
    }
    public void add(ProductPojo productPojo) {
        em.persist(productPojo);
    }
    public ProductPojo getByID(int id) {
        TypedQuery<ProductPojo> query=em.createQuery(selectByID, ProductPojo.class);
        query.setParameter("id",id);
        return query.getResultStream().findFirst().orElse(null);
    }
    public ProductPojo getByParameters(ProductPojo productPojo){
        TypedQuery<ProductPojo> query=em.createQuery(selectByParameters, ProductPojo.class);
        query.setParameter("barcode",productPojo.getBarcode());
        query.setParameter("brand_category",productPojo.getBrand_category());
        query.setParameter("name",productPojo.getName());
        query.setParameter("mrp",productPojo.getMrp());
        return query.getResultStream().findFirst().orElse(null);
    }

}
