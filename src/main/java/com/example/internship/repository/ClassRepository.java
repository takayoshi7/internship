// データベースにアクセスするためのリポジトリクラス
package com.example.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.internship.entity.Class;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
	// 自分が登録済みの授業を削除する
	@Modifying
	@Query(value = "DELETE FROM CLASS " +
			"WHERE TEACHER_ID = :teacherID " +
			"AND CLASS_ID = :classID ", nativeQuery = true)
		void deleteTeacherClass(@Param("teacherID") Integer userId, @Param("classID") Integer classId);

	// 新しく授業を登録する
	@Modifying
	@Query(value = "INSERT INTO CLASS(TEACHER_ID, CLASS_NAME, CLASS_TIME)" +
			"VALUES(:teacherID, :classNAME, :classTIME) ", nativeQuery = true)
		void insertTeacherClass(@Param("teacherID") Integer userId,
								@Param("classNAME") String className, @Param("classTIME") String classTime);

	// 登録済みの授業を更新する
	@Modifying
	@Query(value = "UPDATE CLASS SET CLASS_ID = :classID, CLASS_NAME = :classNAME, CLASS_TIME = :classTIME " +
			"WHERE TEACHER_ID = :teacherID ", nativeQuery = true)
		void updateTeacherClass(@Param("classID") Integer classId, @Param("teacherID") Integer userId,
								@Param("classNAME") String className, @Param("classTIME") String classTime);
}
