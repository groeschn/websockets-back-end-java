package de.groeschn.javaee.util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Resources {

    @PersistenceContext
    private EntityManager em;

    private Jsonb jsonb;

    @PostConstruct
    private void init() {
        jsonb = JsonbBuilder.newBuilder()
                .withConfig(new JsonbConfig()
                        .withFormatting(true)).build();
    }

    @Produces
    public EntityManager getEm() {
        return em;
    }

    @Produces
    public Jsonb produceJsonb() {
        return jsonb;
    }
}
