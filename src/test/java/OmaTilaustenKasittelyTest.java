import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OmaTilaustenKasittelyTest {
    @Mock
    IHinnoittelija hinnoittelijaMock;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testaaKäsittelijäWithMockitoHinnoittelijaHintYliSata() {
// Arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 300.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
// Record
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
                .thenReturn(alennus);
        doNothing().when(hinnoittelijaMock).setAlennusProsentti(asiakas, alennus + 5);


// Act
        TilaustenKasittely käsittelijä = new TilaustenKasittely();
        käsittelijä.setHinnoittelija(hinnoittelijaMock);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
// Assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelijaMock,times(2)).getAlennusProsentti(asiakas, tuote);
        verify(hinnoittelijaMock).setAlennusProsentti(asiakas, alennus + 5);
    }

    @Test
    public void testaaKäsittelijäWithMockitoHinnoittelijaHintAlleSata() {
// Arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 30.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
// Record
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
                .thenReturn(alennus);


// Act
        TilaustenKasittely käsittelijä = new TilaustenKasittely();
        käsittelijä.setHinnoittelija(hinnoittelijaMock);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
// Assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelijaMock,times(2)).getAlennusProsentti(asiakas, tuote);
        verify(hinnoittelijaMock, times(0)).setAlennusProsentti(asiakas, alennus + 5);
    }
}
