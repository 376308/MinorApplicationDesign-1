package nl.bioinf.minorapplicationdesign.ontpillen.model.web_scraping;

import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.DrugDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Larissa Bouwknegt
 */

@SpringBootTest
class IndicationFetcherTest {

    @Autowired
    DrugDao drugDao;

    @Autowired
    IndicationFetcher indicationFetcher;

    @Autowired
    DrugFetcher drugFetcher;

    @BeforeEach
    public void storeDrugs() throws IOException {
        SSLHelper.bypassSSL();
        drugFetcher.parseHtml();
    }

    @Test
    void parseHtml() throws IOException {
        indicationFetcher.parseHtml();
    }
}