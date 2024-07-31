package com.example.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.internship.entity.TimeTable;
import com.example.internship.repository.TimeTableRepository;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableRepository timeTableRepository;

    /**
     * 学生ID指定で時間割表を取得する
     * 
     * @param studentID 学生ID
     * @return 時間割表レコード
     */
    public List<TimeTable> getTimeTable(Integer studentID) {
        return timeTableRepository.getTimeTable(studentID);
    }

    /**
     * 教員ID指定で登録済みの授業を取得する
     * 
     * @param userId ユーザーID
     * @param classId 授業ID
     * @return 登録済みの授業レコード
     */
    public List<TimeTable> getClass(Integer teacherId) {
        return timeTableRepository.getClass(teacherId);
    }

    /**
     * 学生ID指定で未登録の授業を取得する
     * 
     * @param studentID 学生ID
     * @return 時間割表レコード
     * @return 未登録の授業レコード
     */
    public List<TimeTable> getNotClass(Integer studentID) {
        return timeTableRepository.getNotClass(studentID);
    }
}
