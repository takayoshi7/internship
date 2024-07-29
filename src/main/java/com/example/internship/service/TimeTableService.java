package com.example.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.internship.model.TimeTable;
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
     * 学生が履修登録に登録済みの授業を削除する。
     * 
     * @param userId ユーザーID
     * @param classId 授業ID
     * @return 削除された後の履修登録レコード
     */
    public void deleteClass(Integer userId, Integer classId) {
        timeTableRepository.deleteClass(userId, classId);
    }

    /**
     * 学生ID指定で未登録の授業を取得する
     * 
     * @param studentID 学生ID
     * @return 時間割表レコード
     */
    public List<TimeTable> getNotClass(Integer studentID) {
        return timeTableRepository.getNotClass(studentID);
    }
}
