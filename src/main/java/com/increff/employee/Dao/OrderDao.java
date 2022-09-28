package com.increff.employee.Dao;

import com.increff.employee.pojo.OrderPojo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderDao {

    private static final String GET_ALL="select p from OrderPojo p";
    private static final String GET_BY_ID="select p from OrderPojo p where id=:id";

    private static final String DELETE="delete p from OrderPojo p where id=:id";

    @PersistenceContext
    private EntityManager em;


    public OrderPojo createOrder(OrderPojo orderPojo){
        em.persist(orderPojo);
        return orderPojo;
    }
    public void placeOrder(Long id){

    }
    public void deleteOrder(OrderPojo orderPojo){
        em.remove(orderPojo);
    }
    public List<OrderPojo> getAll(){
        TypedQuery<OrderPojo> query=em.createQuery(GET_ALL, OrderPojo.class);
        return query.getResultList();
    }

    public OrderPojo getById(Long id){
        TypedQuery<OrderPojo> query=em.createQuery(GET_BY_ID, OrderPojo.class);
        query.setParameter("id",id);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
