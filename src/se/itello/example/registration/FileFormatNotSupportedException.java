/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.example.registration;

/**
 *
 * @author Dejan
 */
public class FileFormatNotSupportedException extends Exception {

    /**
     * Creates a new instance of <code>FileTypeNotSupportedException</code>
     * without detail message.
     */
    public FileFormatNotSupportedException() {
    }

    /**
     * Constructs an instance of <code>FileTypeNotSupportedException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public FileFormatNotSupportedException(String msg) {
        super(msg);
    }
}
