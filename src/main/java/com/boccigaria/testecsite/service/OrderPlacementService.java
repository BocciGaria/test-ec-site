package com.boccigaria.testecsite.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.boccigaria.testecsite.repository.CourseRepository;
import com.boccigaria.testecsite.repository.CustomerRepository;
import com.boccigaria.testecsite.repository.OrderDetailRepository;
import com.boccigaria.testecsite.repository.OrderRepository;
import com.boccigaria.testecsite.repository.PaymentRepository;
import com.boccigaria.testecsite.repository.entity.Course;
import com.boccigaria.testecsite.repository.entity.Order;
import com.boccigaria.testecsite.repository.entity.OrderDetail;

import jakarta.transaction.Transactional;

@Service
public class OrderPlacementService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void placeOrder(Order order, List<String> courseIds) {
        // 顧客情報の登録
        var existingCustomer = customerRepository.findOne(
            Specification.where(
                CustomerRepository.matchNameAddressEmailTel(
                    order.getCustomer().getName(),
                    order.getCustomer().getAddress(),
                    order.getCustomer().getEmail(),
                    order.getCustomer().getTel()
                )
            )
        );
        if (existingCustomer.isPresent()) {
            order.setCustomer(existingCustomer.get());
        } else {
            order.setCustomer(customerRepository.save(order.getCustomer()));
        }
        // 注文ヘッダーの登録
        order.setOrderDate(new Date());
        var savedOrder = orderRepository.save(order);
        // 注文詳細の登録
        Set<OrderDetail> orderDetails = new HashSet<>();
        var courses = courseRepository.findAllById(courseIds);
        Integer detailNo = 1;
        for (Course c : courses) {
            var d = new OrderDetail();
            d.setOrder(savedOrder);
            d.setDetailNo(detailNo++);
            d.setCourse(c);
            orderDetails.add(d);
        }
        orderDetailRepository.saveAll(orderDetails);
    }

}
