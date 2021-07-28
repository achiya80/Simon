package PresentationLayer;

import DataAccessLayer.CreateDataBase;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class main {


    public static void main(String[] args){
        CreateDataBase c = new CreateDataBase();
        boolean i = true;
        SimonGame simonGame = null;
        while (simonGame == null || simonGame.notOver()){
            if(i){
                i = false;
                simonGame = new SimonGame();
            }
            simonGame.nextTurn();
        }
    }
}
