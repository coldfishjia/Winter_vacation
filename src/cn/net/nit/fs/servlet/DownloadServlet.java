package cn.net.nit.fs.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import cn.net.nit.fs.model.*;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("LoginUser");
		if (user == null) {  // 若未登陆，则先跳转到登录页面
			response.setContentType("text/html;charset=GBK");
			response.setHeader("Refresh", "3; URL=login.htm");
			PrintWriter out = response.getWriter();
			out.println("<FONT color='red'>请先登录后再下载文件！</FONT><BR>");
			out.println("<FONT color='blue'>3秒后跳转到登录页面...</FONT>");
			out.close();
			return;
		}

		// 所有服务器共享文件的保存路径
		String FILE_ROOT = "D:/temp/test";
		String fileId = request.getParameter("fileId");
		String fileName = fileId + ".pdf";
		
		File file = new File(FILE_ROOT, fileName);
		if (!file.exists()) {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out = response.getWriter();
			out.println("<FONT color='red'>文件不存在！</FONT>");
			out.close();
			return;
		}
		openFile(file, response);
	}
	
	/**
	 * 将指定文件输出到响应
	 * @param file 要输出到客户端的文件对象
	 * @param response 响应对象
	 */
	public void openFile(File file, HttpServletResponse response) {
    	int MAX_BUFFER_SIZE = 4096;
    	String contentType = "application/octet-stream";
    	try {
		if (file==null || !file.exists()) return;
		// 设置返回类型
		response.setContentType(contentType);
		response.setContentLength((int)file.length());
		// 不在浏览器中打开文档，并建议保存时的文件名
	    String encodeName = new String(file.getName().getBytes(), "ISO-8859-1");
	    response.setHeader("Content-disposition", "attachment;filename=" + encodeName);
	    BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
	    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
	    byte[] buffer = new byte[MAX_BUFFER_SIZE];
	    int read = 0;
		while ((read = input.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		input.close();
		out.close();
		response.setStatus(HttpServletResponse.SC_OK);
		response.flushBuffer();
	} catch (FileNotFoundException e) {
		System.err.println(e);
	} catch (Exception e) {
		System.err.println(e);
	}
}


}
