package org.ralphsmeets.demo.bd.iba.innen.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.ralphsmeets.demo.bd.iba.coreapi.innen.NieuwAutomatischeIncassoAfspraakOpgevoerdEvent;
import org.ralphsmeets.demo.bd.iba.coreapi.token.TokenGemaaktEvent;
import org.ralphsmeets.demo.bd.iba.coreapi.token.VerwijderTokenCommand;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

@Saga
public class TokenSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient DeadlineManager deadlineManager;

    private String tokenId;

    public TokenSaga() {
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "tokenId")
    public void on(TokenGemaaktEvent event) {
        this.tokenId = event.tokenId();
        SagaLifecycle.associateWith("automatischeIncassoAfspraakId", event.tokenId());
        deadlineManager.schedule(Duration.ofSeconds(30), "verwijderToken", event.tokenId());
    }
    @EndSaga

    @SagaEventHandler(associationProperty = "automatischeIncassoAfspraakId")
    public void on(NieuwAutomatischeIncassoAfspraakOpgevoerdEvent event) {
        commandGateway.send(new VerwijderTokenCommand(tokenId));
        SagaLifecycle.end();
    }

    @DeadlineHandler(deadlineName = "verwijderToken")
    public void verwijderToken(String tokenId) {
        commandGateway.send(new VerwijderTokenCommand(tokenId));
    }
}
