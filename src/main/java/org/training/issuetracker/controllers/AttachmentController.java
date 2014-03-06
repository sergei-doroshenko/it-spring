package org.training.issuetracker.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;

@Controller
@MultipartConfig
@RequestMapping("/attachment")
public class AttachmentController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private IssueDAO issueDAO;
	
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
//	public String addAttachment (@RequestPart("file-data") MultipartFile file, 
//			@RequestParam long id, HttpSession session) throws DaoException {
//	file.getInputStream();	 (value="file", required=false)
	public RedirectView addAttachment (@RequestParam("file") MultipartFile file,
			@PathVariable long id, HttpSession session) throws DaoException, IOException, ServletException {	
		
		logger.warn("Attachment id ==========================+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++===============" + id);

		
		// constructs path of the directory to save uploaded file
		String uploadFilePath = Constants.getRealPath() + File.separator
        		+ Constants.URL_UPLOAD_DIR + File.separator + id;
        File fileUploadDir = new File(uploadFilePath);
        if (!fileUploadDir.exists()) {
            fileUploadDir.mkdirs();
        }
        logger.warn("File Path --------------------------------------------------" + uploadFilePath);
        
        Issue issue = (Issue) issueDAO.getIssueById(id);

		if (issue == null) {
			return new RedirectView("/index.jsp", false);
		}
		
		if (!file.isEmpty()) {
				String name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                		new FileOutputStream(
                				new File(uploadFilePath + File.separator + name)));
                stream.write(bytes);
                stream.close();
                
            
        } else {
            return new RedirectView("/issuetracker/index.jsp", false);
        }
		
		
		//		String string = file1.getName();
		
        // Process upload file
       
        	
	        // creates the save directory if it does not exists
//	        File fileUploadDir = new File(uploadFilePath);
//	        if (!fileUploadDir.exists()) {
//	            fileUploadDir.mkdirs();
//	        }
//
//	        User user = (User) session.getAttribute(Constants.KEY_USER);
//	        java.util.Date date = new java.util.Date();
//	        java.sql.Date currentDate = new java.sql.Date(date.getTime());
//	        
//	        logger.warn("Request parts --------------------------------------------------" + request.getParts());
//	        
//	        //Get all the parts from request and write it to the file on server
//	        for (Part part : request.getParts()) {
//	        	
//	        	fileName = getFileName(part);
//	        	Attachment attch = new Attachment();
//		    	attch.setCreateBy(user);
//		    	attch.setCreateDate(currentDate);
////		    	attch.setIssueId(id);
//		    	attch.setFileName(fileName);
//
//				attachmentDAO.insertAttachment(attch);
//				part.write(uploadFilePath + File.separator + fileName);
//	        }
	        
	        
//			file.transferTo(new File(uploadFilePath + File.separator + fileName));
//			
	        
        
        return new RedirectView("/issuetracker/issue/edit?id=" + id, false);//"WEB-INF/views
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
