package org.churchbooks.churchbooks.transactions.dtos;

import com.webcohesion.ofx4j.domain.data.common.Currency;
import lombok.Getter;
import org.churchbooks.churchbooks.transactions.enums.AccountGroup;
import org.churchbooks.churchbooks.transactions.enums.TransactionType;
import org.churchbooks.churchbooks.transactions.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CreateTransactionDTO {
    private TransactionType transactionType;
    private AccountGroup accountGroup;
    private LocalDateTime datePosted;
    private BigDecimal amount;
    private Status status;
    private String name;
    private String bankAccountTo;
    private String memo;
    private String currency;
}
