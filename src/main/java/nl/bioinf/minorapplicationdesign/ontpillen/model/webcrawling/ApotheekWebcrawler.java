package nl.bioinf.minorapplicationdesign.ontpillen.model.webcrawling;

import nl.bioinf.minorapplicationdesign.ontpillen.model.MedicineDAO.DrugsDao;
import nl.bioinf.minorapplicationdesign.ontpillen.model.MedicineDAO.UseIndication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApotheekWebcrawler extends AbstractWebcrawler {

    ApotheekWebcrawler(DrugsDao drugsDao) {
        super(drugsDao);
    }

    List<String> information = new ArrayList<>();

    @Override
    public List<String> getInformation() {
//        String description = getDescription();
//        String sideEffects = getSideEffects();
//        String stopIndication = getStopIndication();
//        String interactions = getInteractions();
//
//        //information.add(description);
//        information.add(sideEffects);
//        information.add(stopIndication);
//        information.add(interactions);
//        return information;
        return null;
    }

    private String getInteractions() {
        return null;
    }

    private String getStopIndication() {
        return null;
    }

    private static String getSideEffects(Document doc) {
        Elements sideEffects = doc.getElementsByAttributeValueContaining("data-print", "bijwerkingen").select(" .listItemContent_text__otIdg, .sideEffects_sideEffects__sczbd");
        System.out.println(sideEffects.eachText());
        //TODO Titel weg en frequenties niet nodig
        return null;
    }

    private static String getDescription(Document doc) {
        Element useIndicationTag = doc.getElementsByAttributeValueContaining("data-print", "waarbij gebruik").select(".listItemContent_text__otIdg").get(0);
        System.out.println(useIndicationTag.children().eachText());
        //TODO add to the datamodel
        return null;
    }

    private static Document getConnection(String medicine) throws IOException {
        String basicUrl = "https://www.apotheek.nl/medicijnen/";
        String completeUrl = basicUrl + medicine.toLowerCase();
        Document doc = Jsoup.connect(completeUrl).get();
        return doc;
    }

    public static void main(String[] args) throws IOException {
        List<String> medicines = List.of("citalopram", "lorazepam", "Temazepam");
        for (String drug: medicines) {
            Document doc = getConnection(drug);
            //getDescription(doc);
            getSideEffects(doc);

        }
    }
}

