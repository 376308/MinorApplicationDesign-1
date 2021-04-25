package nl.bioinf.minorapplicationdesign.ontpillen.model.web_scraping;

import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.Drug;
import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.DrugDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Larissa Bouwknegt
 */

@Component
@PropertySource("classpath:application.properties")
public class IndicationFetcher implements AbstractWebScraper{
    private DrugDao drugDao;
    private String url;
    private List<String> drugs = new ArrayList<>();

    private IndicationFetcher(@Value("${farmaco.indication.site}") String url){this.url=url;}

    @Autowired
    public void setDrugDao(DrugDao drugDao) {
        this.drugDao = drugDao;
    }

    @Override
    public void parseHtml() throws IOException {
        SSLHelper.bypassSSL();
        Document doc = Jsoup.connect(url).get();
        Elements indicationHtmlElements = doc.getElementsByClass("pat-rich group-1").select("h2");
        List<String> daoDrugs = drugDao.getAllDrugNames();
        for (Element indication: indicationHtmlElements) {
            String useIndication = indication.text();
            List<String> parsedDrugs = indication.nextElementSiblings().select("li").eachText();

            drugs.addAll(parsedDrugs);
            System.out.println("The indication is "+ useIndication + " the drugs are " + drugs);
        }
        checkDrugDao(daoDrugs);

    }
    private void checkDrugDao(List<String> daoDrugs){
        for (String drug: drugs) {
            if (daoDrugs.contains(drug)) {
                Drug currentDrug = drugDao.getDrugByName(drug);
                System.out.println(currentDrug.getName());

            }
            else {
                System.out.println("drug not in drugdao");
            }
        }
    }


}
