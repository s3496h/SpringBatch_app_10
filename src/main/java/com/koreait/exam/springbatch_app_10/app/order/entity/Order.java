package com.koreait.exam.springbatch_app_10.app.order.entity;

import com.koreait.exam.springbatch_app_10.app.base.entity.BaseEntity;
import com.koreait.exam.springbatch_app_10.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "product_order")
public class Order extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member buyer;
    private String name;
    private boolean isPaid;
    private boolean isCanceled;
    private boolean isRefunded;
    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }
    public int calculatePayPrice() {
        int payPrice = 0;
        for (OrderItem orderItem : orderItems) {
            payPrice += orderItem.getSalePrice();
        }
        return payPrice;
    }
    public void setPaymentDone() {
        for (OrderItem orderItem : orderItems) {
            orderItem.setPaymentDone();
        }
        isPaid = true;
    }
    public void setRefundDone() {
        for (OrderItem orderItem : orderItems) {
            orderItem.setRefundDone();
        }
        isRefunded = true;
    }
    public int getPayPrice() {
        int payPrice = 0;
        for (OrderItem orderItem : orderItems) {
            payPrice += orderItem.getSalePrice();
        }
        return payPrice;
    }
    public void makeName() {
        String name = orderItems.get(0).getProduct().getTitle();
        if (orderItems.size() > 1) {
            name += " 외 %d곡".formatted(orderItems.size() - 1);
        }
        this.name = name;
    }
}