package main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TreningsOktObjekt {

	public TreningsOktObjekt() {}
	
    public TreningsOktObjekt(String treningID, String dato, String tidspunkt, String varighet, String personligForm, String prestasjon, String notat) {
    		setTreningID(treningID);
        setdato(dato);
        setTidspunkt(tidspunkt);
        setVarighet(varighet);
        setPersonligForm(personligForm);
        setPrestasjon(prestasjon);        
        setNotat(notat);

    }

    private final StringProperty treningID = new SimpleStringProperty(this, "treningID");
	private final StringProperty dato = new SimpleStringProperty(this, "dato");
    private final StringProperty tidspunkt = new SimpleStringProperty(this, "tidspunkt");
    private final StringProperty varighet = new SimpleStringProperty(this, "varighet");
    private final StringProperty personligForm = new SimpleStringProperty(this, "personligForm");
    private final StringProperty prestasjon = new SimpleStringProperty(this, "prestasjon");
    private final StringProperty notat = new SimpleStringProperty(this, "notat");

    
    public StringProperty treningIDProperty() {
        return treningID ;
    }
    public final String getTreningID() {
        return treningIDProperty().get();
    }
    public final void setTreningID(String treningID) {
        treningIDProperty().set(treningID);
    }
    
	
    public StringProperty datoProperty() {
        return dato ;
    }
    public final String getDato() {
        return datoProperty().get();
    }
    public final void setdato(String dato) {
        datoProperty().set(dato);
    }

    
    public StringProperty tidspunktProperty() {
        return tidspunkt ;
    }
    public final String getTidspunkt() {
        return tidspunktProperty().get();
    }
    public final void setTidspunkt(String tidspunkt) {
        tidspunktProperty().set(tidspunkt);
    }
    
    
    public StringProperty varighetProperty() {
        return varighet ;
    }
    public final String getVarighet() {
        return varighetProperty().get();
    }
    public final void setVarighet(String varighet2) {
    		varighetProperty().set(varighet2);
    }

    
    public StringProperty personligFormProperty() {
        return personligForm ;
    }
    public final String getPersonligForm() {
        return personligFormProperty().get();
    }
    public final void setPersonligForm(String personligForm) {
        personligFormProperty().set(personligForm);
    }
    
    
    public StringProperty prestasjonProperty() {
        return prestasjon ;
    }
    public final String getPrestasjon() {
        return prestasjonProperty().get();
    }
    public final void setPrestasjon(String prestasjon) {
    		prestasjonProperty().set(prestasjon);
    }   
    
    
    public StringProperty notatProperty() {
        return notat ;
    }
    public final String getNotat() {
        return notatProperty().get();
    }
    public final void setNotat(String notat) {
    		notatProperty().set(notat);
    }
}
