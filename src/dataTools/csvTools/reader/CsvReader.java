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

import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author Neel Patel
 */
public interface CsvReader extends Iterable{
    /**
     * implementation of this method should return the
       separator used to separate the values in record.
     * @return separator as String Object.
     */
    String getSeparator();
    
    /**
     * implementation of this method should update separator
       with the new separator value.
     * new records will be constructed using updated separator.<br>
     * old records are remain unaffected.
     * @param sep separator.
     * @return true if separator updated successfully.
     */
    boolean setSeparator(String sep);
    
    /**
     * implementation of this method should return stream over
       the csv file.
     * @return stream of strings read from the csv file.
     */
    Stream<String> RawRecordStream();
    
    /**
     * implementation of this method should return all the
       records as Stream over the csv file.
     * this method should return stream of Map objects each represents
       single record as property - value mapping.
     * @return stream of Map .
     */
    Stream<Map<String,String>> stream();
    
}
