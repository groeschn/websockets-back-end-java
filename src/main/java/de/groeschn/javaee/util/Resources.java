package de.groeschn.javaee.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Resources {

    @PersistenceContext
    private EntityManager em;

    private Jsonb jsonb;

    @Produces
    public EntityManager getEm() {
        return em;
    }

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    public Jsonb produceJsonb() {
        if (jsonb == null) {
            jsonb = JsonbBuilder.create();
        }
        return jsonb;
    }
}
