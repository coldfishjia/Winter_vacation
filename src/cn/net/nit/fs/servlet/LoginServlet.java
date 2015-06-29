package cn.net.nit.fs.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import cn.net.nit.fs.model.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if ("logout".equals(cmd)) {  // ����ע����½
			HttpSession session = request.getSession(false);
			if (session != null) session.invalidate();
			response.setContentType("text/html;charset=GBK");
			response.setHeader("Refresh", "3; URL=login.htm");
			PrintWriter out = response.getWriter();
			out.println("<FONT color='blue'>���ѳɹ��˳���¼��</FONT><BR>");
			out.println("3�����ת����¼ҳ��...");
			out.close();
			return;
		} else if ("register".equals(cmd)) {  // ����ע�����û�
			register(request, response);
			return;
		}

		String loginUrl = "/login.htm";
		String fileUrl = "/filelist.htm";
		HttpSession session = request.getSession();
		String userName = request.getParameter("user_name");
		String password = request.getParameter("login_password");
		// ��֤�û�ID�������Ƿ�Ϸ�����...
		ServletContext context = getServletContext();
		if (userName == null || userName.equals("")) {
			RequestDispatcher dispatcher = context.getRequestDispatcher(loginUrl);
			dispatcher.forward(request, response);
			return;
		} else if (userName.equals("1")) {
			User user = new User();
			user.setUserName(userName);
			session.setAttribute("LoginUser", user);
			RequestDispatcher dispatcher = context.getRequestDispatcher(fileUrl);
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = context.getRequestDispatcher(loginUrl);
			dispatcher.forward(request, response);
			return;
		}
		// ��¼�ɹ�������session�е�����
		

	}
	
	/**
	 * �����û�ע��
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String strSex = request.getParameter("sex");
		char sex = strSex.charAt(0);
		String password = request.getParameter("password");
		String password2 = request.getParameter("retype_password");
		String grade = request.getParameter("grade");
		String cell = request.getParameter("cell");
		String email = request.getParameter("email");
		// ��֤���������ݺϷ�����...
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setSex(sex);
		user.setPassword(password);
		user.setGrade(grade);
		user.setEmail(email);
		user.setCell(cell);
		// �������ݵ����ݿ�...
		// ע��ɹ���ת���ļ��б�ҳ��
		String fileUrl = "/filelist.htm";
		request.getSession().setAttribute("User", user);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(fileUrl);
		dispatcher.forward(request, response);
	}

}
