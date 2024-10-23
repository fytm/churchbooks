package org.churchbooks.churchbooks.transactions.services;

import com.webcohesion.ofx4j.io.OFXParseException;
import org.churchbooks.churchbooks.transactions.entities.Transactions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public interface TransactionService {
    List<Transactions> save(URL ofxPath) throws IOException, URISyntaxException, OFXParseException;

    List<Transactions> findAll();
}

