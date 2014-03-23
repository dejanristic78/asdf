/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.example.datafileregistration.exceptions;

/**
 *
 * @author Dejan
 */
public class FileCouldNotBeReadException extends Exception {

    /**
     * Creates a new instance of <code>FileCouldNotBeReadException</code>
     * without detail message.
     */
    public FileCouldNotBeReadException() {
    }

    /**
     * Constructs an instance of <code>FileCouldNotBeReadException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public FileCouldNotBeReadException(String msg) {
        super(msg);
    }
}
