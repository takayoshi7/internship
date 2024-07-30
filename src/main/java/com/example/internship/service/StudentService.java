package com.example.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.internship.entity.Student;
import com.example.internship.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /**
     * レコードを全件取得する。
     * 
     * @return 全レコード
     */
    public List<Student> findAllDBData() {
        return studentRepository.findAll();
    }

    /**
     * 学生ログインユーザー情報を取得する。
     * 
     * @param userId ユーザーID
     * @return ユーザーIDで取得した1レコード
     */
    public List<Student> loginUser(Integer userId) {
        return studentRepository.loginUser(userId);
    }
}
