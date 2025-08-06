package org.ralphsmeets.demo.bd.iba.innen.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.ralphsmeets.demo.bd.iba.coreapi.innen.NieuwAutomatischeIncassoAfspraakOpgevoerdEvent;
import org.ralphsmeets.demo.bd.iba.coreapi.innen.VoerNieuwAutomatischeIncassoAfspraakOpCommand;
import org.ralphsmeets.demo.bd.iba.coreapi.token.Tokens;

import java.security.NoSuchAlgorithmException;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AutomatischeIncassoAfspraak {
    @AggregateIdentifier
    private String automatischeIncassoAfspraakId;

    public AutomatischeIncassoAfspraak() {
    }

    @CommandHandler
    public AutomatischeIncassoAfspraak(VoerNieuwAutomatischeIncassoAfspraakOpCommand command) throws NoSuchAlgorithmException {
        String token = Tokens.maak(command.id(), command.afspraak());
        if (token.contentEquals(command.token())) {
            apply(new NieuwAutomatischeIncassoAfspraakOpgevoerdEvent(command.id(), command.afspraak()));
        }
    }

    @EventSourcingHandler
    protected void handle(NieuwAutomatischeIncassoAfspraakOpgevoerdEvent event) {
        this.automatischeIncassoAfspraakId = event.afspraakId();
    }
}
