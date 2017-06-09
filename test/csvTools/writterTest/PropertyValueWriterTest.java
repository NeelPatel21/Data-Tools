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
import dataTools.csvTools.writter.CsvWriter;
import dataTools.csvTools.writter.CsvWriterFactory;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Neel Patel
 */
public class PropertyValueWriterTest {
    public static void main(String... arg){
        Scanner sc=new Scanner(System.in);
        String s,s2;
        Map<String,String> m;
        CsvWriter cw=CsvWriterFactory.getCsvWriter(Paths.get("abc.txt"), CsvType.PROPERTY_VALUE);
        for(;;){
            m=new TreeMap<>();
            in:for(;;){
                s=sc.next("[\\S]+");
                if(s.equals("flush")){
                    cw.flush();
                    continue;
                }else if(s.equals(";"))
                    break in;
                else if(s.equals("0"))
                    return;
                s2=sc.next("[\\S]+");
                m.put(s, s2);
            }
            System.out.println(cw.writeMap(m));
        } 
    }
}
