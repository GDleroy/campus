package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by guodi on 2020-04-08 11:01
 */
@Entity
@Getter
@Setter
public class Activity {

    @Id
    private Long    id;
    private String  activityType;
    private String  activityContent;
    private String  activityAdress;
    private Date activityDate;
    private String  activityTitle;
    private String  activityPerson;
    private String  owner;
    private String  activityFlag;
    private String  activityStatus;
    private String  approveStatus;

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType='" + activityType + '\'' +
                ", activityContent='" + activityContent + '\'' +
                ", activityAdress='" + activityAdress + '\'' +
                ", activityDate=" + activityDate +
                ", activityTitle='" + activityTitle + '\'' +
                ", activityPerson='" + activityPerson + '\'' +
                ", owner='" + owner + '\'' +
                ", activityFlag='" + activityFlag + '\'' +
                ", activityStatus='" + activityStatus + '\'' +
                ", approveStatus='" + approveStatus + '\'' +
                '}';
    }
}
