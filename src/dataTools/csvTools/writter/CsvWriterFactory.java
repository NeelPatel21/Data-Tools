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

import dataTools.csvTools.CsvType;
import java.nio.file.Path;

/**
 *
 * @author Neel Patel
 */
public class CsvWriterFactory {
    
    private CsvWriterFactory(){//private constructor for Factory class    
    }
    
    /**
     * this method returns the Object of CsvWriter of specified formate.
     * new file & required directories created if path is not exist.
     * if file is exist then the data will be appended at the end of file.
     * @param file path of CSV file.
     * @param ct CSV type.
     * @return 
     */
    public static CsvWriter getCsvWriter(Path file,CsvType ct){
        if(ct==CsvType.PROPERTY_VALUE){
            PropertyValueWriter cw=new PropertyValueWriter(file);
            cw.init();
            return cw;
        }
        ValueOnlyWriter cw=new ValueOnlyWriter(file);
        cw.init();
        return cw;
    }
    
}
