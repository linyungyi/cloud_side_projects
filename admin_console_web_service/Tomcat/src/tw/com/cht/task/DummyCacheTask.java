package tw.com.cht.task;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;

import tw.com.cht.core.CachingAbsCore;

public class DummyCacheTask extends CachingAbsCore {

	public DummyCacheTask() {
		// TODO Auto-generated constructor stub
	}
	
	/* 
	 * to do criteria to reset cache here. 
	 * use writeIntoMemory(null) to clear cache source in the memory; 
	 * use writeIntoDisk(null) to clear cache file in the disk
	 */
	@Override
	protected void criteriaOfCache() {
		// TODO Auto-generated method stub
		writeIntoMemory(null); // to clear cache source in the memory
	}
	
	/* to do pull data from data source here*/
	@Override
	protected Source pullDataSource(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* sourceUri = ?. redefine file name of cache file. leave it empty to use default file name. default file name is Task name. */
	@Override
	protected void setSourceUri() {
		// TODO Auto-generated method stub

	}

}
