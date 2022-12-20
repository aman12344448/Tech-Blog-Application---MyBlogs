package com.blog.controllers;



import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.services.UserService;



import com.blog.payloads.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
		
	    //we made userDto because 
	    //we dont want to expose our entity that's 
	    //the main reason behind to make dto classs
	  //Post-Create User
	
	   
	  @PostMapping("/")
	  public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
		  
	       UserDto createUserDto =	  this.userService.createUser(userDto);
		  return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	  }
	  
	  
	  
	//put - update user
	  
	  @PutMapping("/{userId}")
	  public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			  @PathVariable("userId") Integer uid){
		  
	UserDto updatedUser = 	this.userService.updateUser(userDto, uid);
	return ResponseEntity.ok(updatedUser);
	  }
	  
	  

	//delete - delete user
	  @PreAuthorize("hasRole('ADMIN')")
	  @DeleteMapping("/{userId}")
	  public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer uid){
		  this.userService.deleteUser(uid);
	//	  return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		  return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted Successfully",true),HttpStatus.OK);
		  
	  }	  
	
	//Get -  All user Get 
	  
	  @GetMapping("/")
	  public ResponseEntity<List<UserDto>> getAllUsers(){
	  return ResponseEntity.ok(this.userService.getAllUsers());
	

}
	  @GetMapping("/{userId}")
	  public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
	  return ResponseEntity.ok(this.userService.getUserById(userId));
	

}
	  
}
