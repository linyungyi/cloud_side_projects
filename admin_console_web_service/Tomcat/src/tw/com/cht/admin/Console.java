package tw.com.cht.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.cht.xslt.XslProcessorServlet;

/**
 * Servlet implementation class Console
 */
public class Console extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Console() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd=request.getParameter("command");
		if(cmd!=null)
		{
			XslProcessorServlet t=new XslProcessorServlet(); 
			if(cmd.compareTo("reloadConfig")==0)
				t.LoadConfig();
			else if(cmd.compareTo("reloadTask")==0)
				t.LoadTaskList();
			else if(cmd.compareTo("stopConfig")==0)
				t.StopConfig();
			else if(cmd.compareTo("stopTask")==0)
				t.StopTaskList();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
