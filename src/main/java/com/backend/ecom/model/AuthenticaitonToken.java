package com.backend.ecom.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tokens")

public class AuthenticaitonToken {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "Id")
    private Integer id;

    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = User.class , fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "user_id")
    private  User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
