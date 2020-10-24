package com.wyu.pojo;

public class Role {
    private Integer rid;

    private String rname;

    private String rremark;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getRremark() {
        return rremark;
    }

    public void setRremark(String rremark) {
        this.rremark = rremark == null ? null : rremark.trim();
    }
}