package org.ralphsmeets.demo.bd.iba.token.projections;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.ralphsmeets.demo.bd.iba.coreapi.token.Token;
import org.ralphsmeets.demo.bd.iba.coreapi.token.TokenGemaaktEvent;
import org.springframework.stereotype.Component;

@Component
public class TokenProjection {
    private final TokenRepository tokenRepository;
    private final QueryUpdateEmitter updateEmitter;

    public TokenProjection(TokenRepository tokenRepository, QueryUpdateEmitter updateEmitter) {
        this.tokenRepository = tokenRepository;
        this.updateEmitter = updateEmitter;
    }

    @QueryHandler(queryName = "getTokenByUUID")
    public Token getTokenByUUID(String uuid) {
        return tokenRepository.findByUUID(uuid);
    }

    @EventHandler
    public void handle(TokenGemaaktEvent event){
        tokenRepository.save( new Token(event.tokenId(), event.token() ));
    }
}
