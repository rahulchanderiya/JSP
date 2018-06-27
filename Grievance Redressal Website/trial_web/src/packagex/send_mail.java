package packagex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class send_mail
 */
@WebServlet("/send_mail")
public class send_mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public send_mail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("inside send_mail");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
					
			database d = new database();
			int count = d.count.municipal_count() + 1000;
			String municipal_id = "mun-" + Integer.toString(count);
			String municipal_pass = name + "@" + Integer.toString(count);
			
			Email mail = new Email();
			String from = "dbms11project@gmail.com";
			String pass = "rahul!123";
			String[] to = { email };
			String subject = "Griveance Redresal System";
			String body = "Your Account Successfully created,   Login Id = " + municipal_id + " ,  Pass = " + municipal_pass;;
			mail.sendmail.sendFromGMail(from, pass, to, subject, body);
			
			d.municipal.insert_data(name, municipal_id, municipal_pass);
			
			request.setAttribute("error", "Message Succesfully Sent");
			request.setAttribute("link", "admin_homepage.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
					
			return;
		}
		catch (Exception exc) {
			request.setAttribute("error", "Kindly Re-Check Email Address");
			request.setAttribute("link", "admin_homepage.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
					
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
