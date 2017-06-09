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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is factory class, provides functionality to process the CSV records.
 * it support VALUE_ONLY and PROPERTY_VALUE CSV formates.
 * @author Neel Patel
 */
public class Records {
    private Records(){}
    
    /**
     * this method make the record using {@code data) and {@code sep}.
     * this method return record as String in the formate
       {@code key=value[,key=value]...}.
     * @param data data as Map Object.
     * @param sep separator as String.
     * @return record of type PROPERTY_VALUE type as String.
     */
    public static String makeRecord(Map<String,String> data,String sep){
        StringBuilder sb=new StringBuilder();
        data.forEach((x,y)->sb.append(x).append("=").append(y).append(sep));
        sb.delete(sb.lastIndexOf(sep),sb.length());
        return sb.toString();
    }
    
    /**
     * this method make the record using {@code data}.
     * this method return record as String in the formate
       {@code key=value[,key=value]...}.
     * it use {@code ","} as separator.
     * it returns same result as method call {@code makeRecord(data,",")}.
     * @param data data as Map Object.
     * @return record of type PROPERTY_VALUE type as String.
     */
    public static String makeRecord(Map<String,String> data){
        return makeRecord(data,",");
    }
    
    /**
     * this method make the record using {@code data) and {@code sep}.
     * this method return record as String in the formate.
       {@code key=value[,key=value]...}.
     * order of the values in the record is same as order in the List.
     * @param data data as List Object.
     * @param sep separator as String.
     * @return record of type VALUE_ONLY type as String.
     */
    public static String makeRecord(List<String> data,String sep){
        StringBuilder sb=new StringBuilder();
        data.forEach(x->sb.append(x).append(sep));
        sb.delete(sb.lastIndexOf(sep),sb.length());
        return sb.toString();
    }
    
    /**
     * this method make the record using {@code data) and {@code sep}.
     * this method return record as String in the formate.
       {@code key=value[,key=value]...}.
     * order of the values in the record is same as order in the List.
     * it use {@code ","} as separator.
     * it returns same result as method call {@code makeRecord(data,",")}.
     * @param data data as List Object.
     * @return record of type VALUE_ONLY type as String.
     */
    public static String makeRecord(List<String> data){
        return makeRecord(data,",");
    }
    
