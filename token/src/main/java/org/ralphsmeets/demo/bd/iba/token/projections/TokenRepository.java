package org.ralphsmeets.demo.bd.iba.token.projections;

import org.ralphsmeets.demo.bd.iba.coreapi.token.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
    Token findByUUID(String uuid);
}
