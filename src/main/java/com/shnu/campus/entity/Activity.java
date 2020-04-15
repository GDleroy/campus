package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by guodi on 2020-04-08 11:01
 */
@Entity
@Getter
@Setter
@Table(name = "t_activity")
public class Activity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long    id;

    @Column(name="activity_type")
    private String  activityType;

    @Column(name="activity_content")
    private String  activityContent;

    @Column(name="activity_address")
    private String  activityAddress;

    @Column(name="activity_date")
    private Date activityDate;

    @Column(name="activity_title")
    private String  activityTitle;

    @Column(name="activity_person")
    private String  activityPerson;

    @Column(name="owner")
    private String  owner;

    @Column(name="activity_flag")
    private String  activityFlag;

    @Column(name="activity_status")
    private String  activityStatus;

    @Column(name="approve_status")
    private String  approveStatus;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="update_time")
    private Date updateTime;

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType='" + activityType + '\'' +
                ", activityContent='" + activityContent + '\'' +
                ", activityAdress='" + activityAddress + '\'' +
                ", activityDate=" + activityDate +
                ", activityTitle='" + activityTitle + '\'' +
                ", activityPerson='" + activityPerson + '\'' +
                ", owner='" + owner + '\'' +
                ", activityFlag='" + activityFlag + '\'' +
                ", activityStatus='" + activityStatus + '\'' +
                ", approveStatus='" + approveStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
