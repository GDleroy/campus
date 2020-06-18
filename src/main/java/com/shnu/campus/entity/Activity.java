package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guodi on 2020-04-08 11:01
 */
@Entity
@Getter
@Setter
@Table(name = "t_activity")
public class Activity {

    public Activity() {
    }

    @Id
    @GeneratedValue
    private Long  id;

    @Column(name="activity_type")
    private String  activityType;

    @Basic(fetch = FetchType.LAZY)
    @Lob//大字段
    @Column(name="activity_content")
    private String  activityContent;

    @Column(name="activity_address")
    private String  activityAddress;

    @DateTimeFormat (pattern="yyyy-MM-dd")
    @Column(name="activity_date")
    private Date activityDate;//开始时间

    @DateTimeFormat (pattern="yyyy-MM-dd")
    @Column(name="end_date")
    private Date endDate;//结束时间

    @Column(name="activity_title")
    private String  activityTitle;

    @Column(name="activity_person")
    private String  activityPerson;

    @Column(name="owner")
    private String  owner;//举办单位

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

    @Column(name="attention")
    private String attention;

    @Column(name="warn_content")
    private String warnContent;

    @Column(name="warn_status")
    private String warnStatus;

    private Integer views;

    private String description;

    private boolean published;

    private boolean recommend;

    private String picture;

    @Transient
    private String tagIds;

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "activity",fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType='" + activityType + '\'' +
                ", activityContent='" + activityContent + '\'' +
                ", activityAddress='" + activityAddress + '\'' +
                ", activityDate=" + activityDate +
                ", activityTitle='" + activityTitle + '\'' +
                ", activityPerson='" + activityPerson + '\'' +
                ", owner='" + owner + '\'' +
                ", activityFlag='" + activityFlag + '\'' +
                ", activityStatus='" + activityStatus + '\'' +
                ", approveStatus='" + approveStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", views=" + views +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", recommend=" + recommend +
                ", tagIds='" + tagIds + '\'' +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
