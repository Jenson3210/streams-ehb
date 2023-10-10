package com.colruytgroup.streams.view;

public record OrderDTO(int id, String name, List<OrderLineDTO> orderLines) {}
