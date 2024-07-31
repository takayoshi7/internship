// DBから取得したデータを格納するエンティティクラス
package com.example.internship.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Class implements Serializable {
    // CLASS_IDカラム
    @Id
    private Integer CLASS_ID;

    // TEACHER_NAMEカラム
    private Integer TEACHER_ID;

    // CLASS_NAMEカラム
    private String CLASS_NAME;

    // CLASS_TIMEカラム
    private String CLASS_TIME;
}
