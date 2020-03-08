package com.vikio.bankapp;

import com.vikio.bankapp.controller.MainController;

public class App {
    public static void main( String[] args ) {
    	MainController mainController = new MainController();
    	mainController.processTransactions();	
    }
}
