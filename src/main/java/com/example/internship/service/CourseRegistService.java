package com.example.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.internship.model.CourseRegist;
import com.example.internship.repository.CourseRegistRepository;

@Service
public class CourseRegistService {
    @Autowired
    private CourseRegistRepository courseRegistRepository;

    /**
     * レコードを全件取得する。
     * 
     * @return 全レコード
     */
    public List<CourseRegist> findAllDBData() {
        return courseRegistRepository.findAll();
    }

    /**
     * 学生が履修登録に登録済みの授業を削除する。
     * 
     * @param userId ユーザーID
     * @param classId 授業ID
     */
    public void deleteClass(Integer userId, Integer classId) {
        courseRegistRepository.deleteClass(userId, classId);
    }


    /**
     * 学生が未登録の授業を追加する。
     * 
     * @param userId ユーザーID
     * @param classId 授業ID
     */
    // public void addClass(Integer userId, Integer classId) {
    //     courseRegistRepository.addClass(userId, classId);
    // }
}
