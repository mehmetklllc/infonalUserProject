/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infonal.db;

import java.util.List;

/**
 *
 * @author mkilic
 */
public interface IBaseRepository<E> {

    public E persist(E entity);

    public E merge(E entity);

    public String remove(String entityId);

    public long find(long entityId);

    public E getSingleEntity(String entityId, String entityName, String where);

    public List<E> getAllResultlist(String entityName);
}
