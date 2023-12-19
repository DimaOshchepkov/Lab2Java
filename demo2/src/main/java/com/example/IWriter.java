package com.example;


public interface IWriter {
    
    void write(String path, String message);

    String getExtention();
}