    /**
     * returns the value of the property.
     * it returns {@code null} if the value is not available for the property.
     * in VALUE_ONLY CSV type, the property is the index of the value in record
       starts with '0'.
     * @param <P> type of property. in VALUE_ONLY type, it is {@code Integer} and
       in PROPERTY_VALUE CVS type, it is {@code String}.
     * @param <V> type of property. it should be {@code String}.
     * @param record row record as String.
     * @param property property
     * @param sep separator as string.
     * @return value if available. otherwise {@code null}.
     * @throws IllegalArgumentException if record formate is invalid.
     */
    public static <P,V> V getProperty(String record,P property,String sep){
        if(property instanceof Integer){
            int i=(Integer)property;
            String ar[]=record.split(sep);
            return (V)ar[i];
        }else if(property instanceof String){
            try{
                String p=(String)property;
                String[] ar=Arrays.stream(record.split(sep))
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
    
    /**
     * returns the value of the property.
     * it returns {@code null} if the value is not available for the property.
     * in VALUE_ONLY CSV type, the property is the index of the value in record
       starts with '0'.
     * it use {@code ","} as separator.
     * it returns same result as method call {@code getProperty(record,property,",")}.
     * @param <P> type of property. in VALUE_ONLY type, it is {@code Integer} and
       in PROPERTY_VALUE CVS type, it is {@code String}.
     * @param <V> type of property. it should be {@code String}.
     * @param record row record as String.
     * @param property property
     * @return value if available. otherwise {@code null}.
     * @throws IllegalArgumentException if record formate is invalid.
     */
    public static <P,V> V getProperty(String record,P property){
        return (V)getProperty(record,property,",");
    }
    
    /**
     * this method returns record as Map object.
     * if the type is VALUE_ONLY then this method returns the object
       of type {@code Map<Integer,String>} in which keys represents indexes.
     * if the type is PROPERTY_VALUE then this method returns the object
       of type {@code Map<Integer,String>}.
     * @param <P> type of property. Integer for VALUE_ONLY and String for
       PROPERTY_VALUE.
     * @param <V> type of value. String for VALUE_ONLY and PROPERTY_VALUE.
     * @param record row record as string.
     * @param sep separator as string.
     * @param type type of CSV formate.
     * @return record as Map.
     * @throws IllegalArgumentException if record formate is invalid.
     */
    public static <P,V> Map<P,V> parseRecord(String record,String sep,CsvType type){
        if(type.equals(CsvType.VALUE_ONLY)){
            Map<Integer,String> m=new HashMap<>();
            String ar[]=record.split(sep);
            for(int i=0;i<ar.length;i++)
                m.put(i,ar[i]);
            return (Map<P,V>)m;
        }
        else{
            Map<String,String> m=new HashMap<>();
            String ar[]=record.split(sep);
            for(int i=0;i<ar.length;i++){
                String rec[]=ar[i].split("=",2);
                try{
                    m.put(rec[0],rec[1]);
                }catch(ArrayIndexOutOfBoundsException ex){
                    throw new IllegalArgumentException("invalid record formate.");
                }
            }
            return (Map<P,V>)m;
        }
    }
    
    /**
     * this method returns record as Map object.
     * if the type is VALUE_ONLY then this method returns the object
       of type {@code Map<Integer,String>} in which keys represents indexes.
     * if the type is PROPERTY_VALUE then this method returns the object
       of type {@code Map<Integer,String>}.
     * it use {@code ","} as separator.
     * it returns same result as method call {@code parseRecord(record, ",", type)}.
     * @param <P> type of property. Integer for VALUE_ONLY and String for
       PROPERTY_VALUE.
     * @param <V> type of value. String for VALUE_ONLY and PROPERTY_VALUE.
     * @param record row record as string.
     * @param type type of CSV formate.
     * @return record as Map.
     * @throws IllegalArgumentException if record formate is invalid.
     */
    public static <P,V> Map<P,V> parseRecord(String record,CsvType type){
        return parseRecord(record, ",", type);
    }
    
    /**
     * this method returns record as Map object.
     * if the type is VALUE_ONLY then this method returns the object
       of type {@code Map<Integer,String>} in which keys represents indexes.
     * if the type is PROPERTY_VALUE then this method returns the object
       of type {@code Map<Integer,String>}.
     * it first try to parse the record as PROPERTY_VALUE formate. if it fails
       then it try to parse it as a VALUE_ONLY record.
     * @param <P> type of property. Integer for VALUE_ONLY and String for
       PROPERTY_VALUE.
     * @param <V> type of value. String for VALUE_ONLY and PROPERTY_VALUE.
     * @param record row record as string.
     * @param sep separator as string.
     * @return record as Map.
     * @throws IllegalArgumentException if record formate is invalid.
     */
    public static <P,V> Map<P,V> parseRecord(String record,String sep){
        try{
            return parseRecord(record, sep, CsvType.PROPERTY_VALUE);
        }catch(Exception ex){
        }
        return parseRecord(record, sep, CsvType.VALUE_ONLY);
    }
    
    /**
     * this method returns record as Map object.
     * if the type is VALUE_ONLY then this method returns the object
       of type {@code Map<Integer,String>} in which keys represents indexes.
     * if the type is PROPERTY_VALUE then this method returns the object
       of type {@code Map<Integer,String>}.
     * it first try to parse the record as PROPERTY_VALUE formate. if it fails
       then it try to parse it as a VALUE_ONLY record.
     * it use {@code ","} as separator.
     * it returns same result as method call {@code parseRecord(record, ",")}.
     * @param <P> type of property. Integer for VALUE_ONLY and String for
       PROPERTY_VALUE.
     * @param <V> type of value. String for VALUE_ONLY and PROPERTY_VALUE.
     * @param record row record as string.
     * @return record as Map.
     * @throws IllegalArgumentException if record formate is invalid.
     */
    public static <P,V> Map<P,V> parseRecord(String record){
        return parseRecord(record,",");
    }
}
