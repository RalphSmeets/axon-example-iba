package org.ralphsmeets.demo.bd.iba.coreapi.token;

public record MaakTokenRequest(String burgerServiceNummer, String rekeningNummer, String middel) {
}
