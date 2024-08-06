// データベースにアクセスするためのリポジトリクラス
package com.example.internship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.internship.entity.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
	// ログインユーザーの時間割表データ
	@Query(value = "SELECT CLASS.CLASS_ID, TEACHER_NAME, CLASS_NAME, CLASS_TIME FROM CLASS " +
			"LEFT JOIN TEACHER ON CLASS.TEACHER_ID = TEACHER.TEACHER_ID " +
			"LEFT JOIN COURSE_REGIST ON CLASS.CLASS_ID = COURSE_REGIST.CLASS_ID " +
			"WHERE COURSE_REGIST.STUDENT_ID = :studentID " +
			"ORDER BY CLASS_TIME ASC ", nativeQuery = true)
		List<TimeTable> getTimeTable(@Param("studentID") Integer studentID);
	
	// ログインユーザーが未登録の授業データ
	@Query(value = "SELECT CLASS.CLASS_ID, TEACHER_NAME, CLASS_NAME, CLASS_TIME FROM CLASS " +
			"LEFT JOIN TEACHER ON CLASS.TEACHER_ID = TEACHER.TEACHER_ID " +
			"WHERE NOT CLASS_ID IN (SELECT CLASS_ID FROM COURSE_REGIST WHERE STUDENT_ID = :studentID) " +
			"ORDER BY CLASS.CLASS_ID ASC ", nativeQuery = true)
		List<TimeTable> getNotClass(@Param("studentID") Integer studentID);

	// ログインユーザーが登録済みの授業データ
	@Query(value = "SELECT CLASS.CLASS_ID, TEACHER_NAME, CLASS_NAME, CLASS_TIME FROM CLASS " +
			"LEFT JOIN TEACHER ON CLASS.TEACHER_ID = TEACHER.TEACHER_ID " +
			"WHERE CLASS.TEACHER_ID = :teacherID " +
			"ORDER BY CLASS.CLASS_ID ASC ", nativeQuery = true)
		List<TimeTable> getClass(@Param("teacherID") Integer teacherID);
}
