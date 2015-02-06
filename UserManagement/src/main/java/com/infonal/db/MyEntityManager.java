/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infonal.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mkilic
 */
public class MyEntityManager {

    private static EntityManagerFactory entityManagerFactory;

    synchronized public static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName) {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence
                    .createEntityManagerFactory(persistenceUnitName);
        }
        return entityManagerFactory;
    }

    public static EntityManager createEntityManager(String persistenceUnitName) {
        return getEntityManagerFactory(persistenceUnitName).createEntityManager();
    }
}
