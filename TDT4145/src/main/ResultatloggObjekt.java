package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResultatloggObjekt {
	
	public ResultatloggObjekt() {}

    public ResultatloggObjekt(int treningId, String ovelse, String dato, int kilo, int sett, String notat) {
    		setTreningId(treningId);
    		setOvelse(ovelse);
        setDato(dato);
        setKilo(kilo);
        setSett(sett);
        setNotat(notat);
    }

    private final IntegerProperty treningId = new SimpleIntegerProperty(this, "treningId");
    private final StringProperty ovelse = new SimpleStringProperty(this, "ovelse");
	private final StringProperty dato = new SimpleStringProperty(this, "dato");
    private final IntegerProperty kilo = new SimpleIntegerProperty(this, "kilo");
    private final IntegerProperty sett = new SimpleIntegerProperty(this, "sett");
    private final StringProperty notat = new SimpleStringProperty(this, "notat");

    
    public IntegerProperty treningIdProperty() {
        return treningId;
    }
    public final Integer getTreningId() {
        return treningIdProperty().get();
    }
    public final void setTreningId(int treningId) {
    		treningIdProperty().set(treningId);
    }
    
    
    public StringProperty ovelseProperty() {
        return ovelse;
    }
    public final String getOvelse() {
        return ovelseProperty().get();
    }
    public final void setOvelse(String ovelse) {
    		ovelseProperty().set(ovelse);
    }
    
	
   public StringProperty datoProperty() {
        return dato ;
    }
    public final String getDato() {
        return datoProperty().get();
    }
    public final void setDato(String dato) {
        datoProperty().set(dato);
    }

    
    public IntegerProperty kiloProperty() {
        return kilo;
    }
    public final Integer getKilo() {
        return kiloProperty().get();
    }
    public final void setKilo(int kilo) {
        kiloProperty().set(kilo);
    }
    
    
    public IntegerProperty settProperty() {
        return sett;
    } 
    public final Integer getSett() {
        return settProperty().get();
    }
    public final void setSett(int sett) {
    		settProperty().set(sett);
    }

    
    public StringProperty notatProperty() {
        return notat;
    }
    public final String getNotat() {
        return notatProperty().get();
    }
    public final void setNotat(String notat) {
        notatProperty().set(notat);
    }
    
}
