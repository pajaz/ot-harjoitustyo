package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void uusiKorttiSaldoOk() {
                
        assertEquals("saldo: 0.10", kortti.toString());
    } 
    
    @Test
    public void rahanLatausLisaaSaldoa() {
        kortti.lataaRahaa(1);
         assertEquals("saldo: 0.11", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeKunRahaa() {
        kortti.otaRahaa(1);
         assertEquals("saldo: 0.9", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuKunEiRahaa() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoMuutosPalauteEiRahaa() {
        assertFalse(kortti.otaRahaa(11));
    }
    
    @Test
    public void saldoMuutosPalauteRahaaOn() {
        assertTrue(kortti.otaRahaa(9));
    }
    
    
}
