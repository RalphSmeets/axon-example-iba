package org.ralphsmeets.demo.bd.iba.token.ui;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.ralphsmeets.demo.bd.iba.coreapi.token.MaakTokenCommand;
import org.ralphsmeets.demo.bd.iba.coreapi.token.MaakTokenRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
public class TokenController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public TokenController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/tokens")
    public CompletableFuture<String> maakToken(@RequestBody MaakTokenRequest maakTokenRequest) {
        String tokenId = UUID.randomUUID().toString();
        MaakTokenCommand maakTokenCommand = new MaakTokenCommand(tokenId, maakTokenRequest.burgerServiceNummer(), maakTokenRequest.rekeningNummer());

        CompletableFuture<String> commandResult = commandGateway.send(maakTokenCommand);
        return commandResult;
    }
}
