// DBから取得したデータを格納するエンティティクラス
package com.example.internship.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TimeTable implements Serializable {
    // CLASS_IDカラム
    @Id
    private Integer CLASS_ID;

    // TEACHER_NAMEカラム    
    private String TEACHER_NAME;

    // CLASS_NAMEカラム   
    private String CLASS_NAME;

    // CLASS_TIMEカラム  
    private String CLASS_TIME;
}
