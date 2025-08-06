package org.ralphsmeets.demo.bd.iba.coreapi.token;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.ralphsmeets.demo.bd.iba.coreapi.innen.AutomatischeIncassoAfspraak;

public record MaakTokenCommand(@TargetAggregateIdentifier String tokenId, AutomatischeIncassoAfspraak afspraak) {
}
