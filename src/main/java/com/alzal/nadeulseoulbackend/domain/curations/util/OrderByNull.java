package com.alzal.nadeulseoulbackend.domain.curations.util;

import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

public class OrderByNull extends OrderSpecifier {
    public static final OrderByNull DEFAULT = new OrderByNull();

    private OrderByNull() {
        super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
    }

    private OrderByNull(Order order) {
        super(order, NullExpression.DEFAULT, NullHandling.Default);
    }

}
