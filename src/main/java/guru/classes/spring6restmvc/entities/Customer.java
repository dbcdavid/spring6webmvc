package guru.classes.spring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, nullable = false, updatable = false, columnDefinition = "varchar(36)")
    private UUID id;

    @Version
    private Integer version;

    @Column(length = 255)
    private String email;


    private String customerName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;
}
