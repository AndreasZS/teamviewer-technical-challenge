package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.orderitem.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "CUSTOMER_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Date createdDt;
    @LastModifiedDate
//    @UpdateTimestamp
    private Date modifiedDt;

    private Status status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    List<OrderItem> orderItems;

}
