package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping("/")
	public String student(Model m) {
		
		List<Student> list=studentRepo.findAll();
		m.addAttribute("all_student",list);
		
		return "index";
	}
	
	@GetMapping("/load_form")
	public String loadForm() {
		return "add";
	}
	
	@GetMapping("/edit_form/{id}")
	public String editForm(@PathVariable(value= "id")long id, Model m) {
		
		Optional<Student> student= studentRepo.findById(id);
		
		Student stu = student.get();
		m.addAttribute("student",stu);
		return "edit";
	}
	
	@PostMapping("/save_student")
	public String saveStudent(@ModelAttribute Student student ) {
		
		studentRepo.save(student);
//		session.setAttribute("msg", "student added successfully...");
		
		return "redirect:/load_form";
		
	}
	
	@PostMapping("/update_student")
	public String updateStudent(@ModelAttribute Student student ) {
		
		studentRepo.save(student);
//		session.setAttribute("msg", "student updated successfully...");
		
		return "redirect:/";
		
	}
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(value= "id") long id ) {
		
		studentRepo.deleteById(id);
		
//		session.setAttribute("msg", "student delete successfully...");
		
		return "redirect:/";
		
	}
	
	
	
}
	
	
