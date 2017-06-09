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

/**
 * this is {@code Enum} of the types of CSV record formate.
 * there are two {@code enum} values define in this {@code Enum}.<br>
 * VALUE_ONLY {@code enum} refers to a {@code value[,value]...} record formate.
 * in VALUE_ONLY CVS formate, record is a coma separated values.<br>
 * PROPERTY_VALUE {@code enum} refers to a
   {@code property=value[,proprerty=value]...} record formate.
 * in PROPERTY_VALUE CVS formate, record is a coma separated property-value mapping.<br>
 * Note:- {@code ','} is a default separator. some classes support different
   separators. 
 * @author Neel Patel
 */
public enum CsvType {
    VALUE_ONLY(0), PROPERTY_VALUE(1);
    public final int i;
    CsvType(int i){
        this.i=i;
    }
    
    public static CsvType getCsvType(int i){
        if(i==0)
            return VALUE_ONLY;
        return PROPERTY_VALUE;
    }
}
