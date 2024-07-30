// DBから取得したデータを格納するエンティティクラス
package com.example.internship.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data // Dataアノテーションを使うとgettterとsetterの定義をしなくてもよい
@Table(name = "STUDENT")
public class Student implements Serializable {
    // STUDENT_IDカラム
    @Id
    @Column(name = "STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer STUDENT_ID;
    
    // STUDENT_NAMEカラム   
    @Column(name = "STUDENT_NAME")
    private String STUDENT_NAME;

    // STUDENT_PASSカラム  
    @Column(name = "STUDENT_PASS")
    private String STUDENT_PASS;
}
