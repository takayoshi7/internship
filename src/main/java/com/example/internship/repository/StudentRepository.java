// データベースにアクセスするためのリポジトリクラス
package com.example.internship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.internship.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query(value = "SELECT * FROM STUDENT " +
			"WHERE STUDENT_ID = :studentID ", nativeQuery = true)
		List<Student> loginUser(@Param("studentID") Integer userId);
}
