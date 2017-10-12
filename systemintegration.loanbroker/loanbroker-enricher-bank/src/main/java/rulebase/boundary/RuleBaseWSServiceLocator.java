/**
 * RuleBaseWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package rulebase.boundary;

public class RuleBaseWSServiceLocator extends org.apache.axis.client.Service implements rulebase.boundary.RuleBaseWSService {

    public RuleBaseWSServiceLocator() {
    }


    public RuleBaseWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RuleBaseWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RuleBaseWSPort
    private java.lang.String RuleBaseWSPort_address = "http://localhost:3000/loanbroker-receiver/rulebase";

    public java.lang.String getRuleBaseWSPortAddress() {
        return RuleBaseWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RuleBaseWSPortWSDDServiceName = "RuleBaseWSPort";

    public java.lang.String getRuleBaseWSPortWSDDServiceName() {
        return RuleBaseWSPortWSDDServiceName;
    }

    public void setRuleBaseWSPortWSDDServiceName(java.lang.String name) {
        RuleBaseWSPortWSDDServiceName = name;
    }

    public rulebase.boundary.RuleBaseWS getRuleBaseWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RuleBaseWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRuleBaseWSPort(endpoint);
    }

    public rulebase.boundary.RuleBaseWS getRuleBaseWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            rulebase.boundary.RuleBaseWSPortBindingStub _stub = new rulebase.boundary.RuleBaseWSPortBindingStub(portAddress, this);
            _stub.setPortName(getRuleBaseWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRuleBaseWSPortEndpointAddress(java.lang.String address) {
        RuleBaseWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (rulebase.boundary.RuleBaseWS.class.isAssignableFrom(serviceEndpointInterface)) {
                rulebase.boundary.RuleBaseWSPortBindingStub _stub = new rulebase.boundary.RuleBaseWSPortBindingStub(new java.net.URL(RuleBaseWSPort_address), this);
                _stub.setPortName(getRuleBaseWSPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RuleBaseWSPort".equals(inputPortName)) {
            return getRuleBaseWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://boundary.rulebase/", "RuleBaseWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://boundary.rulebase/", "RuleBaseWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RuleBaseWSPort".equals(portName)) {
            setRuleBaseWSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
