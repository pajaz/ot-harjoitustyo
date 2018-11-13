package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void uusiKassapaateRahatOk() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void uusiKassaEiMyytyja() {
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0 && kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void kassanRahamaaraLisaantyySyodessaMaukkaastiKateisella() {
        kassa.syoMaukkaasti(400);
        assertTrue(kassa.kassassaRahaa() == 100400);
    }

    @Test
    public void vaihtoRahatOkSyodessaMaukkaasti() {
        assertTrue(kassa.syoMaukkaasti(500) == 100);
    }

    @Test
    public void kassanRahamaaraLisaantyySyodessaEdullisestiKateisella() {
        kassa.syoEdullisesti(240);
        assertTrue(kassa.kassassaRahaa() == 100240);
    }

    @Test
    public void vaihtoRahatOkSyodessaEdullisesti() {
        assertTrue(kassa.syoEdullisesti(300) == 60);
    }

    @Test
    public void kateismyyntiNostaaEdullistenMyyntia() {
        kassa.syoEdullisesti(240);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void kateismyyntiNostaaMaukkaidenMyyntia() {
        kassa.syoMaukkaasti(400);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void josRahatEiRiitaNePalautuvat() {
        assertTrue(kassa.syoEdullisesti(100) == 100 && kassa.syoMaukkaasti(100) == 100);
    }

    @Test
    public void josEiRahaaEdulliseenLounaidenMaaraEiKasva() {
        kassa.syoEdullisesti(100);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void josEiRahaaMaukkaidenLounaidenMaaraEiKasva() {
        kassa.syoMaukkaasti(100);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void josKortillaRahaaEdulliseenVeloituisOk() {
        assertTrue(kassa.syoEdullisesti(kortti) == true && kortti.saldo() == 760);
    }

    @Test
    public void josKortillaRahaaMaukkaaseenVeloitusOk() {
        assertTrue(kassa.syoMaukkaasti(kortti) == true && kortti.saldo() == 600);
    }

    @Test
    public void josKortillaRahaaEdulliseenMyyntiKasvaa() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void josKortillaRahaaMaukkaaseenMyyntiKasvaa() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void josKortillaEiRahaaEdulliseenMikaanEiMuutu() {
        kortti.otaRahaa(800);
        assertTrue(kassa.syoEdullisesti(kortti) == false && kortti.saldo() == 200 && kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void josKortillaEiRahaaMaukkaaseenMikaanEiMuutu() {
        kortti.otaRahaa(800);
        assertTrue(kassa.syoMaukkaasti(kortti) == false && kortti.saldo() == 200 && kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void kortinJaKassanSaldotMuuttuvatLadatessaKortilleRahaa() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertTrue(kortti.saldo() == 2000 && kassa.kassassaRahaa() == 101000);
    }
    
    @Test
    public void NegatiivisenSummanLatausEiMuutaSaldoja() {
       kassa.lataaRahaaKortille(kortti, -100);
       assertTrue(kortti.saldo() == 1000 && kassa.kassassaRahaa() == 100000);
    }

}