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
package csvTools.writterTest;

import dataTools.csvTools.CsvType;
import dataTools.csvTools.reader.CsvReader;
import dataTools.csvTools.reader.CsvReaderFactory;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Neel Patel
 */
public class ValueOnlyReaderTest {
    public static void main(String... arg){
        Scanner sc=new Scanner(System.in);
        String s,s2;
        CsvReader<?,?> cw=CsvReaderFactory.getCsvReader(Paths.get("abc.txt"), CsvType.VALUE_ONLY);
        for(Map<?,?> m:cw){
            System.out.println(m);
        }
    }
}
