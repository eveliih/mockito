package org.example;

public interface IHinnoittelija {
    public abstract float getAlennusProsentti(Asiakas asiakas, Tuote tuote);
    public void aloita();
    public void setAlennusProsentti(Asiakas a, float uusiProsentti);
    public void lopeta();
}
