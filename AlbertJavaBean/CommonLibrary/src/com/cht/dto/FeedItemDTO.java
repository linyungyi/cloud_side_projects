/**
 * 
 */
package com.cht.dto;

/**
 * @author alberltin
 *
 */
public class FeedItemDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Id;
	private String Type;
	private long LastModified;
	private int SizeInBytes;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public long getLastModified() {
		return LastModified;
	}
	public void setLastModified(long lastModified) {
		LastModified = lastModified;
	}
	public int getSizeInBytes() {
		return SizeInBytes;
	}
	public void setSizeInBytes(int sizeInBytes) {
		SizeInBytes = sizeInBytes;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
    public String toString() {
   
        String result = 
        		//"HostAddress.[" + super.HostAddress + "] \n" +
        		"Id.[" + Id + "] " +
                "LastModified.[" + LastModified + "] " +
                "SizeInBytes.[" + SizeInBytes + "] " +
                "Type.[" + Type + "] " ;
        
        return result;
    }
	
}
