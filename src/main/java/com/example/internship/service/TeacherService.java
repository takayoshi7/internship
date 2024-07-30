package com.example.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.internship.entity.Teacher;
import com.example.internship.repository.TeacherRepository;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * レコードを全件取得する。
     * 
     * @return 全レコード
     */
    public List<Teacher> findAllDBData() {
        return teacherRepository.findAll();
    }

    /**
     * 教員ログインユーザー情報を取得する。
     * 
     * @param userId ユーザーID
     * @return ユーザーIDで取得した1レコード
     */
    public List<Teacher> loginUser(Integer userId) {
        return teacherRepository.loginUser(userId);
    }
}
