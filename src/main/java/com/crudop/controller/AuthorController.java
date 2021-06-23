package com.crudop.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.crudop.dao.AuthorDAO;
import com.crudop.model.Author;
import com.crudop.model.Message;
import com.crudop.util.DBCon;
import com.crudop.util.Helper;

@MultipartConfig
@WebServlet(name="AuthorController", urlPatterns= {"/author"})
public class AuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthorDAO authorDao; 
	
	public void init() {
		this.authorDao = new AuthorDAO(DBCon.getConnection());
	}
	
	public AuthorController() {
        super();
	}
	
	protected void processRequset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String qrstr = request.getParameter("_a");
			switch(qrstr) {
				case "index":
					index(request,response);
					break;
				case "store":
					store(request,response);
					break;
				case "update":
					update(request,response);
					break;
				case "delete":
					delete(request,response);
					break;
				default:
					index(request,response);
					break;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequset(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequset(request, response);
	}
	
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Author> listOfAuthor = authorDao.getAll();
		
		// for edit
		String title = "Create an Author";
		boolean isEdit = false;
		int id = 0;
		Author eauthor = new Author();
		String formUrl = request.getContextPath()+"/author?_a=store";
		String edit = request.getParameter("e");
		if(edit != null && edit.length() != 0) {
			id = Integer.parseInt(edit);
			try {
				eauthor = authorDao.getOne(id);
				title = "Edit Author";
				if(eauthor == null) {
					new Message("Author not exist!","Error","alert-danger");
				}else {
					isEdit = true;
					formUrl = request.getContextPath()+"/author?_a=update";
				}
			}catch(Exception e) {
				new Message("Something went wrong!","Error","alert-danger");
			}
		}
		// end of edit
		
		LinkedList<String> qualiList = new LinkedList<String>(Arrays.asList("SSC","HSC","GRADUATED"));
		
		request.setAttribute("authors", listOfAuthor);
		request.setAttribute("title", title);
		request.setAttribute("isEdit", isEdit);
		request.setAttribute("eauthor", eauthor);
		request.setAttribute("qualiList", qualiList);
		request.setAttribute("formUrl", formUrl);
		
		RequestDispatcher rs = request.getRequestDispatcher("/author.jsp");
		rs.forward(request, response);
	}
	
	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String qualification = request.getParameter("qualification");
		String profile = "default.png";
		String createdat = Helper.getCurrentTimestamp();
		
		/*save file image data*/
        Part part = request.getPart("profile");
        if(part.getSize()>0){
            profile = Helper.getRandomString() + ".png";
            String path = getServletContext().getRealPath("/upload/profile") + File.separator + profile;
            Helper.saveFile(part, path);
        }
        /*end of save file image data*/
		
		Author author = new Author(username, email, phone, qualification, profile, createdat);
		boolean status = authorDao.store(author);
		
		HttpSession session = request.getSession();
		Message msg;
		if(status == true) {
			msg = new Message("Author saved Successfully.","Success","bg-success");
		}else {
			msg = new Message("Something went wrong.","Error","bg-danger");
		}
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String qualification = request.getParameter("qualification");
		String profile = "default.png";
		String createdat = Helper.getCurrentTimestamp();
		String oldFile = request.getParameter("oldFile");//oldFile is profile
		
		Author author = new Author(authorId,username, email, phone, qualification, profile, createdat);
		Message msg;
		Part part = request.getPart("profile");
        String imageName = Helper.getRandomString() + ".png";
        
        if (part.getSize() > 0) {
            author.setProfile(imageName);
        }
        
        try{
            if (part.getSize() > 0) {
                String path = getServletContext().getRealPath("/upload/profile") + File.separator + imageName;
                String oldFilePath = getServletContext().getRealPath("/upload/profile") + File.separator + oldFile;

                if (!oldFile.equals("default.png")) {
                    Helper.deleteFile(oldFilePath);
                }

                if (Helper.saveFile(part, path)) {
                    msg = new Message("Profile details and Profile picture Updated...", "success", "alert-success");
                }
            } else {
                msg = new Message("Profile details Updated...", "success", "alert-success");
            }
        } catch (Exception e) {
            msg = new Message(e.getMessage(), "danger", "alert-danger");
        }
        
		boolean status = authorDao.update(author);
		
		HttpSession session = request.getSession();
		
		if(status == true) {
			msg = new Message("Author update Successfully.","Success","bg-success");
		}else {
			msg = new Message("Something went wrong.","Error","alert-danger");
		}
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		
		boolean status = authorDao.delete(authorId);
		
		HttpSession session = request.getSession();
		Message msg;
		if(status == true) {
			msg = new Message("Author delete Successfully.","Success","bg-success");
		}else {
			msg = new Message("Something went wrong.","Error","bg-danger");
		}
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}


}
