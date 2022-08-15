package theoneamin.com.tutorial.batch;

import models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersMapper implements org.springframework.jdbc.core.RowMapper<models.Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder()
                .orderId(rs.getLong("order_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .cost(rs.getString("cost"))
                .itemId(rs.getString("item_id"))
                .itemName(rs.getString("item_name"))
                .shipDate(rs.getDate("ship_date"))
                .nextDay(rs.getBoolean("next_day"))
                .build();
    }
}
