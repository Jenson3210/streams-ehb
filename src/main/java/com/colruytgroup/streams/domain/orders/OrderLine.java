package com.colruytgroup.streams.domain.orders;

public record OrderLine(int articleId, int quantity, double price) {
    public double total() {
        return price * quantity;
    }
}
