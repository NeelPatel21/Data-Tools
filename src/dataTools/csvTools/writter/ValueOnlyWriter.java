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

import dataTools.csvTools.Records;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Neel Patel
 */
class ValueOnlyWriter implements CsvWriter<Integer,String>{
    private Path file; //Path object points to a file where the data will written.
    private List<String> cache; //list to cache the records.
    private int cacheSize = 10; //maximum number records to cache.
    private String sep=","; //separator.
    
    /**
     *
     * @param file Path object of file in which data will be stored.
     */
    ValueOnlyWriter(Path file){
        this.file=file;
        Runtime.getRuntime().addShutdownHook(new Thread(ValueOnlyWriter.this::finalize));
    }
    
    void init(){
        cache= Collections.synchronizedList(new ArrayList(cacheSize));
    }
    
    void setCacheSize(int size){
        cacheSize=size;
    }
    
    int getCacheSize(){
        return cacheSize;
    }
    
    private void writeToFile() throws IOException{
        //creates required directories.
        if(file.getParent()!=null)
            Files.createDirectories(file.getParent());
        //write cached records to the file & clear cache.
        synchronized(cache){
            Files.write(file, cache, StandardOpenOption.APPEND,
                      StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            cache.clear();
        }
    }

    private void flushRoutine(){
        if(cache.size()>=cacheSize)
            new Thread(this::flush).start();
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
    public boolean writeRawRecord(String record) {
        synchronized(cache){
            cache.add(record);
        }
        flushRoutine();
        return true;
    }

    @Override
    public boolean writeMap(Map<Integer, String> data) {
        List<String> temp=new ArrayList<>();
        int max=0;
        try{
            max=data.keySet().stream().mapToInt(i->i)
            .peek(i->{
                if(i<0)
                    throw new IllegalArgumentException("negative index found.");
            }).max().orElse(0);
        }catch(NumberFormatException ex){
            throw new IllegalArgumentException("invalid key format.");
        }
        for(int i=0;i<=max;i++)
            temp.add(data.getOrDefault(i+"", ""));
        String record= Records.makeRecord(temp, sep);
        return writeRawRecord(record);
    }

    @Override
    public void flush() {
        try {
            writeToFile();
        } catch(IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(PropertyValueWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() throws Exception {
        flush();
    }
    
    @Override
    public void finalize(){
        try{
            this.close();
            super.finalize();
        }catch(Throwable ex){
            ex.printStackTrace();
        }
    }
}
