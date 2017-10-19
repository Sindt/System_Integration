package smsbank.lowrate.boundary;

public class SmsBankLowRateWSProxy implements smsbank.lowrate.boundary.SmsBankLowRateWS {
  private String _endpoint = null;
  private smsbank.lowrate.boundary.SmsBankLowRateWS smsBankLowRateWS = null;
  
  public SmsBankLowRateWSProxy() {
    _initSmsBankLowRateWSProxy();
  }
  
  public SmsBankLowRateWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmsBankLowRateWSProxy();
  }
  
  private void _initSmsBankLowRateWSProxy() {
    try {
      smsBankLowRateWS = (new smsbank.lowrate.boundary.SmsBankLowRateWSServiceLocator()).getSmsBankLowRateWSPort();
      if (smsBankLowRateWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)smsBankLowRateWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)smsBankLowRateWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (smsBankLowRateWS != null)
      ((javax.xml.rpc.Stub)smsBankLowRateWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public smsbank.lowrate.boundary.SmsBankLowRateWS getSmsBankLowRateWS() {
    if (smsBankLowRateWS == null)
      _initSmsBankLowRateWSProxy();
    return smsBankLowRateWS;
  }
  
  public smsbank.lowrate.boundary.LoanResponse loanRequest(int arg0, int arg1, int arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (smsBankLowRateWS == null)
      _initSmsBankLowRateWSProxy();
    return smsBankLowRateWS.loanRequest(arg0, arg1, arg2, arg3);
  }
  
  public double getInterestRate(int arg0, int arg1, int arg2) throws java.rmi.RemoteException{
    if (smsBankLowRateWS == null)
      _initSmsBankLowRateWSProxy();
    return smsBankLowRateWS.getInterestRate(arg0, arg1, arg2);
  }
  
  
}