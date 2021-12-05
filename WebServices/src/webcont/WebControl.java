package webcont;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import beans.Student;

@RestController
public class WebControl {
	static ArrayList<Student> studentList;
	static {
		 Student student1 = new Student();
		 student1.setName("Khali");
		 student1.setRollno("101");
		 
		 Student student2 = new Student();
		 student2.setName("kane");
		 student2.setRollno("102");
		 
		 Student student3 = new Student();
		 student3.setName("Undertaker");
		 student3.setRollno("103");
		 
		 studentList = new ArrayList<Student>();
		 studentList.add(student1);
		 studentList.add(student2);
		 studentList.add(student3);
		 
	}
	 
	//**********************Read All Students*******************
	 @RequestMapping(value = "/studentInfo",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	 public ArrayList<Student> getAllStudentInfo() 
	 {		
		
		 return studentList;
	 }
	
	//*********************Read a Specific Student**************************
	@RequestMapping(value = "/studentInfo/{rollno}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudentInfo(@PathVariable("rollno") String rollno)
	{	Student s = null;
		for(Student student : studentList) {
			if(student.getRollno().equals(rollno))
			{s = student;}
		}	
		 return s;
	} 
	
	//*********************Update a Specific Student**************************
	@RequestMapping(value = "/studentInfo/{rollno}",method = RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateStudent(@PathVariable("rollno") String rollno, @RequestBody Student st) 
	{	boolean b = false;
		//find the user record using name from the database
		//update the record using "user" bean object(which is created using @RequestBody annotation)
		for (Student student : studentList) {
			if(student.getRollno().equals(rollno))
			{ 	student.setRollno(st.getRollno());
				student.setName(st.getName());
				b = true;
			}
		}
		
		return b;
		
	}
	
	//*********************Create new Students using post request **************************
	@RequestMapping(value = "/studentInfo",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean createStudent(@RequestBody Student student) 
	{	
		studentList.add(student);
		return true;
		
	}
	
	//********************Delete a specific Student****************************************
	@RequestMapping(value = "/studentInfo/{rollno}",method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteStudent(@PathVariable("rollno") String rollno)
	{	boolean b = false;
		Iterator<Student> studentIterator = studentList.iterator();
		while (studentIterator.hasNext()) 
		{
			Student student = (Student) studentIterator.next();
			if(student.getRollno().equals(rollno))
			{
				studentIterator.remove();
				b = true;
			}
		}
		return b;
		
	}
	
	
	
	
	
	
	
	
}	
	

