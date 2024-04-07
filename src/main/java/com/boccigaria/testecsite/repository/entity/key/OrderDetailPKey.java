package com.boccigaria.testecsite.repository.entity.key;

import java.io.Serializable;

import com.boccigaria.testecsite.repository.entity.Order;

public class OrderDetailPKey implements Serializable {
    private Order order;
    private Integer detail_no;

    public OrderDetailPKey() {

    }

    public OrderDetailPKey(Order order, Integer detail_no) {
        this.order = order;
        this.detail_no = detail_no;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof OrderDetailPKey == false) return false;
        if (this.order.getId() != ((OrderDetailPKey)obj).order.getId()) return false;
        if (this.detail_no != ((OrderDetailPKey)obj).detail_no) return false;
        return true;
    }

    public int hashCode() {
        return order.getId() * 100 + detail_no;
    }
}
