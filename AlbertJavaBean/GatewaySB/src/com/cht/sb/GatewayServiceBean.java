package com.cht.sb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.axis.AxisFault;

import weblogic.ejb.GenericSessionBean;
import weblogic.ejbgen.Constants;
import weblogic.ejbgen.FileGeneration;
import weblogic.ejbgen.JndiName;
import weblogic.ejbgen.LocalMethod;
import weblogic.ejbgen.RemoteMethod;
import weblogic.ejbgen.Session;

import com.cht.dto.ws.*;
import com.cht.ws.client.fc.cluster.ManagementException;

@Session(ejbName = "GatewayServiceBean")
@JndiName(remote = "ejb.GatewayServiceBeanRemoteHome")
@FileGeneration(remoteClass = Constants.Bool.TRUE, remoteHome = Constants.Bool.TRUE, localClass = Constants.Bool.TRUE, localHome = Constants.Bool.TRUE)
public class GatewayServiceBean extends GenericSessionBean implements
		SessionBean {
	private static final long serialVersionUID = 1L;

	private Context ctx = null;

	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	/************** Wu *****************/

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean AddSubscriberToGroupService(String username,
			String password, String host, String port, String userid, String group) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.addSubscriberToGroup(username, password, host,
					port, userid, group);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean RemoveSubscriberFromGroupService(String username,
			String password, String host, String port, String userid, String group) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.removeSubscriberFromGroup(username, password,
					host, port, userid, group);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean AddClientSubscriptionService(String username,
			String password, String host, String port, String userid,
			String feedAppId, boolean activate, boolean trial) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.addClientSubscription(username, password, host,
					port, userid, feedAppId, activate, trial);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean UpdateClientSubscriptionService(String username,
			String password, String host, String port, String userid,
			String feedAppId, boolean activate, boolean trial, int error) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.updateClientSubscription(username, password,
					host, port, userid, feedAppId, activate, trial, error);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean SetClientPreferenceService(String username, String password,
			String host, String port, String msisdn, String feedAppId, String pref) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.setClientPreference(username, password, host,
					port, msisdn, feedAppId, pref);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean ResetClientPreferenceService(String username,
			String password, String host, String port, String msisdn,
			String feedAppId) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.resetClientPreference(username, password, host,
					port, msisdn, feedAppId);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean SetSubscriberAclService(String username, String password,
			String host, String port, String userid, String targetType,
			String target, int permission) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.setSubscriberAcl(username, password, host, port,
					userid, targetType, target, permission);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public boolean RemoveSubscriberAclService(String username, String password,
			String host, String port, String userid, String targetType,
			String targetName) {

		boolean result = false;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			result = database.removeSubscriberAcl(username, password, host,
					port, userid, targetType, targetName);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public UserGroupsDTO listSubscriberGroups(String username, String password,
			String host, String port) {

		UserGroupsDTO dto = null;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listSubscriberGroups(username, password, host, port);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return dto;
	}

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public UserDTO describeSubscriber(String username, String password,
			String host, String port,String userid) {

		UserDTO dto = null;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.describeSubscriber(username, password, host, port,userid);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return dto;
	}
	
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listClientSubscriptions(String username, String password,
			String host, String port,String msisdn) {

		ListDTO dto = null;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listClientSubscriptions(username, password, host, port,msisdn);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return dto;
	}
	
	//listFeeds
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listFeedApps(String username, String password,
			String host, String port) {

		ListDTO dto = null;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listFeedApps(username, password, host, port,true);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return dto;
	}
	//
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listCategories(String username, String password,
			String host, String port) {

		ListDTO dto = null;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listCategories(username, password, host, port);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return dto;
	}
	/*******************************************************************************************/
	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO RetrieveUserAcl(String username, String password,
			String host, String port, String userid) {
		ListDTO list = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			list = database.listSubscriberAcl(username, password, host, port,
					userid);

		} catch (Exception e) {
			list=null;
		}
		return list;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public PackageDTO describePackage(String username, String password,
			String host, String port, String id) {
		PackageDTO dto = null;

		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.describePackage(username, password, host, port, id);

		} catch (ManagementException e) {
			e.printStackTrace();
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return dto;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public FeedappDTO describeFeedApp(String username, String password,
			String host, String port, String id) {
		FeedappDTO dto = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.describeFeedApp(username, password, host, port, id);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		}

		return dto;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public CategoryDTO describeCategory(String username, String password,
			String host, String port, String id) {
		CategoryDTO dto = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.describeCategory(username, password, host, port, id);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.describeFeedApp error:"
					+ e.toString());
		}

		return dto;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listCategoryAcl(String username, String password,
			String host, String port, String id) {
		ListDTO dto = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listCategoryAcl(username, password, host, port, id);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		}

		return dto;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listSubscriberGroupAcl(String username, String password,
			String host, String port, String id) {
		ListDTO dto = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listSubscriberGroupAcl(username, password, host, port, id);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		}

		return dto;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listPackageFeedApps(String username, String password,
			String host, String port, String id) {
		ListDTO dto = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listPackageFeedApps(username, password, host, port, id);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		}

		return dto;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ListDTO listPackageSubscriptions(String username, String password,
			String host, String port, String id) {
		ListDTO dto = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.listPackageSubscriptions(username, password, host, port, id);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		}

		return dto;
	}

	/**
	 * Lu
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public DeviceDTO describeClient(String username, String password,
			String host, String port, String msisdn) {
		DeviceDTO dto = null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			dto = database.describeClient(username, password, host, port, msisdn);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.listCategoryAcl error:"
					+ e.toString());
		}

		return dto;
	}

	/*
	 * Yao
	 * */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO deleteUser(String source,String username, String password, String host, String port,String msisdnToDelete){
		
		ResultDTO aResultDTO=null;
		try {
			aResultDTO=new ResultDTO();
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			aResultDTO=database.deleteUser(source,username, password, host, port, msisdnToDelete);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.deleteUser ManagementException:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.deleteUser AxisFault:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.deleteUser CreateException:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.deleteUser NamingException:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.deleteUser RemoteException:"
					+ e.toString());
		}
        return aResultDTO;
		
	
	}
	/*
	 * Yao
	 * */

	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO modifyMSISDN(String source,String username, String password, String host, String port,String oldMSISDN,String newMSISDN){
		
		ResultDTO aResultDTO=null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			aResultDTO=database.modifyMSISDN(source,username, password, host, port, oldMSISDN, newMSISDN);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.modifyMSISDN ManagementException:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.modifyMSISDN AxisFault:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.modifyMSISDN CreateException:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.modifyMSISDN NamingException:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.modifyMSISDN RemoteException:"
					+ e.toString());
		}
        return aResultDTO;
		
		
		
		
	}
	
	
	
	/*
	 * Yao
	 * */


	


	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public String getFeedAppName(String username, String password, String host,
			String port, String FeedAppid) {
		FeedappDTO dto=null;
	    String name="";
		try{
			dto = this.describeFeedApp(username,password,host,port,FeedAppid);
			name=dto.getName();
		} catch (Exception e) {
			System.out
					.println("Exception on getFeedAppName:"
							+ e);
		}
		return name;
	}

	/*
	 * Yao
	 * */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public String  getPackageName(String username, String password, String host, String port,String Packageid){
		PackageDTO dto=null;
		String name="";
		try {
			
			dto=this.describePackage(username,password,host,port,Packageid);
			
			name=dto.getName();
			
		} catch (Exception e) {
			System.out.println("Exception on getPackageName:"+e);
		}
			return name;
			
	}
	/*
	 * Yao
	 * */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public void _checkTrialUsersTask(String username, String password,
			String host, String port) {
		
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			database._checkTrialUsersTask(username, password, host, port);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean._checkTrialUsersTask ManagementException:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean._checkTrialUsersTask AxisFault:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean._checkTrialUsersTask CreateException:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean._checkTrialUsersTask NamingException:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean._checkTrialUsersTask RemoteException:"
					+ e.toString());
		}
		
		
	}
	
	/*
	 * Yao
	 * */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO isPackageSubscribed(String username, String password,
			String host, String port, String my_msisdn, String my_packageid) {
		ResultDTO aResultDTO=null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			aResultDTO=database.isPackageSubscribed(username, password, host, port, my_msisdn, my_packageid);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.isPackageSubscribed ManagementException:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.isPackageSubscribed AxisFault:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.isPackageSubscribed CreateException:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.isPackageSubscribed NamingException:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.isPackageSubscribed RemoteException:"
					+ e.toString());
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
		ResultDTO aResultDTO=null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			aResultDTO=database.cancelPackageSubscription(username, password, host, port, msisdn, packageid);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.cancelPackageSubscription ManagementException:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.cancelPackageSubscription AxisFault:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.cancelPackageSubscription CreateException:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.cancelPackageSubscription NamingException:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.cancelPackageSubscription RemoteException:"
					+ e.toString());
		}
		return aResultDTO;
		
		
	}
	
	/*
	 * Yao
	 */
	@LocalMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	@RemoteMethod(transactionAttribute = Constants.TransactionAttribute.NEVER)
	public ResultDTO cancelChannelSubscription(String username,
			String password, String host, String port, String msisdn,
			String feedappid) {
	
		ResultDTO aResultDTO=null;
		try {
			DatabaseServiceBeanRemoteHome databaseHome = (DatabaseServiceBeanRemoteHome) ctx
					.lookup("ejb.DatabaseServiceBeanRemoteHome");
			DatabaseServiceBeanRemote database = databaseHome.create();
			aResultDTO=database.cancelChannelSubscription(username, password, host, port, msisdn, feedappid);

		} catch (ManagementException e) {
			System.out.println("GatewayServiceBean.cancelChannelSubscription ManagementException:"
					+ e.toString());
		} catch (AxisFault e) {
			System.out.println("GatewayServiceBean.cancelChannelSubscription AxisFault:"
					+ e.toString());
		} catch (CreateException e) {
			System.out.println("GatewayServiceBean.cancelChannelSubscription CreateException:"
					+ e.toString());
		} catch (NamingException e) {
			System.out.println("GatewayServiceBean.cancelChannelSubscription NamingException:"
					+ e.toString());
		} catch (RemoteException e) {
			System.out.println("GatewayServiceBean.cancelChannelSubscription RemoteException:"
					+ e.toString());
		}
		return aResultDTO;
		
		
	}
	

}