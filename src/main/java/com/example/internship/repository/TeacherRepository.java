// データベースにアクセスするためのリポジトリクラス
package com.example.internship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.internship.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	
	@Query(value = "SELECT * FROM TEACHER " +
			"WHERE TEACHER_ID = :teacherID ", nativeQuery = true)
		List<Teacher> loginUser(@Param("teacherID") Integer userId);
}
