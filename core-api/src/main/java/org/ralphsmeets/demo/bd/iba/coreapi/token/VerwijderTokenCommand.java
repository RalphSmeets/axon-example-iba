package org.ralphsmeets.demo.bd.iba.coreapi.token;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record VerwijderTokenCommand(@TargetAggregateIdentifier String tokenId) {
}
