package com.cht.sb;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.axis.AxisFault;

import weblogic.ejb.GenericSessionBean;
import weblogic.ejbgen.Constants;
import weblogic.ejbgen.FileGeneration;
import weblogic.ejbgen.JndiName;
import weblogic.ejbgen.LocalMethod;
import weblogic.ejbgen.RemoteMethod;
import weblogic.ejbgen.Session;

import com.cht.dto.ws.*;


import com.cht.util.ResourceLocator;
import com.cht.ws.castor.Transformer;
import com.cht.ws.client.fc.cluster.ManagementException;
import com.cht.ws.client.fc.database.DatabaseManagementServiceLocator;
import com.cht.ws.client.fc.database.DatabaseManagementSoapBindingStub;

@Session(ejbName = "DatabaseServiceBean")
@JndiName(remote = "ejb.DatabaseServiceBeanRemoteHome")
@FileGeneration(remoteClass = Constants.Bool.TRUE, remoteHome = Constants.Bool.TRUE, localClass = Constants.Bool.TRUE, localHome = Constants.Bool.TRUE)
public class DatabaseServiceBean extends GenericSessionBean implements
		SessionBean {
	private static final long serialVersionUID = 1L;

	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public UserDTO describeSubscriber(String username, String password, String host, String port, String userid) {
		
		String result = null;
		UserDTO dto = null;
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.describeSubscriber(userid);
			dto = Transformer.describeSubscriber(result);
		} catch (Exception e) {
			dto = null;
		}
		
		return dto;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public DeviceDTO describeClient(String username, String password, String host, String port, String msisdn) {
		
		String result = null;
		DeviceDTO device = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.describeClient(msisdn);
			device = Transformer.describeClient(result);
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			device = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return device;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listClientSubscriptions(String username, String password, String host, String port, String msisdn) {
		
		String result = null;
		ListDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listClientSubscriptions(msisdn);
			dto = Transformer.listClientSubscriptions(result);
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean addSubscriberToGroup(String username, String password, String host, String port, String userid,String group) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.addSubscriberToGroup(userid, group);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean removeSubscriberFromGroup(String username, String password, String host, String port, String userid,String group) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.removeSubscriberFromGroup(userid, group);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean addClientSubscription(String username, String password, String host, String port,String userid, String feedAppId, boolean activate,boolean trial ) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.addClientSubscription(userid, feedAppId, activate, trial);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean updateClientSubscription(String username, String password, String host, String port,String userid, String feedAppId, boolean activate,boolean trial,int error ) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.updateClientSubscription(userid, feedAppId, activate, trial,error);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean setClientPreference(String username, String password, String host, String port,String msisdn, String feedAppId, String pref ) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.setClientPreference(msisdn, feedAppId, pref);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean resetClientPreference(String username, String password, String host, String port,String userid, String feedAppId) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.resetClientPreference(userid, feedAppId);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean setSubscriberAcl(String username, String password, String host, String port,String userid, String targetType,String target,int permission) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.setSubscriberAcl(userid, targetType, target, permission);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean removeSubscriberAcl(String username, String password, String host, String port,String userid, String targetType,String targetName) {
		
		boolean result = false;
		
		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.removeSubscriberAcl(userid, targetType, targetName);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public UserGroupsDTO listSubscriberGroups(String username, String password, String host, String port) {
		String result="";
		UserGroupsDTO dto = null;

		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listSubscriberGroups();
			dto=Transformer.listSubscriberGroups(result);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
	
//	listFeedApps
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listFeedApps(String username, String password, String host, String port,boolean commissionOnly) {
		String result="";
		ListDTO dto = null;

		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listFeeds();
			dto=Transformer.listFeedApps(result);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listCategories(String username, String password, String host, String port) {
		String result="";
		ListDTO dto = null;

		
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listCategories();
			dto=Transformer.listCategories(result);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listSubscriberAcl(String username, String password, String host, String port,String name) {
		String result="";
		ListDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listSubscriberAcl(name);
			dto=Transformer.listSubscriberAcl(result);
			
		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return dto;
	}	

	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public FeedappDTO describeFeedApp(String username, String password, String host, String port,String name) {
		String result="";
		FeedappDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.describeFeedApp(name);
			dto=Transformer.describeFeedApp(result);
			
		} catch (ManagementException e) {
			System.out.println("describeFeedApp ManagementException error:"+e.toString());
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			System.out.println("describeFeedApp MalformedURLException error:"+e.toString());
		} catch (RemoteException e) {
			System.out.println("describeFeedApp RemoteException error:"+e.toString());
		}
		return dto;
	}	

	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public PackageDTO describePackage(String username, String password, String host, String port,String name) {
		String result="";
		PackageDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.describePackage(name);
			dto=Transformer.describePackage(result);
			
		} catch (ManagementException e) {
			System.out.println("describePackage ManagementException error:"+e.toString());
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			System.out.println("describePackage MalformedURLException error:"+e.toString());
		} catch (RemoteException e) {
			System.out.println("describePackage RemoteException error:"+e.toString());
		}
		return dto;
	}	

	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public CategoryDTO describeCategory(String username, String password, String host, String port,String name) {
		String result="";
		CategoryDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.describeCategory(name);
			dto=Transformer.describeCategory(result);
		} catch (ManagementException e) {
			System.out.println("describeCategory ManagementException error:"+e.toString());
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			System.out.println("describeCategory MalformedURLException error:"+e.toString());
		} catch (RemoteException e) {
			System.out.println("describeCategory RemoteException error:"+e.toString());
		}
		return dto;
	}	

	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listCategoryAcl(String username, String password, String host, String port,String name) {
		String result="";
		ListDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listCategoryAcl(name);
			dto=Transformer.listCategoryAcl(result);
		} catch (ManagementException e) {
			System.out.println("describeCategory ManagementException error:"+e.toString());
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			System.out.println("describeCategory MalformedURLException error:"+e.toString());
		} catch (RemoteException e) {
			System.out.println("describeCategory RemoteException error:"+e.toString());
		}
		return dto;
	}	

	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listSubscriberGroupAcl(String username, String password, String host, String port,String name) {
		String result="";
		ListDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listCategoryAcl(name);
			dto=Transformer.listSubscriberGroupAcl(result);
		} catch (ManagementException e) {
			System.out.println("describeCategory ManagementException error:"+e.toString());
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			System.out.println("describeCategory MalformedURLException error:"+e.toString());
		} catch (RemoteException e) {
			System.out.println("describeCategory RemoteException error:"+e.toString());
		}
		return dto;
	}	
	

	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listPackageFeedApps(String username, String password, String host, String port,String name) {
		String result="";
		ListDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listPackageFeedApps(name);
			dto=Transformer.listPackageFeedApps(result);
		} catch (ManagementException e) {
			System.out.println("listPackageFeedApps ManagementException error:"+e.toString());
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			System.out.println("listPackageFeedApps MalformedURLException error:"+e.toString());
		} catch (RemoteException e) {
			System.out.println("listPackageFeedApps RemoteException error:"+e.toString());
		}
		return dto;
	}	

	/**
	 * @author Lu
	 * 
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listPackageSubscriptions(String username, String password, String host, String port,String name) {
		String result="";
		ListDTO dto = null;
		try {
			DatabaseManagementSoapBindingStub database = helper(username, password, host, port);
			result = database.listPackageSubscriptions(name);
			dto=Transformer.listPackageSubscriptions(result);
		} catch (ManagementException e) {
			System.out.println("listPackageFeedApps ManagementException error:"+e.toString());
		} catch (AxisFault e) {
			dto = null;
		} catch (MalformedURLException e) {
			System.out.println("listPackageFeedApps MalformedURLException error:"+e.toString());
		} catch (RemoteException e) {
			System.out.println("listPackageFeedApps RemoteException error:"+e.toString());
		}
		return dto;
	}	
	
	private DatabaseManagementSoapBindingStub helper(String username, String password, String host, String port) 
		throws ManagementException, AxisFault, MalformedURLException {
		
		DatabaseManagementSoapBindingStub database = null;
		URL endpointURL = null;
		if (host == null) host = "localhost";
		if (port == null) port = "80";
		
		endpointURL = new URL("http://" + host + ":" + port + "/feedserver/admin/services/DatabaseManagement");
		database = new DatabaseManagementSoapBindingStub(endpointURL, new DatabaseManagementServiceLocator());
		database.setUsername(username);
		database.setPassword(password);

		return database;
	}
	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO deleteUser(String source, String username,
			String password, String host, String port, String msisdnToDelete) {
		boolean result = false;
		String errMsg = "";
		ResultDTO aResultDTO = null;
		String useridtoRemove = "";
		try {
			aResultDTO = new ResultDTO();
			useridtoRemove = this.getUserIDByMSISDN(msisdnToDelete);
		} catch (Exception e) {
			result = false;
			errMsg = "Exception1 on deleteUser " + e.toString();
			System.out.println("Exception1 on deleteUser:" + e);
		}
		try {
			if (!useridtoRemove.equalsIgnoreCase("")) {

				DatabaseManagementSoapBindingStub stub = helper(username,
						password, host, port);
				result = stub.removeSubscriber(useridtoRemove);
			} else {
				result = false;
				if (!errMsg.equalsIgnoreCase("")) {
					errMsg = "devid " + msisdnToDelete
							+ " does not exist in fc_member table";
				}
			}

		} catch (Exception e) {
			result = false;
			errMsg = "Exception2 on deleteUser " + e.toString();
			System.out.println("Exception2 on deleteUser:" + e);
		}
		if (result == true) {
			aResultDTO.setResult("true");
			aResultDTO.setErrMsg("none");
			this.log_delete_fc_user(source, useridtoRemove, msisdnToDelete);
			this.delete_fc_memberByMSISDN(msisdnToDelete);
		} else {
			aResultDTO.setResult("false");
			if (errMsg != null) {
				errMsg = errMsg.replaceAll("&apos;", " ");
			}
			aResultDTO.setErrMsg(errMsg);
		}
		return aResultDTO;

	}

	/*
	 * Yao
         */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO modifyMSISDN(String source, String username,
			String password, String host, String port, String oldMSISDN,
			String newMSISDN) {
		boolean result = false;
		String res = "";
		String errMsg = "";

		try {
			DatabaseManagementSoapBindingStub stub = helper(username, password,
					host, port);
			res = stub.updateClientAttribute(oldMSISDN, "id", newMSISDN);
			if (res != null
					& res.trim().equalsIgnoreCase("<message></message>")) {
				result = true;
				errMsg = res;
			} else {
				result = false;
				errMsg = res;
			}

		} catch (Exception e) {
			result = false;
			errMsg = e.toString();
			System.out.println("Exception on modifyMSISDN:" + e);
		}
		ResultDTO aResultDTO = new ResultDTO();
		if (result == true) {
			aResultDTO.setResult("true");
			aResultDTO.setErrMsg("none");
			this.log_modify_msisdn(source, oldMSISDN, newMSISDN);
			this.modify_fc_member_MSISDN(oldMSISDN, newMSISDN);
		} else {
			aResultDTO.setResult("false");
			if (errMsg != null) {
				errMsg = errMsg.replaceAll("&apos;", " ");
			}
			aResultDTO.setErrMsg(errMsg);
		}
		return aResultDTO;

	}

	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public String getFeedAppName(String username, String password, String host,
			String port, String FeedAppid) {
		FeedappDTO dto = null;
		String name = "";
		try {
			dto = this.describeFeedApp(username, password, host, port,
					FeedAppid);
			name = dto.getName();
		} catch (Exception e) {
			System.out.println("Exception on getFeedAppName:" + e);
		}
		return name;
	}

	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public String getPackageName(String username, String password, String host,
			String port, String Packageid) {
		PackageDTO dto = null;
		String name = "";
		try {

			dto = this.describePackage(username, password, host, port,
					Packageid);

			name = dto.getName();

		} catch (Exception e) {
			System.out.println("Exception on getPackageName:" + e);
		}
		return name;

	}

	/*
	 * Yao
	 */

	@SuppressWarnings("finally")
	private boolean log_modify_msisdn(String source, String old_msisdn,
			String new_msisdn) {
		boolean isOK = false;
		Connection conn = null;

		PreparedStatement pstmt = null;
		Context ctx = null;
		DataSource ds = null;
		String jndiDataSource = "";
		String SqlString = "insert into log_modify_msisdn(source,old_msisdn,new_msisdn)values(?,?,?)";

		try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiDataSource);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SqlString);
			pstmt.setString(1, source);
			pstmt.setString(2, old_msisdn);
			pstmt.setString(3, new_msisdn);
			isOK = pstmt.execute();
		} catch (Exception e) {
			System.out.println("Error on log_modify_msisdn:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}

			return isOK;
		}

	}

	/*
	 * Yao
	 */

	@SuppressWarnings("finally")
	private boolean log_delete_fc_user(String source, String userid,
			String devid) {
		boolean isOK = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Context ctx = null;
		DataSource ds = null;
		String jndiDataSource = "";
		String SqlString = "insert into log_delete_fc_user(source,userid,devid)values(?,?,?)";

		try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiDataSource);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SqlString);
			pstmt.setString(1, source);
			pstmt.setString(2, userid);
			pstmt.setString(3, devid);
			isOK = pstmt.execute();
		} catch (Exception e) {
			System.out.println("Error on log_delete_fc_user:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}

			return isOK;
		}

	}

	/*
	 * Yao
	 */

	@SuppressWarnings("finally")
	private boolean delete_fc_memberByMSISDN(String msisdn) {
		boolean isOK = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Context ctx = null;
		DataSource ds = null;
		String jndiDataSource = "";
		String SqlString = "delete from  fc_member where devid=?";

		try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiDataSource);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SqlString);
			pstmt.setString(1, msisdn);

			isOK = pstmt.execute();
		} catch (Exception e) {
			System.out.println("Error on delete_fc_memberByMSISDN:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}

			return isOK;
		}

	}

	/*
	 * Yao
	 */

	@SuppressWarnings("finally")
	private boolean modify_fc_member_MSISDN(String old_devid, String new_devid) {
		boolean isOK = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Context ctx = null;
		DataSource ds = null;
		String jndiDataSource = "";
		String SqlString = "update fc_member set devid=? where  devid=? ";
		try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiDataSource);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SqlString);
			pstmt.setString(1, new_devid);
			pstmt.setString(2, old_devid);

			isOK = pstmt.execute();
		} catch (Exception e) {
			System.out.println("Error on modify_fc_member_MSISDN:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}

			return isOK;
		}

	}

	/*
	 * Yao
	 */

	@SuppressWarnings("finally")
	private String getUserIDByMSISDN(String msisdn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Context ctx = null;
		DataSource ds = null;
		String jndiDataSource = "";
		String result = "";
		ResultSet rs = null;
		String SqlString = "select userid from  fc_member where devid=?";

		try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiDataSource);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SqlString);
			pstmt.setString(1, msisdn);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("userid");
			} else {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("Error on getUserIDByMSISDN:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}

			return result;
		}

	}

	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO cancelChannelSubscription(String username,
			String password, String host, String port, String msisdn,
			String feedappid) {
		boolean result = false;
		String errMsg = "";
		ResultDTO aResultDTO = null;
		String userid = "";
		try {
			aResultDTO = new ResultDTO();
			userid = this.getUserIDByMSISDN(msisdn);
		} catch (Exception e) {
			result = false;
			errMsg = "Exception1 on cancelChannelSubscription " + e.toString();
			System.out.println("Exception1 on cancelChannelSubscription:" + e);
		}
		try {
			if (!userid.equalsIgnoreCase("")) {

				DatabaseManagementSoapBindingStub stub = helper(username,
						password, host, port);
				result = stub.cancelClientSubscription(userid, feedappid);
			} else {
				result = false;
				if (!errMsg.equalsIgnoreCase("")) {
					errMsg = "devid " + msisdn
							+ " does not exist in fc_member table";
				}
			}

		} catch (Exception e) {
			result = false;
			errMsg = "Exception2 on cancelChannelSubscription " + e.toString();
			System.out.println("Exception2 on cancelChannelSubscription:" + e);
		}
		if (result == true) {
			aResultDTO.setResult("true");
			aResultDTO.setErrMsg("none");

		} else {
			aResultDTO.setResult("false");
			if (errMsg != null) {
				errMsg = errMsg.replaceAll("&apos;", " ");
			}
			aResultDTO.setErrMsg(errMsg);
		}
		return aResultDTO;

	}

	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO cancelPackageSubscription(String username,
			String password, String host, String port, String msisdn,
			String packageid) {

		boolean result = false;
		String errMsg = "";
		ResultDTO aResultDTO = null;
		try {
			aResultDTO = new ResultDTO();
			DatabaseManagementSoapBindingStub stub = helper(username, password,
					host, port);
			result = stub.cancelPackageSubscription(msisdn, packageid);
		} catch (Exception e) {
			result = false;
			errMsg = "Exception on cancelPackageSubscription " + e.toString();
			System.out.println("Exception on cancelPackageSubscription:" + e);
		}
		if (result == true) {
			aResultDTO.setResult("true");
			aResultDTO.setErrMsg("none");

		} else {
			aResultDTO.setResult("false");
			if (errMsg != null) {
				errMsg = errMsg.replaceAll("&apos;", " ");
			}
			aResultDTO.setErrMsg(errMsg);
		}
		return aResultDTO;
	}

	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO isPackageSubscribed(String username, String password,
			String host, String port, String my_msisdn, String my_packageid) {

		boolean result = false;
		String errMsg = "";
		ResultDTO aResultDTO = null;
		String userid = "";
		try {
			aResultDTO = new ResultDTO();
			userid = this.getUserIDByMSISDN(my_msisdn);
		} catch (Exception e) {
			result = false;
			errMsg = "Exception1 on isPackageSubscribed " + e.toString();
			System.out.println("Exception1 on isPackageSubscribed:" + e);
		}

		try {
			if (!userid.equalsIgnoreCase("")) {
				aResultDTO = new ResultDTO();

				ListDTO list = this.listPackageSubscriptions(username,
						password, host, port, userid);
				String packageid = "";
			
				if (list != null) {
					
					Collection c =null ;
					
					
					c=list.getPkgsubscriptions();
					Iterator it = c.iterator();

					while (it.hasNext()) {
						
						PackageSubscriptionDTO aPackageSubscriptionDTO = (PackageSubscriptionDTO) it
								.next();
						
						packageid = aPackageSubscriptionDTO.getPackageId();
						System.out.println("packageid=" + packageid);
						if (packageid != null
								&& packageid.equalsIgnoreCase(my_packageid)) {
							result = true;
							break;
						}

					}
					if (packageid != null) {
						if (packageid.equalsIgnoreCase("")) {
							result = false;
						}
					} else {
						result = false;
					}
				} else {
					result = false;
					errMsg = "devid " + my_msisdn
					+ " does not have any package subscribed";
				}

			} else {
				System.out.println("q1");
				result = false;
				if (!errMsg.equalsIgnoreCase("")) {
					errMsg = "devid " + my_msisdn
							+ " does not exist in fc_member table";
				}
			}
		} catch (Exception e) {
			result = false;
			errMsg = "Exception2 on isPackageSubscribed " + e.toString();
			System.out.println("Exception2 on isPackageSubscribed:" + e);
		}

		if (result == true) {
			aResultDTO.setResult("true");
			aResultDTO.setErrMsg("none");

		} else {
			aResultDTO.setResult("false");
			if (errMsg != null) {
				errMsg = errMsg.replaceAll("&apos;", " ");
			}
			aResultDTO.setErrMsg(errMsg);
		}
		return aResultDTO;
	}

	/*
	 * Yao
	 */
	@SuppressWarnings("finally")
	private Vector getTrialUserMSISDNs() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Context ctx = null;
		DataSource ds = null;
		String jndiDataSource = "";
		ResultSet rs = null;
		Vector<String> aVector = null;
		String SqlString = "select devid from  fc_member where (trialenddate-sysdate)<0 and status='A'";

		try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiDataSource);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SqlString);

			rs = pstmt.executeQuery();
			aVector = new Vector<String>();
			while (rs.next()) {
				aVector.addElement(rs.getString("devid"));
			}
		} catch (Exception e) {
			System.out.println("Error on getTrialUsers:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}

			return aVector;
		}

	}

	/*
	 * Yao
	 */
	@SuppressWarnings("finally")
	private boolean markUserTrialExpired(String msisdn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Context ctx = null;
		DataSource ds = null;
		String jndiDataSource = "";

		String SqlString = "update  fc_member set status='E' where devid=?";
		boolean isDone = false;
		try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiDataSource);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SqlString);
			pstmt.setString(1, msisdn);
			isDone = pstmt.execute();

		} catch (Exception e) {
			System.out.println("Error on markUserTrialExpired:" + e);
		} finally {
			try {

				if (conn != null) {
					conn.close();
				}
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}

			return isDone;
		}

	}

	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public void _checkTrialUsersTask(String username, String password,
			String host, String port) {
		try {
			Vector trialUserMSISDNs=this.getTrialUserMSISDNs();
			for (int i=0;i<trialUserMSISDNs.size();i++){
			  String  msisdn	=(String)trialUserMSISDNs.elementAt(i);
			  ResultDTO aResultDTO=this.isPackageSubscribed("weblogic", "weblogic", "localhost", "7001", msisdn, "BASIC");
				if (aResultDTO.getResult().equalsIgnoreCase("true")){
				    	
					ResultDTO bResultDTO=this.cancelPackageSubscription("weblogic", "weblogic", "localhost", "7001", msisdn, "BASIC");
					if (bResultDTO.getResult().equalsIgnoreCase("true")){ 
						this.markUserTrialExpired(msisdn);
					}else{
						//cancel basic channel fail
					}
					
				}else{
					
					this.markUserTrialExpired(msisdn);
				}
			}
			
            //after excution~~
		} catch (Exception e) {
			System.out.println("Error on _checkTrialUsersTask:" + e);
		} finally {

		}

	}
	
	

}