// <li class="listItemContent_container__25F5W" data-print="Wat zijn mogelijke bijwerkingen?" data-section="wat-zijn-mogelijke-bijwerkingen" id="bijwerkingen"><button class="listItemContent_toggleButton__hmbW8"><div class="listItemContent_toggleIconWrapper__2MMIr"><div class="listItemContent_toggleIcon__WmYxX"><svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 35"><g fill="none"><path d="M17.5,0A17.5,17.5,0,1,1,0,17.5,17.5,17.5,0,0,1,17.5,0Z" stroke="none"></path><path d="M 17.5 2 C 13.35980033874512 2 9.467409133911133 3.612279891967773 6.539850234985352 6.539850234985352 C 3.612279891967773 9.467409133911133 2 13.35980033874512 2 17.5 C 2 21.64019966125488 3.612279891967773 25.53258895874023 6.539850234985352 28.46014976501465 C 9.467409133911133 31.38772010803223 13.35980033874512 33 17.5 33 C 21.64019966125488 33 25.53258895874023 31.38772010803223 28.46014976501465 28.46014976501465 C 31.38772010803223 25.53258895874023 33 21.64019966125488 33 17.5 C 33 13.35980033874512 31.38772010803223 9.467409133911133 28.46014976501465 6.539850234985352 C 25.53258895874023 3.612279891967773 21.64019966125488 2 17.5 2 M 17.5 0 C 27.16497993469238 0 35 7.835020065307617 35 17.5 C 35 27.16497993469238 27.16497993469238 35 17.5 35 C 7.835020065307617 35 0 27.16497993469238 0 17.5 C 0 7.835020065307617 7.835020065307617 0 17.5 0 Z" stroke="none" fill="currentColor"></path></g><rect width="17" height="2" transform="translate(9 17)" fill="currentColor"></rect></svg></div><h2>Wat zijn mogelijke bijwerkingen?</h2></div></button><div class="listItemContent_content__w3Hqp listItemContent_opened__1J0Zt"><div class="listItemContent_text__otIdg customListStyle html_html__3ZlsW"><p>Behalve het gewenste effect kan dit medicijn bijwerkingen geven.</p><p>Bijwerkingen treden niet bij iedereen op, maar alleen bij personen die daar gevoelig voor zijn.</p><p>De meeste bijwerkingen zijn in <strong>de eerste week </strong>het meest uitgesproken en nemen daarna af of verdwijnen zelfs. Ze gaan weer over als u met het medicijn stopt.</p><p>De belangrijkste bijwerkingen zijn de volgende.</p></div><div class="sideEffects_sideEffects__sczbd"><h3 class="sideEffects_freqTitle__2-wGN">Regelmatig (bij meer dan 30 op de 100 mensen)</h3><ul><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Maagdarmklachten</strong>, zoals misselijkheid, diarree, verstopping, winderigheid, krampen en verminderde eetlust.&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Dit gaat meestal binnen enkele dagen over, als u gewend bent geraakt aan het medicijn. U heeft minder last van deze bijwerkingen als u het medicijn met wat voedsel inneemt. Ook kunt u de arts vragen een dosering voor te schrijven waarmee u langzamer opbouwt.</p><p>Heeft u ooit een maag- of darmzweer gehad, of een andere ernstige maag- of darmaandoening, zoals een maag- of darmbloeding? U heeft dan meer kans op bijwerkingen op maag en darmen. Overleg met uw arts. Mogelijk schrijft uw arts behalve dit medicijn ook een maagbeschermend medicijn voor.</p></div></div></li></ul><h3 class="sideEffects_freqTitle__2-wGN">Soms (bij 10 tot 30 op de 100 mensen)</h3><ul><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Sufheid</strong>, slaperigheid en een verminderd reactievermogen.</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Dit is vooral lastig bij activiteiten waarbij uw oplettendheid erg nodig is, zoals autorijden, het beklimmen van een ladder of het bewaken van een proces op het werk. Onderneem geen risicovolle activiteiten de eerste week van het gebruik en ook niet als u daarna last heeft van deze bijwerkingen.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Slapeloosheid</strong></p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Heeft u hier last van, neem het medicijn dan altijd 's ochtends in.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Hoofdpijn </strong>en <strong>duizeligheid</strong>.</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Dit treedt vooral op aan het begin van de behandeling en wordt vanzelf minder.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Droge mond</strong></p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Hierdoor kunnen zich eerder gaatjes in uw gebit ontwikkelen. Poets en flos daarom extra goed als u merkt dat u last heeft van een droge mond. Laat eventueel de tandarts vaker controleren.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Zweten&nbsp;</strong></p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg uw arts als u hier veel last van heeft.</p></div></div></li></ul><h3 class="sideEffects_freqTitle__2-wGN">Zelden (bij 1 tot 10 op de 100 mensen)</h3><ul><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Seksuele stoornissen</strong>, zoals minder zin in vrijen, moeilijke erectie en een vertraagde zaadlozing.&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Neem contact op met uw arts als u hier last van heeft. Sommige mensen blijven last hebben van seksuele stoornissen, zelfs na het stoppen met het medicijn.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Gapen, trillen</strong>, <strong>oorsuizen </strong>en <strong>bibberen</strong>.</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg uw arts als u hier veel last van heeft.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Verwardheid</strong>, angst en nervositeit.</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Dit treedt vooral op aan het begin van de behandeling en wordt vanzelf minder.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Gewichtsverandering</strong></p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Vraag uw huisarts om een verwijzing naar een diëtist als de gewichtsverandering te groot en ongewenst is.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Huiduitslag </strong>en <strong>jeuk</strong>.&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg in dat geval uw arts. Zeer zelden komt dit door een allergische reactie op het medicijn en moet u met het medicijn stoppen.</p></div></div></li></ul><h3 class="sideEffects_freqTitle__2-wGN">Zeer zelden (bij minder dan 1 op de 100 mensen)</h3><ul><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Hartklachten </strong>zoals hartkloppingen, versnelde hartslag of juist een vertraagde hartslag.&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg uw arts als u hier veel last van heeft.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Hartritmestoornissen</strong>. U merkt dit soms alleen aan plotselinge duizelingen of als u even wegraakt.&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Vooral mensen met de hartritmestoornis <strong>verlengd QT-interval</strong> hebben hier meer kans op. Gebruik dit medicijn NIET als u deze hartritmestoornis heeft.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Wazig zien.</strong>&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg uw arts als u hier last van blijft houden.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Smaakstoornissen</strong></p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Neem contact op met uw arts bij de eerste verschijnselen.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Moeilijk kunnen stilzitten</strong> en <strong>rusteloosheid</strong>.</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Vooral mensen met de ziekte van Parkinson, kunnen hier meer last van krijgen. Raadpleeg uw arts als dit gebeurt, mogelijk moet de dosering van citalopram verlaagd worden.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p>Sneller en langer <strong>bloeden </strong>bij een verwonding. Dit merkt u ook aan blauwe plekken en bloedneuzen.&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg uw arts als u daar vaak last van heeft. Dit medicijn kan problemen geven bij bloedingen. Meld daarom uw arts dat u dit medicijn gebruikt wanneer u een operatie moet ondergaan.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Hallucinaties </strong>(dingen zien en horen die er niet werkelijk zijn).&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg dan uw arts.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Stemmingsverandering, toename van depressieve gedachten, vijandige gevoelens naar zichzelf </strong>of <strong>anderen.&nbsp;</strong></p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Dit kan zich uiten in agressie, zelfverwonding of gedachten aan zelfmoord. Neem contact met uw arts op als depressieve gevoelens juist toenemen of verergeren.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Haaruitval</strong></p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Raadpleeg uw arts als u hier veel last van heeft.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p>Kans op toename van het aantal aanvallen bij <strong>epilepsie</strong>.</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Mensen met epilepsie hebben kans op een toename van het aantal aanvallen. Overleg hierover met uw arts.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Leveraandoeningen</strong>. U kunt dit merken aan een gevoelige, opgezwollen buik of een gele verkleuring van het oogwit of van de huid.</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>&nbsp;Waarschuw bij deze klachten uw arts.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p><strong>Overgevoeligheid</strong> voor dit medicijn.&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>U kunt dit merken aan huiduitslag, jeuk en galbulten. Raadpleeg bij deze verschijnselen uw arts. In zeldzame gevallen ontstaat er bij allergie koorts, opgezwollen lippen, tong of gezicht of overgevoeligheid voor zonlicht. Stop dan meteen het gebruik en raadpleeg uw arts. Bij allergie mag u mag dit medicijn in de toekomst niet meer gebruiken. Geef aan de apotheker door dat u overgevoelig bent voor citalopram. Het apotheekteam kan er dan op letten dat u het medicijn niet opnieuw krijgt.</p></div></div></li><li class="sideEffectsItem_listItem__tVvim"><span class="sideEffectsItem_button__V-L1C" tabindex="0" role="button"><div class="sideEffectsItem_text__3ElNA customListStyle html_html__3ZlsW"><p>Dit middel kan de huid<strong> gevoeliger maken voor UV-licht</strong> (zon, zonnebank, UV-lamp).&nbsp;</p></div><div class="sideEffectsItem_chevron__3xZxb"><svg xmlns="http://www.w3.org/2000/svg" width="7.008" height="10.001" viewBox="0 0 7.008 10.001" style="min-width: 7px;"><path d="M0,8.786,4.246,5,0,1.215,1.4,0,7.008,5,1.4,10Z" fill="#21b19e"></path></svg></div></span><div class="sideEffectsItem_content__10s1c"><div class="sideEffectsItem_description__UQWDS customListStyle html_html__3ZlsW"><p>Blootstelling aan zonlicht, zelfs voor korte perioden, kan huiduitslag, jeuk, roodheid en andere verkleuring van de huid of ernstige verbranding door de zon geven. Een zonnebrandcrème met hoge beschermingsfactor beschermt hier slechts gedeeltelijk tegen. Blijf daarom uit direct zonlicht, met name tussen 10.00 en 15.00 uur en draag beschermende kleding, waaronder hoed en zonnebril.</p></div></div></li></ul><br><p class="sideEffects_text__1dmy9">Neem contact op met uw apotheker of arts als u te veel last heeft van deze of andere bijwerkingen waar u zich zorgen over maakt.</p><br><p class="sideEffects_text__1dmy9">Heeft u last van een bijwerking? Meld dit dan bij het bijwerkingencentrum lareb. Hier worden alle meldingen over bijwerkingen van medicijnen in Nederland verzameld.&nbsp;<a href="https://www.lareb.nl/meld-bijwerking/Meldformulier.aspx" target="_blank" rel="noreferrer">Ik wil een bijwerking melden</a></p></div><ul class="listItemContent_explanationFrequencies__1-jk7"><li class="listItemContent_container__25F5W listItemContent_subMenu__COLGV" data-section=""><button class="listItemContent_toggleButton__hmbW8 content-hidden"><div class="listItemContent_toggleIconWrapper__2MMIr"><div class="listItemContent_toggleIcon__WmYxX"><svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 35"><g fill="none" stroke="currentColor" stroke-width="2"><circle cx="17.5" cy="17.5" r="17.5" stroke="none"></circle><circle cx="17.5" cy="17.5" r="16.5" fill="none"></circle></g><rect width="17" height="2" transform="translate(9 17)" fill="currentColor"></rect><rect width="16.921" height="2.168" transform="translate(16.416 26.308) rotate(-90)" fill="currentColor"></rect></svg></div><h2>Uitleg frequenties</h2></div></button><div class="listItemContent_content__w3Hqp"><div class="listItemContent_text__otIdg customListStyle html_html__3ZlsW"><strong>Regelmatig</strong>: bij meer dan 30 op de 100 mensen <br><strong>Soms</strong>: bij 10 tot 30 op de 100 mensen <br><strong>Zelden</strong>: bij 1 tot 10 op de 100 mensen <br><strong>Zeer zelden</strong>: bij minder dan 1 op de 100 mensen</div><ul></ul></div></li></ul><ul></ul></div></li>