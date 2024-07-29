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
    
    //Dataアノテーションを使うとgettterとsetterの定義をしなくてもよい
//    public Integer getId() {
//        return STUDENT_ID;
//    }
//    public void setId(Integer STUDENT_ID) {
//        this.STUDENT_ID = STUDENT_ID;
//    }
//    public String getName() {
//        return STUDENT_NAME;
//    }
//    public void setName(String STUDENT_NAME) {
//        this.STUDENT_NAME = STUDENT_NAME;
//    }
//    public String getPassword() {
//        return STUDENT_PASS;
//    }
//    public void setPassword(String STUDENT_PASS) {
//        this.STUDENT_PASS = STUDENT_PASS;
//    }
}
