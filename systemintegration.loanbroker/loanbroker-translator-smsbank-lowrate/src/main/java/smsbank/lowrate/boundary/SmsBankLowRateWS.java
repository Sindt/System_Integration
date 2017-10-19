/**
 * SmsBankLowRateWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package smsbank.lowrate.boundary;

public interface SmsBankLowRateWS extends java.rmi.Remote {
    public smsbank.lowrate.boundary.LoanResponse loanRequest(int arg0, int arg1, int arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public double getInterestRate(int arg0, int arg1, int arg2) throws java.rmi.RemoteException;
}
