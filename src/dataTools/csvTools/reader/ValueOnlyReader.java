/*
 * The MIT License
 *
 * Copyright 2017 Neel Patel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package dataTools.csvTools.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author Neel Patel
 */
public class ValueOnlyReader implements CsvReader{
    private Path file; //Path object points to a file where the data will written.
    private String sep=","; //separator.
    
    /**
     *
     * @param file Path object of file in which data will be stored.
     */
    ValueOnlyReader(Path file){
        this.file=file;
    }
    
    @Override
    public String getSeparator() {
        return sep;
    }

    @Override
    public boolean setSeparator(String sep) {
        this.sep=sep;
        return true;
    }

    @Override
    public Stream<String> RawRecordStream() {
        try {
            return Files.lines(file);
        } catch(IOException ex) {
            //Logger.getLogger(ValueOnlyReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Stream<Map<String, String>> stream() {
        return null;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
