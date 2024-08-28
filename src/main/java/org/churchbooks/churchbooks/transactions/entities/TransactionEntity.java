package org.churchbooks.churchbooks.transactions.entities;

import com.webcohesion.ofx4j.domain.data.common.Currency;
import com.webcohesion.ofx4j.domain.data.common.Payee;
import com.webcohesion.ofx4j.domain.data.common.Transaction;
import com.webcohesion.ofx4j.domain.data.common.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.churchbooks.churchbooks.transactions.enums.Status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Transaction")
@Builder
//@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    //TODO split into core transaction data and additional transaction details from ofx spec
    @Id
    private final UUID id = UUID.randomUUID();
    private TransactionType transactionType;
    private LocalDateTime datePosted;
    private BigDecimal amount;
    private String uniqueTransactionId;
    private String correctionId;
    private Status status;
    private String payeeId;
    private String name;
    private Payee payee;
    private String bankAccountTo;
    private String memo;
    private Currency currency;
    private Timestamp createdAt;


    public static TransactionEntity fromOfxTransaction(Transaction transaction) {
        return TransactionEntity.builder()
                .transactionType(transaction.getTransactionType())
                .datePosted(LocalDateTime.from(transaction.getDatePosted().toInstant().atZone(ZoneId.systemDefault())))
                // necessary conversion?
                .amount(transaction.getBigDecimalAmount())
                .uniqueTransactionId(transaction.getId())
                .correctionId(transaction.getCorrectionId())
                .payeeId(transaction.getPayeeId())
                .name(transaction.getName())
                .payee(transaction.getPayee())
                .bankAccountTo(transaction.getBankAccountTo().getAccountNumber())
                .memo(transaction.getMemo())
                .currency(transaction.getCurrency())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
}