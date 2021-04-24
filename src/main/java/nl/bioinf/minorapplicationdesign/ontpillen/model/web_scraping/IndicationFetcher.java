package nl.bioinf.minorapplicationdesign.ontpillen.model.web_scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IndicationFetcher {


    public static void main(String[] args) throws IOException {
        SSLHelper.bypassSSL();
        String link = "https://www.farmacotherapeutischkompas.nl/bladeren/categorie/psychiatrie";

        Document doc = Jsoup.connect(link).get();
        Elements indicationHtmlElements = doc.getElementsByClass("pat-rich group-1").select("h2");
        for (Element indication: indicationHtmlElements) {
            System.out.println("The indication is "+ indication.text() + " the drugs are " + indication.nextElementSibling().text());
        }

    }
}
