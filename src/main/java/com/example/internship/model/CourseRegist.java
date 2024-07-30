// DBから取得したデータを格納するエンティティクラス
package com.example.internship.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COURSE_REGIST")
public class CourseRegist implements Serializable {
    // STUDENT_IDカラム
    @Id
    @Column(name = "STUDENT_ID")
    private Integer STUDENT_ID;

    // CLASS_IDカラム
    @Column(name = "CLASS_ID")
    private Integer CLASS_ID;
}
