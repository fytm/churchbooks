package org.churchbooks.churchbooks.transactions.services;

import com.webcohesion.ofx4j.domain.data.common.Transaction;
import com.webcohesion.ofx4j.io.OFXParseException;
import org.churchbooks.churchbooks.transactions.entities.Transactions;
import org.churchbooks.churchbooks.transactions.repositories.TransactionRepository;
import org.churchbooks.churchbooks.transactions.util.TransactionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionLoader transactionLoader;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(@Autowired TransactionLoader _transactionLoader, @Autowired TransactionRepository _transactionRepository) {
        transactionLoader = _transactionLoader;
        transactionRepository = _transactionRepository;
    }

    @Override
    public List<Transactions> save(URL ofxPath) throws IOException, URISyntaxException, OFXParseException {
        List<Transaction> transactions = transactionLoader.loadTransactionsFromURI(ofxPath.toString());
        return transactionRepository.saveAll(transactions.stream()
                .map(Transactions::fromOfxTransaction)
                .collect(Collectors.toList())
        );
    }


    @Override
    public List<Transactions> findAll(){
     return transactionRepository.findAll();
    }

}
