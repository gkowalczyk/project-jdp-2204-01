package com.kodilla.ecommercee.dto;

// OrderDto dummy implementation because we don't know DB structure yet
public class OrderDto {
    private Long id;
    private String description;

    public OrderDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        if (id != null ? !id.equals(orderDto.id) : orderDto.id != null) return false;
        return description != null ? description.equals(orderDto.description) : orderDto.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
