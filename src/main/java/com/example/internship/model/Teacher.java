// DBから取得したデータを格納するエンティティクラス
package com.example.internship.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "TEACHER")
public class Teacher implements Serializable {
    // TEACHER_IDカラム
    @Id
    @Column(name = "TEACHER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TEACHER_ID;

    // TEACHER_NAMEカラム   
    @Column(name = "TEACHER_NAME")
    private String TEACHER_NAME;

    // TEACHER_PASSカラム  
    @Column(name = "TEACHER_PASS")
    private String TEACHER_PASS;
}
