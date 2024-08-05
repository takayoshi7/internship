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
@Data
@Table(name = "CLASS")
public class Class implements Serializable {
    // CLASS_IDカラム
    @Id
    @Column(name = "CLASS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CLASS_ID;

    // TEACHER_NAMEカラム
    @Column(name = "TEACHER_ID")
    private Integer TEACHER_ID;

    // CLASS_NAMEカラム
    @Column(name = "CLASS_NAME")
    private String CLASS_NAME;

    // CLASS_TIMEカラム
    @Column(name = "CLASS_TIME")
    private String CLASS_TIME;
}
