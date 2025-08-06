package org.ralphsmeets.demo.bd.iba.coreapi.innen;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record VoerNieuwAutomatischeIncassoAfspraakOpCommand(
        @TargetAggregateIdentifier String id, AutomatischeIncassoAfspraak afspraak, String token) {
}

