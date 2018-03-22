/**
 * 
 */
package com.cht.dto;

/**
 * @author alberltin
 *
 */
public class UserSignalDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String batchId;
	private String message;
	private int numDevices;
	private String providerName;
	private boolean success;
	private long timeStart;
	public UserSignalDTO() {
		super();
		// 
	}
	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the numDevices
	 */
	public int getNumDevices() {
		return numDevices;
	}
	/**
	 * @param numDevices the numDevices to set
	 */
	public void setNumDevices(int numDevices) {
		this.numDevices = numDevices;
	}
	/**
	 * @return the providerName
	 */
	public String getProviderName() {
		return providerName;
	}
	/**
	 * @param providerName the providerName to set
	 */
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the timeStart
	 */
	public long getTimeStart() {
		return timeStart;
	}
	/**
	 * @param timeStart the timeStart to set
	 */
	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}
    public String toString() {
        
        String result = 
        		//"UserSignalDTO begin*********** \n"+
        		"HostAddress.[" + super.HostAddress + "] \n" +
        		"BatchId.[" + this.batchId + "] \n" +
                "Message.[" + this.message + "] \n" +
                "NumDevices.[" + this.numDevices + "] \n" +
                "ProviderName.[" + this.providerName + "] \n" +
                "Success.[" + this.success + "] \n" +
                "TimeStart.[" + this.timeStart + "] \n" ;
                //"UserSignalDTO end************* \n" ;
        
        return result;
    }
}
