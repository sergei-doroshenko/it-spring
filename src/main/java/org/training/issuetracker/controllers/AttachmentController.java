package org.training.issuetracker.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;

@Controller
//@MultipartConfig
@RequestMapping("/attachment")
public class AttachmentController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private IssueDAO issueDAO;
	
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public RedirectView addAttachment (@RequestParam("file") MultipartFile file,
			@PathVariable long id, HttpSession session) throws DaoException, IOException, ServletException {	

		// constructs path of the directory to save uploaded file
		String uploadFilePath = Constants.getRealPath() + File.separator
        		+ Constants.URL_UPLOAD_DIR + File.separator + id;
        File fileUploadDir = new File(uploadFilePath);
        if (!fileUploadDir.exists()) {
            fileUploadDir.mkdirs();
        }
        
        Issue issue = (Issue) issueDAO.getIssueById(id);
        
		if (issue == null) {
			return new RedirectView("/index.jsp", false);
		}
		
		User user = (User) session.getAttribute(Constants.KEY_USER);
        java.util.Date date = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		if (!file.isEmpty()) {
			String name = file.getOriginalFilename();
			
			Attachment attch = new Attachment();
        	attch.setCreateBy(user);
        	attch.setCreateDate(currentDate);
        	attch.setIssueId(id);
        	attch.setFileName(name);
        	attachmentDAO.insertAttachment(attch);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(
            		new FileOutputStream(
            				new File(uploadFilePath + File.separator + name)));
            stream.write(bytes);
            stream.close();
        } else {
            return new RedirectView("/issuetracker/index.jsp", false);
        }

        return new RedirectView("/issuetracker/issue/edit?id=" + id, false);
	}
	
	@RequestMapping(value="/{attachmentId}", method = RequestMethod.GET)
	public RedirectView getAttachment (@PathVariable long attachmentId, OutputStream out) throws DaoException, IOException {	
		
		Attachment attachment = attachmentDAO.getAttchment(attachmentId);
		
		long issueId = attachment.getIssueId();
		
		InputStream fis = new FileInputStream(attachment.getFile());
		
		byte[] bufferData = new byte[1024];
		int read=0;

		while((read = fis.read(bufferData))!= -1){
			out.write(bufferData, 0, read);
		}

		out.flush();
		out.close();
		fis.close();
		
        return new RedirectView("/issuetracker/issue/edit?id=" + issueId, false);
	}
	
	@RequestMapping(value="/del/{attachmentId}", method = RequestMethod.GET)
	public RedirectView removeAttachment(@PathVariable long attachmentId) throws DaoException, FileNotFoundException {
		
		Attachment attachment = attachmentDAO.getAttchment(attachmentId);
		
		long issueId = attachment.getIssueId();
		
		File file = attachment.getFile();
		file.delete();
		
		attachmentDAO.deleteAttachment(attachmentId);
		
		return new RedirectView("/issuetracker/issue/edit?id=" + issueId, false);
	}
	
    @ExceptionHandler(DaoException.class)
	public String handleDaoException(DaoException ex, ModelMap model) {
    	model.addAttribute("error", ex.getMessage());
		ex.printStackTrace();
		return "error";
	}
    
    @ExceptionHandler(IOException.class)
	public String handleIOException(IOException ex, ModelMap model) {
    	model.addAttribute("error", ex.getMessage());
		ex.printStackTrace();
		return "error";
	}
    
    @ExceptionHandler(ServletException.class)
	public String handleServletException(ServletException ex, ModelMap model) {
    	model.addAttribute("error", ex.getMessage());
		ex.printStackTrace();
		return "error";
	}
}
