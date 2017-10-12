package rulebase.boundary;

public class RuleBaseWSProxy implements rulebase.boundary.RuleBaseWS {
  private String _endpoint = null;
  private rulebase.boundary.RuleBaseWS ruleBaseWS = null;
  
  public RuleBaseWSProxy() {
    _initRuleBaseWSProxy();
  }
  
  public RuleBaseWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initRuleBaseWSProxy();
  }
  
  private void _initRuleBaseWSProxy() {
    try {
      ruleBaseWS = (new rulebase.boundary.RuleBaseWSServiceLocator()).getRuleBaseWSPort();
      if (ruleBaseWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ruleBaseWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ruleBaseWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ruleBaseWS != null)
      ((javax.xml.rpc.Stub)ruleBaseWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public rulebase.boundary.RuleBaseWS getRuleBaseWS() {
    if (ruleBaseWS == null)
      _initRuleBaseWSProxy();
    return ruleBaseWS;
  }
  
  public java.lang.String[] getBanks(int arg0, int arg1, int arg2) throws java.rmi.RemoteException{
    if (ruleBaseWS == null)
      _initRuleBaseWSProxy();
    return ruleBaseWS.getBanks(arg0, arg1, arg2);
  }
  
  
}