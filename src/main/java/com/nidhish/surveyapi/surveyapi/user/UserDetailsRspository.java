package com.nidhish.surveyapi.surveyapi.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRspository extends JpaRepository<UserDetails, Long> {
	List<UserDetails> findByRole(String role);
}
