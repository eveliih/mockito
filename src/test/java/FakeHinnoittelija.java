import org.example.Asiakas;
import org.example.IHinnoittelija;
import org.example.Tuote;

public class FakeHinnoittelija implements IHinnoittelija {
    private float alennus;

    public FakeHinnoittelija(float alennus) {
        this.alennus = alennus;
    }

    public float getAlennusProsentti(Asiakas asiakas, Tuote tuote) {
        return alennus;
    }

    @Override
    public void aloita() {

    }

    @Override
    public void setAlennusProsentti(Asiakas a, float uusiProsentti) {


    }

    @Override
    public void lopeta() {

    }
}