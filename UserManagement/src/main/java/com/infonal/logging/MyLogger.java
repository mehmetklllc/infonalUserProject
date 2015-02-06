/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infonal.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkilic
 */
public class MyLogger {

    public static void myLogging(Level level, String messsage, Class type) {
        Logger log = Logger.getLogger(
                type.getName());
        log.log(level, messsage);

    }
}
