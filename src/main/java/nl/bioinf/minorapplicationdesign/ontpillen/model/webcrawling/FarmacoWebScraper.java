package nl.bioinf.minorapplicationdesign.ontpillen.model.webcrawling;


import nl.bioinf.minorapplicationdesign.ontpillen.model.MedicineDAO.DrugDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
@Configuration
@PropertySource("classpath:values.properties")
public class FarmacoWebScraper extends AbstractWebScraper {

    private static String basicUrl;

    @Value("${farmaco.basic.site}")
    public void setBasicUrl(String basicUrl) {
        FarmacoWebScraper.basicUrl = basicUrl;
    }

    FarmacoWebScraper(DrugDao drugDao) {
        super(drugDao);
    }

    @Override
    public void parseHtml() {
        String drugs = getDrugs(drugDao);
        information.add(drugs);
    }


    private String getDrugs(DrugDao informationStorage) {
//        Drug drug = new DrugGroup();
//        drugDao.addDrugSubstance(null);
        return null;
    }


    private static Document getConnection(String medicine) throws IOException {

//        String basicUrl = "https://www.farmacotherapeut.nl/bladeren/preparaatteksten/";
        String completeUrl = (basicUrl + medicine.charAt(0) + "/" + medicine).toLowerCase(Locale.ROOT);
        Document doc = Jsoup.connect(completeUrl).get();
        return doc;
    }

    // FIXME needs to be linked to the data model
    private static void parseInformation( List<String> druglist) throws IOException {
        SSLHelper.bypassSSL();

        for (String medicine : druglist) {
            Document doc = getConnection(medicine);
            Elements h2Tags = doc.getElementsByTag("h2");
            List<String> sideEffects = h2Tags.select(":contains(Bijwerkingen)").nextAll().select("p").eachText();
            List<String> drugDescription = h2Tags.select(":contains(Advies)").nextAll().eachText();
            System.out.println(sideEffects);
            System.out.println(drugDescription);

        }

    }

    public static void main(String[] args) throws IOException {
//        DrugDao informationStorage,
        List drugs = Arrays.asList("Citalopram", "Lorazepam", "Temazepam");
        parseInformation(drugs);
    }
}
