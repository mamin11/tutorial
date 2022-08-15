package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippedOrder {
    private long orderId;
    private String firstName;
    private String lastName;
    private String email;
    private String cost;
    private String itemId;
    private String itemName;
    private Date shipDate;
    private String trackingNumber;
    private boolean freeShipping;
}
