package com.cht.ws;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.cht.dto.ws.DeviceDTO;
import com.cht.dto.ws.ListDTO;
import com.cht.dto.ws.UserDTO;

import com.cht.sb.*;
@WebService
public class GatewayWS {

	
	/*
	 * Yao
	 * */
	@WebMethod
	public String modifyMSISDN(String source,String oldValue,String newValue) {
		
		String res = "";
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();

			ResultDTO result =  database.modifyMSISDN(source,"weblogic", "weblogic", "localhost", "7001",oldValue, newValue);
			res="<result>"+result.getResult()+"</result><errmsg>"+result.getErrMsg()+"</errmsg>";
		
		
		} catch (Exception e) {
			res="<result>false</result><errmsg>"+e.toString()+"</errmsg>";
		}
		
		return res;
	}

	/*
	 * Yao
	 * */
	@WebMethod
	public String deleteUser(String source,String devid) {
		
		String res = "";
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			ResultDTO result = database.deleteUser(source,"weblogic", "weblogic", "localhost", "7001",devid);
			res="<result>"+result.getResult()+"</result><errmsg>"+result.getErrMsg()+"</errmsg>";
			
		}  catch (Exception e) {
			res="<result>false</result><errmsg>"+e.toString()+"</errmsg>";
		}
		
		return res;
	}
	
	
	
	@WebMethod
	public String describeServer() {
		
		String result = null;
		
		try {
			InitialContext ctx = new InitialContext();
            ClusterServiceBeanRemoteHome clusterHome = (ClusterServiceBeanRemoteHome)ctx.lookup("ejb.ClusterServiceBeanRemoteHome");
            ClusterServiceBeanRemote cluster = clusterHome.create();
			result = cluster.describeServer("weblogic", "weblogic", "localhost", "7001");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@WebMethod
	public UserDTO describeSubscriber(String name) {
		
		UserDTO user = new UserDTO();
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			user = database.describeSubscriber("weblogic", "weblogic", "localhost", "7001",name);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@WebMethod
	public DeviceDTO describeClient(String name) {
		
		DeviceDTO device = new DeviceDTO();
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			device = database.describeClient("weblogic", "weblogic", "localhost", "7001",name);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return device;
	}
	
	
	@WebMethod
	public ListDTO listClientSubscriptions(String name) {
		
		ListDTO list = new ListDTO();
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			list = database.listClientSubscriptions("weblogic", "weblogic", "localhost", "7001",name);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	@WebMethod
	public boolean addSubscriberToGroup(String name,String group) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.addSubscriberToGroup("weblogic", "weblogic", "localhost", "7001",name,group);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@WebMethod
	public boolean removeSubscriberFromGroup(String name,String group) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.removeSubscriberFromGroup("weblogic", "weblogic", "localhost", "7001",name,group);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@WebMethod
	public boolean addClientSubscription(String name, String feedAppId, boolean activate,boolean trial ) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.addClientSubscription("weblogic", "weblogic", "localhost", "7001",name,feedAppId,activate,trial);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@WebMethod
	public boolean updateClientSubscription(String name, String feedAppId, boolean activate,boolean trial,int error ) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.updateClientSubscription("weblogic", "weblogic", "localhost", "7001",name,feedAppId,activate,trial,error);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean setClientPreference(String name, String feedAppId, String pref ) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.setClientPreference("weblogic", "weblogic", "localhost", "7001",name,feedAppId,pref);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean resetClientPreference(String name, String feedAppId) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.resetClientPreference("weblogic", "weblogic", "localhost", "7001",name,feedAppId);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean setSubscriberAcl(String name, String targetType,String target,int permission) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.setSubscriberAcl("weblogic", "weblogic", "localhost", "7001",name,targetType,target,permission);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean removeSubscriberAcl(String name, String targetType,String target) {
		
		boolean result=false;
		
		try {
			InitialContext ctx = new InitialContext();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome)ctx.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.removeSubscriberAcl("weblogic", "weblogic", "localhost", "7001",name,targetType,target);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}