package org.churchbooks.churchbooks.transactions.entities;

import com.webcohesion.ofx4j.domain.data.common.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.churchbooks.churchbooks.transactions.dtos.CreateTransactionDTO;
import org.churchbooks.churchbooks.transactions.enums.Source;
import org.churchbooks.churchbooks.transactions.enums.Status;
import org.churchbooks.churchbooks.transactions.enums.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

//@EqualsAndHashCode(callSuper = true)
@Entity
//@Table(name = "Transaction")
@Builder
//@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    // core transaction data
    @Id
    private final UUID id = UUID.randomUUID();
    private TransactionType transactionType;
    private LocalDateTime datePosted; // date of transaction
    private BigDecimal amount;
    private Status status;
    private String memo;
    private Source source;
    private Timestamp createdAt = Timestamp.from(Instant.now()); // time transaction started being tracked //external modification tracking table can track modified at time along with number of modifications

    // additional transaction details from ofx spec
/*
    private String currency;
    private String uniqueTransactionId; //breakdown
    private String correctionId; // id
*/
/*
    private String name;
    private String theiraccountNumber; // account of external party. The user's account number will be fixed for each monetary account
    private String theirAccountGroup; // account type of external party

    private String myaccountNumber; // account of external party. The user's account number will be fixed for each monetary account
    private String myaccountType; // account type of external party
*/

public static Transactions fromOfxTransaction(Transaction transaction) {
    return Transactions.builder()
            .transactionType(TransactionType.CREDIT)
            .datePosted(LocalDateTime.from(transaction.getDatePosted().toInstant().atZone(ZoneId.systemDefault())))
            .amount(transaction.getBigDecimalAmount())
            .status(Status.VALID)
            .memo(transaction.getMemo())
            .source(Source.OFX)
            .build();
}

    public static Transactions fromRequest(CreateTransactionDTO createTransactionDTO){
        return Transactions.builder()
                .transactionType(createTransactionDTO.getTransactionType())
                .datePosted(createTransactionDTO.getDatePosted())
                .amount(createTransactionDTO.getAmount())
                .status(createTransactionDTO.getStatus())
                .memo(createTransactionDTO.getMemo())
                .source(Source.USER)
                .build();
    }
//
//    private static TransactionType transactionTypeMapper(com.webcohesion.ofx4j.domain.data.common.TransactionType ofxTransactionType){
//       if (ofxTransactionType == CREDIT || ofxTransactionType == DIV || ofxTransactionType == INT || ofxTransactionType == DEP) {
//           return TransactionType.CREDIT;
//       } else if (ofxTransactionType== DEBIT || ofxTransactionType == FEE || ofxTransactionType == SRVCHG || ofxTransactionType == XFER) {
//           return TransactionType.DEBIT;
//       }else if(){
//           return TransactionType.TRANSFER;
//
//       }
//    }
}