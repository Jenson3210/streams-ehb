package com.colruytgroup.streams.domain.orders;

import com.colruytgroup.streams.domain.customer.CustomerId;

public record Order(CustomerId customer, List<OrderLine> orderlines) {}
