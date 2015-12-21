package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/addHotel")
@MultipartConfig(fileSizeThreshold=1024*1024*2, 
				maxFileSize=1024*1024*10,
				maxRequestSize=1024*1024*50)
public class AddHotelController extends HttpServlet{


	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String savePath = request.getServletContext().getRealPath("")+ File.separator + "Hotels";
		ArrayList<String> imagesLocation = new ArrayList<>();
		String hotelName = "";
		int images =1;
		HttpSession session = request.getSession();
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try 
		{
			items = upload.parseRequest(request);
		} 
		catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator<FileItem> itr = items.iterator();
		while (itr.hasNext()) 
		{
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) {
				if(item.getFieldName().equals("totalCount"))
					session.setAttribute(item.getFieldName(),Integer.parseInt(item.getString()));
				session.setAttribute(item.getFieldName(),item.getString());
			} 
			else 
			{
				try 
				{
						hotelName = session.getAttribute("hotelName").toString().replaceAll("\\s","").replaceAll("\\W", "");
						new File(savePath+File.separator+hotelName).mkdir();
						item.write(new File(savePath+File.separator+hotelName+File.separator+"image"+images+".jpg"));
						imagesLocation.add("Hotels"+File.separator+session.getAttribute("hotelName").toString().
									replaceAll("\\s","").replaceAll("\\W", "")+File.separator+"image"+images+".jpg");
						images++;
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		session.setAttribute("hotelImages", imagesLocation);
		response.sendRedirect("/addRooms.jsp");
	}
}
