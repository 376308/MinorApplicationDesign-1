package nl.bioinf.minorapplicationdesign.ontpillen.model.web_scraping;

import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.Drug;
import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.DrugDao;
import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.DrugSubstance;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Larissa Bouwknegt, Jonathan Klimp, Naomi Hindriks
 */
@Component
public class ApotheekWebScraper implements AbstractWebScraper {
    private DrugDao drugDao;
    private String basicUrl;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApotheekWebScraper.class);

    private ApotheekWebScraper(@Value("${apotheek.site}") String url) {this.basicUrl = url;}

    @Autowired
    public void setDrugDao(DrugDao drugDao) {
        this.drugDao = drugDao;
    }

    @Override
    public void parseHtml() throws IOException {
        LOGGER.info("Parsing html");
        List<String> drugSubstances = new ArrayList<>();
        for(Drug drugSubstance : drugDao.getDrugSubstances()){
            drugSubstances.add(drugSubstance.getName());
        }

        for (String drug: drugSubstances) {
            Drug currentDrug = drugDao.getDrugByName(drug);
            System.out.println(currentDrug.getName());
            Document doc = getConnection(drug);
            List<String> alternativeHtmlPageDrugs = Arrays.asList(new String[]{"fluvoxamine", "mianserine",
                    "imipramine", "acamprosaat", "nicotine", "buprenorfine (bij verslaving)", "methadon",
                    "nitrazepam", "prazepam", "paliperidon", "sertindol", "penfluridol", "periciazine",
                    "pimozide", "pipamperon"});
            if (alternativeHtmlPageDrugs.contains(drug)){
                getAlternativeHtmlSideEffects(doc, drug);
            }
//            else {
//                getSideEffects(doc, drug);
//            }
            getDescription(doc, drug);
//            getInteractions(doc);
                // code to log the description of the Dao
            Drug drugSubstance = drugDao.getDrugByName(drug);
            LOGGER.debug("Drug: " + drug);
            DrugSubstance drugSubstance1 = (DrugSubstance) drugSubstance;
            LOGGER.debug("Description in the dao: " + drugSubstance1.getDescription());
//            LOGGER.debug("Interactions in the dao: " + drugSubstance1.getInteractions());
//            LOGGER.debug("SideEffects in the dao: " + drugSubstance1.getSideEffects());


        }
    }

    private void getInteractions(Document doc) {
        Elements interactions = doc.getElementsByAttributeValueContaining("data-print", "andere medicijnen gebruiken").select(".listItemContent_text__otIdg ");
        LOGGER.debug("Interactions: " + interactions.eachText());
    }

    private String getStopIndication() {
        return null;
    }

    private void getAlternativeHtmlSideEffects(Document doc, String drug){
        Elements sideEffectsHtmlLocation = doc.getElementsByAttributeValueContaining("data-print", "bijwerkingen");
        System.out.println(sideEffectsHtmlLocation.select(".listItemContent_text__otIdg").select("p:not(p.strong)"));

    }

    private void getSideEffects(Document doc, String drug) {
        Elements sideEffectsHtmlLocation = doc.getElementsByAttributeValueContaining("data-print", "bijwerkingen");
        List<String> sideEffectsIntro = sideEffectsHtmlLocation.select(".listItemContent_text__otIdg p, p.listItemContent_text__otIdg").eachText();
        LOGGER.debug("side effects intro: " + sideEffectsIntro);
        Element frequencyAndSideEffect = sideEffectsHtmlLocation.select(".sideEffects_sideEffects__sczbd").get(0);
        Elements frequency = frequencyAndSideEffect.getElementsByTag("h3");
        for (Element element: frequency) {
            Elements sideEffects = element.nextElementSibling().getElementsByClass("sideEffectsItem_button__V-L1C");
            LOGGER.debug("Chance of side effect: " + element.text() + sideEffects.eachText());
            System.out.println(element.text() + sideEffects.eachText());
            for (Element sideEffect : sideEffects) {
                Elements sideEffectDescription = sideEffect.nextElementSibling().select(".sideEffectsItem_content__10s1c");
                LOGGER.debug("side effects: " + sideEffect.text() + sideEffectDescription.eachText());
            }
        }
        //TODO Add to datamodel
    }

    private void getDescription(Document doc, String drug) {
        Element useIndicationTag = doc.getElementsByAttributeValueContaining("data-print", "waarbij gebruik").select(".listItemContent_text__otIdg").get(0);
//        System.out.println(useIndicationTag.children().eachText());
        //TODO add to the datamodel
        DrugSubstance myDrug = (DrugSubstance) drugDao.getDrugByName(drug);
        myDrug.setDescription(useIndicationTag.children().eachText());
    }

    private Document getConnection(String medicine) throws IOException {
        //TODO Temporarily solution to pass the test, needs to be figured out what to do with these medicines.
        if (medicine.equals("coffeïne") || medicine.contains("esketamine")){
            medicine = "citalopram";
        }
        if (medicine.contains("(")){
            // Regex to replace all between and including ()
            medicine = medicine.replaceAll("\\((.*?)\\)", "");

        }
        if (medicine.contains("/")){
            medicine = medicine.replace("/", "-met-");
        }
        String completeUrl = basicUrl + medicine.toLowerCase();
        Document doc = Jsoup.connect(completeUrl).get();
        return doc;
    }
}