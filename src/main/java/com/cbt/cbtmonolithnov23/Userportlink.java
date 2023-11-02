package com.cbt.cbtmonolithnov23;

import javax.persistence.*;

@Entity
@Table(name = "userportlinks")
public class Userportlink {
    @Id
    @Column(name = "linkid", nullable = false, length = 10)
    private String linkid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private Userdetail username;

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }

    public Userdetail getUsername() {
        return username;
    }

    public void setUsername(Userdetail username) {
        this.username = username;
    }

}