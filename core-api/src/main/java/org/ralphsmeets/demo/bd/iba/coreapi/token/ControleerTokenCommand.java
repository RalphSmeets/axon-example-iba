package org.ralphsmeets.demo.bd.iba.coreapi.token;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ControleerTokenCommand(@TargetAggregateIdentifier String tokenId, String bsn, String rn, String middel, String token) {
}
