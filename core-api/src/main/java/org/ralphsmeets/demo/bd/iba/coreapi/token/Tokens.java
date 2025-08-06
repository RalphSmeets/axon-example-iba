package org.ralphsmeets.demo.bd.iba.coreapi.token;

import org.ralphsmeets.demo.bd.iba.coreapi.innen.AutomatischeIncassoAfspraak;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tokens {
    public static String maak(String tokenId, AutomatischeIncassoAfspraak afspraak) throws NoSuchAlgorithmException {
        String aia = tokenId + afspraak.bsn() + afspraak.rekeningNummer() + afspraak.middel();
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(aia.getBytes()).toString();
    }
}
