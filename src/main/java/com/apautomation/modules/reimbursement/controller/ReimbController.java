package com.apautomation.modules.reimbursement.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apautomation.blob.AzureBlobService;
import com.apautomation.modules.employee.exception.NotFoundException;
import com.apautomation.modules.employee.repository.DeptRepository;
import com.apautomation.modules.employee.repository.UserRepository;
import com.apautomation.modules.reimbursement.model.ReimbursementInfo;
import com.apautomation.modules.reimbursement.model.ReimbursementInfoList;
import com.apautomation.modules.reimbursement.model.ReimbursementInfoWoutImg;
import com.apautomation.modules.reimbursement.repository.ReimbRepository;
import com.apautomation.utils.ImageUtils;

import jakarta.servlet.http.HttpServletResponse;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin("https://apautomation-backend.azurewebsites.net")
@RestController
@RequestMapping("/api/transaction")
public class ReimbController {
	
	@Autowired
	private ReimbRepository reimbRepository;
	
	@Autowired
	private AzureBlobService azureBlobAdapter;
	
	@Autowired
	private DeptRepository deptRepository;
	
	@PostMapping("/reimb/save")
	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute ReimbursementInfo model) {
	    try {
	    	String fileExt = model.getFilename().substring(model.getFilename().indexOf(".") , model.getFilename().length());
	    	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    	String newFilename = model.getFilename().substring(0, model.getFilename().indexOf("."))+"_"+timeStamp + fileExt;
	    	String approver = deptRepository.findApproverByDept(model.getDepartment());
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    	LocalDateTime now = LocalDateTime.now();
	    	
	    	model.setApprovalDates("");
	    	model.setApprovalHistory("");
	    	model.setApprover("finance");
	    	model.setStatus(1);
	    	model.setFilename(newFilename);
	    	model.setImageData(ImageUtils.compressImage(model.getFile().getBytes()));
	    	model.setApprovalRemarks(model.getUsername()+"~"+model.getRemarks());
	    	model.setApprovalDates(dtf.format(now));
	    	model.setApprovalHistory(""+1);
	    	model.setApproversHistory(model.getUsername());
	    	reimbRepository.save(model);
	    	//azureBlobAdapter.upload(model.getFile(), newFilename);
	    	
	    } catch (Exception e) {
	        return new ResponseEntity("Error encountered while saving data. Please contact your administrator.", HttpStatus.BAD_REQUEST);
	    }

	    return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}
	
	@PostMapping("/reimb/approve")
	public ResponseEntity<?> approveReimb(@RequestBody ReimbursementInfo model) {
	    try {
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    	LocalDateTime now = LocalDateTime.now();
	    	String approvalDates = model.getApprovalDates()+","+dtf.format(now);
	    	String approvalHistory = model.getApprovalHistory()+","+2;
	    	String approvalRemarks = model.getApprovalRemarks()+"@^"+model.getRemarks();
	    	String approversHistory = model.getApproversHistory()+","+ model.getApprover();
	    	
	    	reimbRepository.updateReimbApprovalInfo(2, approvalDates, approvalHistory, 
	    			approvalRemarks, approversHistory, model.getId());
	    	
	    } catch (Exception ex) {
	        return new ResponseEntity("Error encountered while saving data. Please contact your administrator. "+ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }

	    return new ResponseEntity("Successfully updated!", HttpStatus.OK);
	}
	
	@GetMapping("/reimb/getAll")
	List<ReimbursementInfo> getReimbursementInfo() {
		try {
			List<ReimbursementInfo> reimbInfo = reimbRepository.findAllReimbInfo();
			
			for(int i = 0; i < reimbInfo.size(); i++) { 
				String statusDesc = convertStatus(reimbInfo.get(i).getStatus());
				reimbInfo.get(i).setStatusDesc(statusDesc); 
			}
  
			return reimbInfo;
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
	 
	  @GetMapping("/reimb/getHistoryByUsername/{username}") 
	  List<ReimbursementInfo> getHistoryByUsername(@PathVariable String username) { 
		  try {
			  List<ReimbursementInfo> reimbInfo = reimbRepository.findAllByUsername(username);
	  
			  for(int i = 0; i < reimbInfo.size(); i++) { 
				  String statusDesc = convertStatus(reimbInfo.get(i).getStatus());
				  reimbInfo.get(i).setStatusDesc(statusDesc); 
			  }
	  
			  return reimbInfo; 
		  }catch(Exception ex) { 
			  System.out.println(ex); 
		  } 
		  return null; 
	  }
	  
	  @GetMapping("/reimb/getApprovals") 
	  List<ReimbursementInfo> getApprovals() { 
		  try {
			  List<ReimbursementInfo> reimbInfo = reimbRepository.findAllPendingForApproval();
	  
			  for(int i = 0; i < reimbInfo.size(); i++) { 
				  String statusDesc = convertStatus(reimbInfo.get(i).getStatus());
				  reimbInfo.get(i).setStatusDesc(statusDesc); 
			  }
	  
			  return reimbInfo; }
		  catch(Exception ex) { 
			  System.out.println(ex); } 
		  return null; 
	  }
	
	  @GetMapping("/reimb/getById/{id}")
	  public ResponseEntity<?> getApprovalById(@PathVariable Long id) {
		  Map<String, Object> map = new LinkedHashMap<String, Object>();
		  
		  try { 
			  ReimbursementInfo reimbDetails = reimbRepository.findById(id).get();
			  map.put("status", 1); 
			  map.put("data", reimbDetails); 
			  map.put("b64img",ImageUtils.decompressImage(reimbDetails.getImageData())); 
			  return new ResponseEntity<>(map, HttpStatus.OK); 
		  } catch (Exception ex) { 
			  map.clear(); 
			  map.put("status", 0); 
			  map.put("message", "Data is not found"); 
			  return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		  }
	  }	
	  
	  private String convertStatus(int status) {
		  
		  String statusDesc = status == 2 ? "Approved" : status == 1 ? "Pending" : status == 4 ? "Cancelled" : "Declined"; 
		  
		  return statusDesc;
	  }
	  
	/*
	 * @GetMapping("/export-to-excel") public void
	 * exportIntoExcelFile(HttpServletResponse response) throws IOException {
	 * response.setContentType("application/octet-stream"); DateFormat dateFormatter
	 * = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
	 * dateFormatter.format(new Date());
	 * 
	 * String headerKey = "Content-Disposition"; String headerValue =
	 * "attachment; filename=student" + currentDateTime + ".xlsx";
	 * response.setHeader(headerKey, headerValue);
	 * 
	 * List <Student> listOfStudents = studentService.getTheListStudent();
	 * ExcelGenerator generator = new ExcelGenerator(listOfStudents);
	 * generator.generateExcelFile(response); }
	 */
}
