package org.ralphsmeets.demo.bd.iba.innen.ui;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.ralphsmeets.demo.bd.iba.coreapi.innen.VoerAutomatischeIncassoAfspraakOpRequest;
import org.ralphsmeets.demo.bd.iba.coreapi.innen.VoerNieuwAutomatischeIncassoAfspraakOpCommand;
import org.ralphsmeets.demo.bd.iba.innen.command.AutomatischeIncassoAfspraak;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
public class InnenController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public InnenController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/automatische/incasso/afspraken")
    public CompletableFuture<String> voerNieuweAutomatischeIncassoAfspraakOp(@RequestBody VoerAutomatischeIncassoAfspraakOpRequest voerAutomatischeIncassoAfspraakOpRequest) {

        VoerNieuwAutomatischeIncassoAfspraakOpCommand command = new VoerNieuwAutomatischeIncassoAfspraakOpCommand(voerAutomatischeIncassoAfspraakOpRequest.automatischeIncassoId(), voerAutomatischeIncassoAfspraakOpRequest.burgerServiceNummer(), voerAutomatischeIncassoAfspraakOpRequest.rekeningNummer(), voerAutomatischeIncassoAfspraakOpRequest.token());
        CompletableFuture<String> commandResult = commandGateway.send(command);

        return commandResult;

    }

    @GetMapping("/automatische/incasso/afspraken")
    public CompletableFuture<List<AutomatischeIncassoAfspraak>> getAIAvoorBsn() {

    }
}
