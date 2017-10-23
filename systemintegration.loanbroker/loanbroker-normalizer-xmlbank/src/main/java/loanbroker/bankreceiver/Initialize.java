/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanbroker.bankreceiver;


/**
 *
 * @author Kasper
 */
public class Initialize {
    public static void main(String[] args) throws Exception {
        ReceiveXML rx = new ReceiveXML();
        Thread t1 = new Thread(rx);

        
        t1.start();

    }
}
