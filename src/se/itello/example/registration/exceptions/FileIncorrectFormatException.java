/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.example.registration.exceptions;

/**
 *
 * @author Dejan
 */
public class FileIncorrectFormatException extends Exception {

    /**
     * Creates a new instance of <code>FileIncorrectFormatException</code>
     * without detail message.
     */
    public FileIncorrectFormatException() {
    }

    /**
     * Constructs an instance of <code>FileIncorrectFormatException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public FileIncorrectFormatException(String msg) {
        super(msg);
    }
}
