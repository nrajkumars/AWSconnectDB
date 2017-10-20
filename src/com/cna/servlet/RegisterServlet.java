package com.cna.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 
        String empID = request.getParameter("userempid");
    
        out.println(" AWS  -- Emp ID got  from UI is = "+empID);
        String errormessage ="";
        int restultCount = 0;
		 try {
     		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	    String url = "jdbc:sqlserver://rajserver.database.windows.net:1433;database=rajDB";
	    	    Connection conn = DriverManager.getConnection(url, "raj@rajserver", "Tcs12345");
	    	    Statement stmt=conn.createStatement();
	    	    out.println("<BR><BR> DB  Connection established successfully...!!");   
	    	    	    	         	    	    
	            ResultSet rs=stmt.executeQuery("Select * from TCSEmp where EMPID ="+empID);
	         
	            out.println("<BR><BR>Select Query ran successfully...!!"); 
	            
	             out.println("<BR><BR><table border=1>");
	             while(rs.next())  {
	                	 out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td>"+rs.getString(3)+"</td>"+
	                                      "<td>"+rs.getString(4)+"</td></tr>");
	                	 restultCount = restultCount+1;
	             }
	             out.println("</table>");
	             
	    	     conn.close();
	      	} 
	      	catch(Exception e){
	      		errormessage = e.toString();
	      		System.out.println(e);
	      		out.println("<h1> Error: " + errormessage);
	    	   }
	      
    if(restultCount<1){
 	   out.print("<BR><BR>...Employee  NOT Found..............");
    }
    
    out.close();
	}

}
