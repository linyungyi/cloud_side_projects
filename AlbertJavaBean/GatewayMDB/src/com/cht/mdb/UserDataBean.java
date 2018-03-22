package com.cht.mdb;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.MessageDrivenBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import weblogic.ejb.GenericMessageDrivenBean;
import weblogic.ejbgen.MessageDriven;

import com.cht.dto.UserDataDTO;
import com.cht.task.TaskInf;
import com.cht.util.TaskLoader;
/**
 * @author alberltin
 *
 */
@MessageDriven(ejbName = "ejb.UserDataBean", destinationJndiName = "jms.topic.UserData", destinationType = "javax.jms.Topic", transactionType = MessageDriven.MessageDrivenTransactionType.BEAN)
public class UserDataBean extends GenericMessageDrivenBean implements
		MessageDrivenBean, MessageListener {
	private static final long serialVersionUID = 1L;

	public void onMessage(Message msg) {

		UserDataDTO dto = null;
		ObjectMessage omsg = (ObjectMessage)msg;
		try {
			dto = (UserDataDTO)omsg.getObject();
			Collection tasks = TaskLoader.getTaskList("TASK_ID." + this.getClass().getSimpleName());
			Iterator i = tasks.iterator();
			while (i.hasNext()) {
				TaskInf task = (TaskInf)i.next();
				task.process(this.getClass().getName(), dto);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}