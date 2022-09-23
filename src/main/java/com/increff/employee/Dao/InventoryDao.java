package com.increff.employee.Dao;

import com.increff.employee.pojo.InventoryPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryDao {

    public static final String SelectAll="select p from InventoryPojo p";
    public static final String SelectByID="select p from InventoryPojo p where id=:id";

    @PersistenceContext
    private EntityManager em;




    public void update(Long id, Long quantity){

    }
    public List<InventoryPojo> getAll(){
        //List<InventoryPojo> inventoryPojoList=new ArrayList<>();
        TypedQuery<InventoryPojo> query=em.createQuery(SelectAll, InventoryPojo.class);
        return query.getResultList();
    }
    public InventoryPojo getById(Long id){
        TypedQuery<InventoryPojo> query=em.createQuery(SelectByID, InventoryPojo.class);
        query.setParameter("id",id);
        return query.getResultStream().findFirst().orElse(null);
    }
    public void add(InventoryPojo inventoryPojo){
        em.persist(inventoryPojo);
    }
}
