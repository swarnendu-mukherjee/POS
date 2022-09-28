package com.increff.employee.Dao;

import com.increff.employee.pojo.OrderItemPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderItemDao {

    @PersistenceContext
    private EntityManager em;

    private static final String SELECT_BY_ORDER_ID="select p from OrderItemPojo p where orderId=:orderId";
    private static final String SELECT_BY_PRODUCT_ORDER_ID="select p from OrderItemPojo p where orderId=:orderId and productId=:productId";

    private static final String DELETE_BY_ORDER_ID="delete p from OrderItemPojo p where orderId=:orderId";

    private static final String GET_All="select p from OrderItemPojo p";

    private static final String GET_BY_ID="select p from OrderItemPojo p where id=:id";

    public List<OrderItemPojo> getOrderItemsByOrderId(Long orderId){
        TypedQuery<OrderItemPojo>query=em.createQuery(SELECT_BY_ORDER_ID, OrderItemPojo.class);
        query.setParameter("orderId",orderId);
        return query.getResultList();
    }

    public OrderItemPojo getById(Long id){
        TypedQuery<OrderItemPojo>query=em.createQuery(GET_BY_ID, OrderItemPojo.class);
        query.setParameter("id",id);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public void delete(OrderItemPojo orderItemPojo){
       em.remove(orderItemPojo);
    }
    public List<OrderItemPojo> getAll(){
        TypedQuery<OrderItemPojo>query=em.createQuery(SELECT_BY_ORDER_ID, OrderItemPojo.class);
        return query.getResultList();
    }
    public OrderItemPojo getByProductOrderId(Long productId, Long orderId){
        TypedQuery<OrderItemPojo>query=em.createQuery(SELECT_BY_PRODUCT_ORDER_ID, OrderItemPojo.class);
        query.setParameter("orderId",orderId);
        query.setParameter("productId",productId);
        return query.getResultList().stream().findFirst().orElse(null);
    }
    public void update(){

    }
    public void add(OrderItemPojo orderItemPojo){
        em.persist(orderItemPojo);
    }
}
