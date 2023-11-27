import org.example.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TilaustenKasittelyFakeTest {
    @Test
    public void testaaKäsittelijäWithFakeHinnoittelija() {
// Arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 30.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        IHinnoittelija hinnoittelija = new FakeHinnoittelija(alennus);
// Act
        TilaustenKasittely käsittelijä = new TilaustenKasittely();
        käsittelijä.setHinnoittelija(hinnoittelija);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
// Assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
    }
}
