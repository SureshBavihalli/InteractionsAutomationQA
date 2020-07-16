package com.pojoclass;

import java.util.List;

import java.util.ArrayList;
import java.util.List;
public class Root
{
    private String message;

    private String cod;

    private int city_id;

    private double calctime;

    private int cnt;

    private List<ListO> list;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setCod(String cod){
        this.cod = cod;
    }
    public String getCod(){
        return this.cod;
    }
    public void setCity_id(int city_id){
        this.city_id = city_id;
    }
    public int getCity_id(){
        return this.city_id;
    }
    public void setCalctime(double calctime){
        this.calctime = calctime;
    }
    public double getCalctime(){
        return this.calctime;
    }
    public void setCnt(int cnt){
        this.cnt = cnt;
    }
    public int getCnt(){
        return this.cnt;
    }
    public void setList(List<ListO> list){
        this.list = list;
    }
    public List<ListO> getList(){
        return this.list;
    }
}
