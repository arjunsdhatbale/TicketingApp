package com.main.User;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

	public static Specification<User> hasUserNameLike(String userName){
		return (root,query,criteriaBuilder) -> 
			userName == null || userName.isEmpty() ? null 
					: criteriaBuilder.like(criteriaBuilder.lower(root.get("userName")), "%" + userName.toLowerCase() + "%");
	}
	
	public static Specification<User> hasEmailLike(String email){
		return (root,query,criteriaBuilder) -> 
		 	email == null || email.isEmpty() ? null 
		 			: criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase()+"%");
		
	}
	public static Specification<User> hasActive(Boolean active){
		return (root,query,criteriaBuilder) -> 
			active == null ? null : criteriaBuilder.equal(root.get("active"), active);
	}
}
