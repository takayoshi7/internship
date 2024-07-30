// データベースにアクセスするためのリポジトリクラス
package com.example.internship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.internship.model.CourseRegist;
import com.example.internship.model.Student;
import com.example.internship.model.TimeTable;

import jakarta.transaction.Transactional;
// @QueryでSELECT以外の直接SQL文を実行する場合は@Transactionalと@Modifyingが必須
@Transactional
@Repository
public interface CourseRegistRepository extends JpaRepository<CourseRegist, Integer> {

	// 自分が登録済みの授業を削除する
	@Modifying
	@Query(value = "DELETE FROM COURSE_REGIST " +
			"WHERE STUDENT_ID = :studentID " +
			"AND CLASS_ID = :classID", nativeQuery = true)
		void deleteClass(@Param("studentID") Integer userId, @Param("classID") Integer classId);


		// @Modifying
		// @Query(value = "INSERT INTO COURSE_REGIST(STUDENT_ID, CLASS_ID)" +
		// 		"VALUES(:studentID, :classID) ", nativeQuery = true)
		// 	void addClass(@Param("studentID") Integer userId, @Param("classID") Integer classId);
	

}
