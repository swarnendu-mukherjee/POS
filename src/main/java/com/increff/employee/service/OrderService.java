package com.increff.employee.service;

import com.increff.employee.Dao.OrderDao;
import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Data.OrderData;
import com.increff.employee.model.Data.OrderItemData;
import com.increff.employee.pojo.InventoryPojo;
import com.increff.employee.pojo.OrderItemPojo;
import com.increff.employee.pojo.OrderPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
// TODO check if it is already placed for create, update(place) and delete
    @Transactional(rollbackFor = ApiException.class)
    public OrderPojo createOrder(){
        OrderPojo orderPojo=new OrderPojo();
        orderPojo.setTime(ZonedDateTime.now());
        orderPojo.setIsPlaced(false);
        return orderDao.createOrder(orderPojo);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void deleteOrder(Long id){
        OrderPojo orderPojo=getById(id);
        if(orderPojo.getIsPlaced()||Objects.isNull(orderPojo)){
            throw  new ApiException("No order present");
        }
        orderDao.deleteOrder(orderPojo);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void placeOrder(Long id){
        OrderPojo orderPojo=getById(id);
        if(orderPojo.getIsPlaced()||Objects.isNull(orderPojo)){
            throw  new ApiException("No order present");
        }
        orderPojo.setIsPlaced(true);
        orderDao.placeOrder(id);
    }
    @Transactional(readOnly = true)
    public List<OrderPojo> getAll(){
        return orderDao.getAll();
    }

    @Transactional(readOnly = true)
    public OrderPojo getById(Long id){
        OrderPojo orderPojo=orderDao.getById(id);
        if(orderPojo==null){
            throw new ApiException("No order exists");
        }
        return orderPojo;
    }

//    @Transactional
//    public void generateInvoice(Long orderId,List<OrderItemData> orderItemDataList){
//        ZonedDateTime time = getById(orderId).getTime();
//        double total = 0.;
//        for (OrderItemData itemData : orderItemDataList) {
//            total += itemData.getQuantity() * itemData.getSellingPrice();
//        }
//        InvoiceData invoiceData = new InvoiceData(orderItemDataList, time, total, orderId);
//        String invoice = "main/resources/Invoice/invoice" + orderId + ".pdf";
//        String xml = jaxbObjectToXML(invoiceData);
//        File xsltFile = new File("src", "main/resources/com/increff/pos/invoice.xml");
//        File pdfFile = new File("src", invoice);
//        try {
//            convertToPDF(xsltFile, pdfFile, xml);
//        } catch (IOException e) {
//            throw new ApiException(e.getMessage());
//        }
//    }
//    private void convertToPDF(File xslt, File pdf, String xml) throws ApiException, IOException {
//        OutputStream out = null;
//        try {
//            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
//            out = Files.newOutputStream(pdf.toPath());
//            out = new java.io.BufferedOutputStream(out);
//            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
//            TransformerFactory factory = TransformerFactory.newInstance();
//            Transformer transformer = factory.newTransformer(new StreamSource(xslt));
//            Source src = new StreamSource(new StringReader(xml));
//            Result res = new SAXResult(fop.getDefaultHandler());
//            transformer.transform(src, res);
//        } catch (FOPException e) {
//            System.out.println("FOP Exception");
//            throw new ApiException(e.getMessage());
//        } catch (TransformerException e) {
//            System.out.println("Transformer Exception");
//            throw new ApiException(e.getMessage());
//        } catch (IOException e) {
//            throw new ApiException(e.getMessage());
//        } finally {
//            out.close();
//        }
//    }
}
