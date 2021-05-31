package web.member.bean;

import java.sql.Timestamp;

public class Member {
    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private Boolean pass;
    private Timestamp lastUpdateDate;
    
    // 現在狀態
    private Boolean status;
    
    // Singleton 使用者唯一member物件
    private static Member instance;
    
    public static Member getInstance(){
        // 第一次被呼叫的時候再建立物件
        if(instance == null){
            instance = new Member();
        } 
        return instance;
    }
    
    private Member() {
        this.status = false;
    }
    
    private Member(Integer id, String account, String password, String nickname, Boolean pass,
            Timestamp lastUpdateDate) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.pass = pass;
        this.lastUpdateDate = lastUpdateDate;
        this.status = false;
    }

    public void clear() {
        instance = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
