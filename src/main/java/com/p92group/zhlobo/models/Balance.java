package com.p92group.zhlobo.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "balances")
@Getter
@Setter
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "balance_status")
    @Enumerated(EnumType.STRING)
    private BalanceStatus balanceStatus = BalanceStatus.enable;

    @Column(name = "value")
    private BigDecimal value = BigDecimal.ZERO;

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", balanceStatus=" + balanceStatus +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Balance balance = (Balance) obj;
        return Objects.equals(this.balanceStatus, balance.balanceStatus) && Objects.equals(this.value, balance.value);
    }

    public void updateData(Balance obj){
        this.createdAt = LocalDateTime.now();
        this.value = obj.value;
        this.balanceStatus = obj.balanceStatus;
    }

}
