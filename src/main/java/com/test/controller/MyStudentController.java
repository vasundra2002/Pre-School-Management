package com.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.test.model.MyStudent;
import com.test.repository.MyStudentRepository;
import com.test.service.FilesStorageService;
import com.test.service.MyStudentsDetailsService;
import com.test.upload.ResponseMessage;

import jakarta.validation.Valid;

//@Controller
//@ResponseBody
//@RestController = @Controller + @ResponseBody

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class MyStudentController {
	@Autowired
	private MyStudentsDetailsService myStudentsDetailsService;
	
	@Autowired
	private MyStudentRepository myStudentRepository;
	
	@Autowired
	private FilesStorageService filesStorageService;
	
	//@RequestMapping(path="/myStudent", method = RequestMethod.POST) //localhost:8080/myStudent
		@PostMapping("/mystudent")
		public MyStudent saveMyStudentRecord(@Valid @RequestBody MyStudent myStudent) //request handler method
		{
			return this.myStudentsDetailsService.addMyStudent(myStudent);
		}
		
		
		
		@RequestMapping(path="/mystudent/{id}",method = RequestMethod.GET)
	    
	    public ResponseEntity<?> getMyStudent(@PathVariable long id)
	    {
	        if(this.myStudentRepository.findById(id).isPresent())
	        {
	            return new ResponseEntity(this.myStudentsDetailsService.getMyStudentById(id),HttpStatus.OK);
	        }
	        else
	        {
	            return new ResponseEntity("Student Id not found!!",HttpStatus.NOT_FOUND);
	        }
	    }

		@GetMapping("/mystudents")
		//@RequestMapping(path="/mystudents",method = RequestMethod.GET)
		public List<MyStudent> getMyStudents()
		{
			return this.myStudentsDetailsService.getAllMyStudents();
		}
		
		@DeleteMapping(path = "/mystudent/{id}")
	    public ResponseEntity<HashMap> deleteMyStudent(@PathVariable("id") long mystuid)   
	    {
	        HashMap<String, String> response=new HashMap<>(); 
	        
	        if(myStudentRepository.findById(mystuid).isPresent())
	        {
	        	myStudentsDetailsService.deleteMyStudentById(mystuid);
	            response.put("message", "Record deleted successfully!!");
	            return new ResponseEntity<HashMap>(response, HttpStatus.OK);
	        }
	        else
	        {
	            response.put("message", "Invalid student id!!");
	            return new ResponseEntity<HashMap>(response, HttpStatus.NOT_FOUND);
	        }
	    }
		

		@PutMapping("/mystudent/{id}")

	    public ResponseEntity<HashMap> updateMyStudent(@RequestBody MyStudent myStudent,@PathVariable("id")long id)
	    {
			 HashMap<String , String> response=new HashMap<>();
		        if(myStudentRepository.findById(id).isPresent())
	        {
	            MyStudent existingMyStu=myStudentRepository.findById(id).get();
	            
	            if(myStudent.getName()!=null)
	            {
	                existingMyStu.setName(myStudent.getName());
	            }
	             if(myStudent.getStandard()!=null)
	            {
	            	 existingMyStu.setStandard(myStudent.getStandard());
	            }
	             if(myStudent.getEmail()!=null)
	            {
	            	 existingMyStu.setEmail(myStudent.getEmail());
	            }
	             if(myStudent.getAge()!=0.0)
	            {
	            	 existingMyStu.setAge(myStudent.getAge());
	            }
	             if(myStudent.getPhonenumber()!=0.0)
		            {
		            	 existingMyStu.setPhonenumber(myStudent.getPhonenumber());
		            }
	            myStudentsDetailsService.updateMyStudent(existingMyStu);
	            
	            response.put("message", "MyStudent information updated!");
	            return new ResponseEntity<HashMap>(response, HttpStatus.OK);
	            
	        }
	        else
	        {
	            response.put("message", "Invalid Student id!");
	            return new ResponseEntity<HashMap>(response, HttpStatus.NOT_FOUND);
	        }
	        
	    }
		
		@PatchMapping("/mystudent/{id}")
	    public ResponseEntity<HashMap> updateMyStudentById(@Valid @RequestBody MyStudent myStudent,@PathVariable("id")long id)
	    {
			 HashMap<String , String> response=new HashMap<>();
		        if(myStudentRepository.findById(id).isPresent())
	        {
	            MyStudent existingMyStu=myStudentRepository.findById(id).get();
	            
	            if(myStudent.getName()!=null)
	            {
	                existingMyStu.setName(myStudent.getName());
	            }
	             if(myStudent.getStandard()!=null)
	            {
	            	 existingMyStu.setStandard(myStudent.getStandard());
	            }
	             if(myStudent.getEmail()!=null)
	            {
	            	 existingMyStu.setEmail(myStudent.getEmail());
	            }
	             if(myStudent.getAge()!=0.0)
	            {
	            	 existingMyStu.setAge(myStudent.getAge());
	            }
	             if(myStudent.getPhonenumber()!=0.0)
		            {
		            	 existingMyStu.setPhonenumber(myStudent.getPhonenumber());
		            }
	            myStudentsDetailsService.updateMyStudent(existingMyStu);
	            
	            response.put("message", "MyStudent information updated!");
	            return new ResponseEntity<HashMap>(response, HttpStatus.OK);
	            
	        }
	        else
	        {
	            response.put("message", "Invalid Student id!");
	            return new ResponseEntity<HashMap>(response, HttpStatus.NOT_FOUND);
	        }
	        
	    }
		 // employee/123 - URI parameter
        // employee?name=aman - Query/request parameter
        
        @GetMapping("/mystudents/name")
        public ResponseEntity<List<MyStudent>> getMyStudentsByName(@RequestParam("name") String myStuName)
        {
          return new ResponseEntity<List<MyStudent>>(myStudentsDetailsService.findyMyStudentsByName(myStuName),HttpStatus.OK);    
        }
        
        @GetMapping("/myStudents/standards")
        public ResponseEntity<List<MyStudent>> getMyStudentsByStandrad(@RequestParam("standard") String standard)
        {
          return new ResponseEntity<List<MyStudent>>(myStudentsDetailsService.findMyStudentsByStandard(standard),HttpStatus.OK);   
        }
        
       
        
        
       
        
        
        @PostMapping("myStudent/upload/{id}")
        public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable long id) {
          String message = "";
          HashMap<String, String> response=new HashMap<>();
          if(myStudentRepository.findById(id).isPresent())
	        {
	            MyStudent existingMyStu=myStudentRepository.findById(id).get();
	            
          try {
            filesStorageService.save(file);
            existingMyStu.setImageName(file.getOriginalFilename());
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
          } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
          }
	        }
          else {
        	  response.put("message", "Invalid Student id!");
	            return new ResponseEntity<HashMap>(response, HttpStatus.NOT_FOUND);
          }
        }
       
        
        
        
       // @GetMapping("mystudent/file")
       // public ResponseEntity<MyStudent> getFileByName(@RequestParam("file")String name) throws IOException {
          //List<MyStudent> myStudents =  filesStorageService.loadAll().map(path -> {
         ///   String filename = path.getFileName().toString();
        //    String url = MvcUriComponentsBuilder
          //      .fromMethodName(MyStudentController.class, "getFile", path.getFileName().toString()).build().toString();
//
           // return new MyStudent(filename, url);
          //}).collect(Collectors.toList());
          
      //	myStudents=myStudents.stream().filter(f->f.getImageName().equals(name)).collect(Collectors.toList());

      //    return ResponseEntity.status(HttpStatus.OK).body(myStudents.get(0));
      //  }
        
        @GetMapping("mystudent/files/{filename:.+}")
        public ResponseEntity<Resource> getFile(@PathVariable String filename) {
          Resource file = filesStorageService.load(filename);
          return ResponseEntity.ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        
        @DeleteMapping("mystudent/files/{filename:.+}")
        public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String filename) {
          String message = "";
          
          try {
            boolean existed = filesStorageService.delete(filename);
            
            if (existed) {
              message = "Delete the file successfully: " + filename;
              return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            
            message = "The file does not exist!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
          } catch (Exception e) {
            message = "Could not delete the file: " + filename + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
          }
        }
}


