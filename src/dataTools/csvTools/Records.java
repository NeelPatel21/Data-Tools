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
package dataTools.csvTools;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Neel Patel
 */
public class Records {
    private Records(){}
    
    public static String makeRecord(Map<String,String> data,String sep){
        StringBuilder sb=new StringBuilder();
        data.forEach((x,y)->sb.append(x).append("=").append(y).append(sep));
        sb.delete(sb.lastIndexOf(sep),sb.length());
        return sb.toString();
    }
    
    public static String makeRecord(Map<String,String> data){
        return makeRecord(data,",");
    }
    
    public static String makeRecord(List<String> data,String sep){
        StringBuilder sb=new StringBuilder();
        data.forEach(x->sb.append(x).append(sep));
        sb.delete(sb.lastIndexOf(sep),sb.length());
        return sb.toString();
    }
    
    public static String makeRecord(List<String> data){
        return makeRecord(data,",");
    }
    
    public static <P,V> V getProperty(String record,P property,String sep){
        if(property instanceof Integer){
            int i=(Integer)property;
            String ar[]=record.split(sep);
            return (V)ar[i];
        }else if(property instanceof String){
            try{
                String p=(String)property;
                String[] ar=Arrays.stream(record.split(","))
                                .toArray(String[]::new);
                for(String s:ar){
                    String[] a=Arrays.stream(s.split("=",2))
                                    .toArray(String[]::new);
                    if(a[0].equals(p))
                        return (V)a[1];
                }
            }catch(Exception e){
            }
            return null;
        }else{
            throw new IllegalArgumentException("invalid  property type");
        }
    }
    
    public static <P,V> V getProperty(String record,P property){
        return (V)getProperty(record,property,",");
    }
    
    public static <P,V> Map<P,V> parseRecord(String record,String sep){
        return null;
    }
}
