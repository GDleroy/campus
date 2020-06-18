package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guodi on 2020-04-20 00:27
 */
@Entity
@Table(name = "t_type")
@Getter
@Setter
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    private String name;

    private Date createTime;

    private Date updateTime;

    @OneToMany(mappedBy = "type")
    private List<Activity> activities = new ArrayList<>();

    public Type() {
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
