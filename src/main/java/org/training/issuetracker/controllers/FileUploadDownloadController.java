package org.training.issuetracker.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.AttachmentDAO;

import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.i18n.Localizer;
import org.training.issuetracker.i18n.LocalizerFactory;
import org.training.issuetracker.utils.ParameterParser;

/**
 * Servlet implementation class FileUploadController
 */
@WebServlet(value = "/FileUploadDownload.do")
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB
				maxFileSize=1024*1024*50,          // 50 MB
				maxRequestSize=1024*1024*100)      // 100 MB
public class FileUploadDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger("org.training.issuetracker.controllers");
	
	private AttachmentDAO attachmentDAO;
	private IssueDAO issueDAO;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fileName = request.getParameter("fileName");

		if(fileName == null || fileName.equals("")) {
			throw new ServletException("File Name can't be null or empty");
		}

//		String path = Constants.getRealPath() + Constants.URL_UPLOAD_DIR
//				+ File.separator + issue.getId() + File.separator + fileName;

		String path = Constants.getRealPath() + Constants.URL_UPLOAD_DIR
				+  fileName;

		logger.debug("Download file path = " + path);

		File file = new File(path);

		if(!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}

		logger.debug("File location on server::"+file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read=0;

		while((read = fis.read(bufferData))!= -1){
			os.write(bufferData, 0, read);
		}

		os.flush();
		os.close();
		fis.close();
		logger.info("File downloaded at client successfully");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Locale locale = (Locale) session.getAttribute(Constants.KEY_LOCALE);
		Localizer localizer = LocalizerFactory.getLocalizer(locale);
		logger.info(request.getLocale().getLanguage());

		ResourceBundle bundle = localizer.getBundle("errors");
		logger.debug(bundle.getString("issue.err.null"));

		ParameterParser parser = new ParameterParser(request);
		String fileName = null;

		try {
			long issueId = parser.getLongParameter(Constants.KEY_ID);

			Issue issue = issueDAO.getIssueById(issueId);

			if (issue == null) {
				return;
			}

			// constructs path of the directory to save uploaded file
	        String uploadFilePath = Constants.getRealPath() + File.separator
	        		+ Constants.URL_UPLOAD_DIR + File.separator + issueId;

	        // creates the save directory if it does not exists
	        File fileUploadDir = new File(uploadFilePath);
	        if (!fileUploadDir.exists()) {
	            fileUploadDir.mkdirs();
	        }

	        User user = (User) session.getAttribute(Constants.KEY_USER);
	        java.util.Date date = new java.util.Date();
	        java.sql.Date currentDate = new java.sql.Date(date.getTime());


	        logger.info("Upload File Directory="+fileUploadDir.getAbsolutePath());
	       

	        //Get all the parts from request and write it to the file on server
	        for (Part part : request.getParts()) {

	            fileName = getFileName(part);

	            Attachment attch = new Attachment();
	        	attch.setCreateBy(user);
	        	attch.setCreateDate(currentDate);
	        	attch.setIssueId(issueId);
	        	attch.setUrl(fileName);

				attachmentDAO.addAttchment(attch);
				part.write(uploadFilePath + File.separator + fileName);

	        }

	        request.setAttribute("uploadmessage", fileName + " File uploaded successfully!");
	        request.setAttribute(Constants.ISSUE, issue);
	        getServletContext().getRequestDispatcher(Constants.URL_EDIT_ISSUE).forward(request, response);

		} catch (ParameterNotFoundException e) {

			e.printStackTrace();
			getServletContext().getRequestDispatcher(Constants.URL_ERROR).forward(request, response);
		} catch (DaoException e) {
			e.printStackTrace();
			request.setAttribute("errormessage", fileName + " File upload fail!");
			getServletContext().getRequestDispatcher(Constants.URL_ERROR).forward(request, response);
		}

	}

	/**
     * Method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
//  out.write("File "+fileItem.getName()+ " uploaded successfully.");
//	out.write("<br>");
//	out.write("<a href=\"UploadDownloadFileServlet?fileName="+fileItem.getName()+"\">Download "+fileItem.getName()+"</a>");

}
