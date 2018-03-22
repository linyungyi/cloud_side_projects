/**
 * 
 */
package com.cht.dto;

import java.util.Iterator;

/**
 * @author alberltin
 *
 */
public class FeedItemCollectionDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;
	private String FeedId;
	private boolean Delta;
	private Iterator iterator;
	
	public boolean isDelta() {
		return Delta;
	}

	public void setDelta(boolean delta) {
		Delta = delta;
	}

	public String getFeedId() {
		return FeedId;
	}

	public void setFeedId(String feedId) {
		FeedId = feedId;
	}

	public Iterator getIterator() {
		return iterator;
	}

	public void setIterator(Iterator iterator) {
		this.iterator = iterator;
	}

    public String toString() {
 
        String result = 
        		//"HostAddress.[" + super.HostAddress + "] \n" +
        		"Delta.[" + Delta + "] \n" +
                "FeedId.[" + FeedId + "] \n" ;
        if(iterator!=null){
            while(iterator.hasNext()){
            	FeedItemDTO fi=(FeedItemDTO)iterator.next();
            	result+="FeedItem.{ " + fi.toString() + "} \n";
            }        	
        }
        
        return result;
    }
}
