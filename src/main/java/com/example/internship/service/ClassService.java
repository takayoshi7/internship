package com.example.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.internship.entity.Class;
import com.example.internship.repository.ClassRepository;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    /**
     * レコードを全件取得する。
     * 
     * @return 全レコード
     */
    public List<Class> findAllDBData() {
        return classRepository.findAll();
    }

    /**
     * 教員が登録済みの授業を削除する。
     * 
     * @param userId ユーザーID
     * @param classId 授業ID
     */
    public void deleteTeacherClass(Integer userId, Integer classId) {
        classRepository.deleteTeacherClass(userId, classId);
    }

    /**
     * 教員が未登録の授業を登録する。
     * 
     * @param classId 授業ID
     * @param userId ユーザーID
     * @param className 授業名
     * @param classTime 授業時間
     */
    public void insertTeacherClass(Integer userId, String className, String classTime) {
        classRepository.insertTeacherClass(userId, className, classTime);
    }

    /**
     * 教員が登録済みの授業を更新する。
     * 
     * @param classId 授業ID
     * @param userId ユーザーID
     * @param className 授業名
     * @param classTime 授業時間
     */
    public void updateTeacherClass(Integer classId, String className, String classTime) {
        classRepository.updateTeacherClass(classId, className, classTime);
    }
}
