package by.halkina.authservice.entity;

import by.halkina.authservice.converter.HashMapConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @CreationTimestamp
    private Timestamp timestamp;

    @Column
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column
    @NotBlank(message = "Actor is mandatory")
    private String actor;

    @Column
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> transactionData = new HashMap<>();

}
