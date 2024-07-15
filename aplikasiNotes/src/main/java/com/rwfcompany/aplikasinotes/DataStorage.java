/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rwfcompany.aplikasinotes;

/**
 *
 * @author rizky
 */
public interface DataStorage {

    void writeData(String data);

    String readData();
    
    void deleteData(int id); 
}
