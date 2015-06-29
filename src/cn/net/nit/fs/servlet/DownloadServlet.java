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
		if (user == null) {  // ��δ��½��������ת����¼ҳ��
			response.setContentType("text/html;charset=GBK");
			response.setHeader("Refresh", "3; URL=login.htm");
			PrintWriter out = response.getWriter();
			out.println("<FONT color='red'>���ȵ�¼���������ļ���</FONT><BR>");
			out.println("<FONT color='blue'>3�����ת����¼ҳ��...</FONT>");
			out.close();
			return;
		}

		// ���з����������ļ��ı���·��
		String FILE_ROOT = "D:/temp/test";
		String fileId = request.getParameter("fileId");
		String fileName = fileId + ".pdf";
		
		File file = new File(FILE_ROOT, fileName);
		if (!file.exists()) {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out = response.getWriter();
			out.println("<FONT color='red'>�ļ������ڣ�</FONT>");
			out.close();
			return;
		}
		openFile(file, response);
	}
	
	/**
	 * ��ָ���ļ��������Ӧ
	 * @param file Ҫ������ͻ��˵��ļ�����
	 * @param response ��Ӧ����
	 */
	public void openFile(File file, HttpServletResponse response) {
    	int MAX_BUFFER_SIZE = 4096;
    	String contentType = "application/octet-stream";
    	try {
		if (file==null || !file.exists()) return;
		// ���÷�������
		response.setContentType(contentType);
		response.setContentLength((int)file.length());
		// ����������д��ĵ��������鱣��ʱ���ļ���
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
