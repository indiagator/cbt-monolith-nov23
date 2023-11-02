package com.cbt.cbtmonolithnov23;

import javax.persistence.*;

@Entity
@Table(name = "usernamewalletlinks")
public class Usernamewalletlink {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false)
    private Userdetail userdetails;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Userdetail getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(Userdetail userdetails) {
        this.userdetails = userdetails;
    }

}