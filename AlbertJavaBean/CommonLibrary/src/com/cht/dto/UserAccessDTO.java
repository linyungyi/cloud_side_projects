package com.cht.dto;

//import java.io.Serializable;

/**
 *
 * @author albertlin
 */
public class UserAccessDTO extends BaseDTO {
    
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of UserAccessDTO */
    public UserAccessDTO() {
    	super();
    }

    /**
     * Holds value of property dataIn.
     */
    private long dataIn;

    /**
     * Getter for property dataIn.
     * @return Value of property dataIn.
     */
    public long getDataIn() {
        return this.dataIn;
    }

    /**
     * Setter for property dataIn.
     * @param dataIn New value of property dataIn.
     */
    public void setDataIn(long dataIn) {
        this.dataIn = dataIn;
    }

    /**
     * Holds value of property dataOut.
     */
    private long dataOut;

    /**
     * Getter for property dataOut.
     * @return Value of property dataOut.
     */
    public long getDataOut() {
        return this.dataOut;
    }

    /**
     * Setter for property dataOut.
     * @param dataOut New value of property dataOut.
     */
    public void setDataOut(long dataOut) {
        this.dataOut = dataOut;
    }

    /**
     * Holds value of property deviceId.
     */
    private String deviceId;

    /**
     * Getter for property deviceId.
     * @return Value of property deviceId.
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Setter for property deviceId.
     * @param deviceId New value of property deviceId.
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Holds value of property headerIn.
     */
    private long headerIn;

    /**
     * Getter for property headerIn.
     * @return Value of property headerIn.
     */
    public long getHeaderIn() {
        return this.headerIn;
    }

    /**
     * Setter for property headerIn.
     * @param headerIn New value of property headerIn.
     */
    public void setHeaderIn(long headerIn) {
        this.headerIn = headerIn;
    }

    /**
     * Holds value of property headerOut.
     */
    private long headerOut;

    /**
     * Getter for property headerOut.
     * @return Value of property headerOut.
     */
    public long getHeaderOut() {
        return this.headerOut;
    }

    /**
     * Setter for property headerOut.
     * @param headerOut New value of property headerOut.
     */
    public void setHeaderOut(long headerOut) {
        this.headerOut = headerOut;
    }

    /**
     * Holds value of property request.
     */
    private String request;

    /**
     * Getter for property request.
     * @return Value of property request.
     */
    public String getRequest() {
        return this.request;
    }

    /**
     * Setter for property request.
     * @param request New value of property request.
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * Holds value of property requestId.
     */
    private String requestId;

    /**
     * Getter for property requestId.
     * @return Value of property requestId.
     */
    public String getRequestId() {
        return this.requestId;
    }

    /**
     * Setter for property requestId.
     * @param requestId New value of property requestId.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Holds value of property sessionId.
     */
    private String sessionId;

    /**
     * Getter for property sessionId.
     * @return Value of property sessionId.
     */
    public String getSessionId() {
        return this.sessionId;
    }

    /**
     * Setter for property sessionId.
     * @param sessionId New value of property sessionId.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Holds value of property timeEnd.
     */
    private long timeEnd;

    /**
     * Getter for property timeEnd.
     * @return Value of property timeEnd.
     */
    public long getTimeEnd() {
        return this.timeEnd;
    }

    /**
     * Setter for property timeEnd.
     * @param timeEnd New value of property timeEnd.
     */
    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * Holds value of property timeStart.
     */
    private long timeStart;

    /**
     * Getter for property timeStart.
     * @return Value of property timeStart.
     */
    public long getTimeStart() {
        return this.timeStart;
    }

    /**
     * Setter for property timeStart.
     * @param timeStart New value of property timeStart.
     */
    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * Holds value of property userAddress.
     */
    private String userAddress;

    /**
     * Getter for property userAddress.
     * @return Value of property userAddress.
     */
    public String getUserAddress() {
        return this.userAddress;
    }

    /**
     * Setter for property userAddress.
     * @param userAddress New value of property userAddress.
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * Holds value of property userId.
     */
    private String userId;

    /**
     * Getter for property userId.
     * @return Value of property userId.
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Setter for property userId.
     * @param userId New value of property userId.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String toString() {
        
        String result = 
        		//"UserAccessDTO begin*********** \n"+
        		"HostAddress.[" + super.HostAddress + "] \n" +
                "DataIn.[" + dataIn + "] \n" +
                "DataOut.[" + dataOut + "] \n" +
                "DeviceId.[" + deviceId + "] \n" +
                "HeaderIn.[" + headerIn + "] \n" +
                "HeaderOut.[" + headerOut + "] \n" +
                "Request.[" + request + "] \n" +
                "RequestId.[" + requestId + "] \n" +
                "SessionId.[" + sessionId + "] \n" +
                "TimeEnd.[" + timeEnd + "] \n" +
                "TimeStart.[" + timeStart + "] \n" +
                "UserAddress.[" + userAddress + "] \n" +
                "UserId.[" + userId + "]. \n" ;
                //"UserAccessDTO end************* \n" ;
        
        return result;
    }
}
