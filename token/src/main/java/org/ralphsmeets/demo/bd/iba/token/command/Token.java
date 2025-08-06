package org.ralphsmeets.demo.bd.iba.token.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.ralphsmeets.demo.bd.iba.coreapi.token.*;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Token {
    @AggregateIdentifier
    private String tokenId;
    private String automatischeIncassoAfspraakId;
    private String token;

    public Token() {
    }


    @CommandHandler
    public MaakTokenResponse handle(MaakTokenCommand command) throws NoSuchAlgorithmException {
        String aiaId = UUID.randomUUID().toString();
        String token = Tokens.maak(command.tokenId(), command.);
        apply(new TokenGemaaktEvent(command.tokenId(), token, aiaId));
        return new MaakTokenResponse(command.tokenId(), token, aiaId);
    }

    @CommandHandler
    public void handle(VerwijderTokenCommand command) {
    }

    @EventSourcingHandler
    protected void handle(TokenGemaaktEvent event) {
        this.tokenId = event.tokenId();
        this.token = event.token();
        this.automatischeIncassoAfspraakId = event.aiaId();
    }
}
