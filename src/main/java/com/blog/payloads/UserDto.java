package com.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;

import com.blog.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	//use for transfer data 
     private int id;
     
    
     
     @NotEmpty
     @NotNull
     @Size(min = 4 , message = "username must be min of 4 characters")
     private String name;
     
     @Email(message = "Email address is not valid!!")
     @NotEmpty
     @NotNull
     private String email;
     
     @NotEmpty
     @NotNull
     @Size(min = 3 , max = 10, message = "Password must be minimum of Three char and maximum of 10 chars")
     private String password;
     
     @NotEmpty
     @NotNull
     private String about;
     
     private Set<RoleDto> roles = new HashSet<>();
     
     @JsonIgnore
    public String getPassword() {
    	 return this.password;
     }
     
     @JsonProperty
     public void setPassword(String password) {
    	 this.password=password;
     }
     
}
