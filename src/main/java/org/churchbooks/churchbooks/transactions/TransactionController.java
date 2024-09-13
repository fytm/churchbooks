package org.churchbooks.churchbooks.transactions;

import com.webcohesion.ofx4j.io.OFXParseException;
import org.churchbooks.churchbooks.transactions.dtos.CreateTransactionDTO;
import org.churchbooks.churchbooks.transactions.entities.Transactions;
import org.churchbooks.churchbooks.transactions.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<Transactions> findAll(){
        return transactionService.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Transactions createTransaction(CreateTransactionDTO createTransactionDTO){
        return transactionService.save(createTransactionDTO);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    List<Transactions>  createTransactionsFromOFX(URL ofxFile) throws IOException, URISyntaxException, OFXParseException {
        return transactionService.saveFromOFX(ofxFile);
    }


}
