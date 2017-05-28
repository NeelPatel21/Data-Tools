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
package dataTools.csvTools.writter;

import java.util.Map;

/**
 *
 * @author Neel Patel
 */
public interface CsvWriter extends AutoCloseable{
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
     * implementation of this method should write the String as it is.<br>
     * Note:- It is strongly recommended to use {@link writeMap} method.
     * as the implementation of this method does not modify the record before
       writing, problem may be occur while reading the data.
     * @param record String record
     * @return true if successfully written.
     */
    boolean writeRawRecord(String record);
    
    /**
     * implementation of this method should write the record create by
       combining the values using {@code separator}.
     * {@code data} object will contain property(column name) to value mapping.
     * @param data Map Object
     * @return true if successfully written.
     */
    boolean writeMap(Map<String,String> data);
    
    /**
     * implementation of this method should write the cached data immediately.
     */
    void flush();
}
