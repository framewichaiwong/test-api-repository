package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "user_manager_member")
public class UserManagerMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "role_status")
    private String roleStatus;

    @Column(name = "manager_id")
    private int managerId;
}
