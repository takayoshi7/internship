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

    //Dataアノテーションを使うとgettterとsetterの定義をしなくてもよい
//    public Integer getId() {
//        return TEACHER_ID;
//    }
//    public void setId(Integer TEACHER_ID) {
//        this.TEACHER_ID = TEACHER_ID;
//    }
//    public String getName() {
//        return TEACHER_NAME;
//    }
//    public void setName(String TEACHER_NAME) {
//        this.TEACHER_NAME = TEACHER_NAME;
//    }
//    public String getPassword() {
//        return TEACHER_PASS;
//    }
//    public void setPassword(String TEACHER_PASS) {
//        this.TEACHER_PASS = TEACHER_PASS;
//    }
}
