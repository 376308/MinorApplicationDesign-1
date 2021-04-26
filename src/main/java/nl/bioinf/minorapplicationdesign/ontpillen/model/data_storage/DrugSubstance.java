package nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Larissa Bouwknegt, Jonathan Klimp, Naomi Hindriks
 */
public class DrugSubstance extends Drug {
    private List<String> brandNames = new ArrayList<>();
    private List<String> description;
    private List<String> sideEffects;
    private List<UseIndication> useIndications = new ArrayList<>();
    private List<StopIndication>  stopIndications;
    private List<String> interactions;

    public DrugSubstance(String name) {
        super(name);
    }

    public void setDescription(List<String> Description){
        this.description = Description;
    }

    public void addBrandName(String brandName){
        this.brandNames.add(brandName);
    }

    public void setSideEffects(List<String> sideEffects){
        this.sideEffects = sideEffects;
    }

    public void addUseIndication(UseIndication useIndication){
        this.useIndications.add(useIndication);
    }

    public void setStopIndications(List<StopIndication> stopIndications){
        this.stopIndications = stopIndications;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBrandNames() {
        return Collections.unmodifiableList(brandNames);
    }

    public List<String> getDescription() {
        return Collections.unmodifiableList(description);
    }

    public List<String> getSideEffects() {
        return Collections.unmodifiableList(sideEffects);
    }

    public List<UseIndication> getUseIndications() {
        return Collections.unmodifiableList(useIndications);
    }

    public List<StopIndication> getStopIndications() {
        return Collections.unmodifiableList(stopIndications);
    }

    public List<String> getInteractions() {
        return Collections.unmodifiableList(interactions);
    }

    public void setInteractions(List<String> interactions) {
        this.interactions = interactions;
    }
}