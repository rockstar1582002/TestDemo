/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account_module;

import java.util.concurrent.TimeUnit;

public class Account {
    private String id;
    private String PhoneNumber;
    private String PassWord;
    private String Name;
    private TimeUnit CreateAt;
    private TimeUnit UpdateAt;
    private int Status;
    private int Type;
    
    public Account(){
        
    }

    public Account(String id, String PhoneNumber, String PassWord, String Name, TimeUnit CreateAt, TimeUnit UpdateAt, int Status, int Type) {
        this.id = id;
        this.PhoneNumber = PhoneNumber;
        this.PassWord = PassWord;
        this.Name = Name;
        this.CreateAt = CreateAt;
        this.UpdateAt = UpdateAt;
        this.Status = Status;
        this.Type = Type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public TimeUnit getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(TimeUnit CreateAt) {
        this.CreateAt = CreateAt;
    }

    public TimeUnit getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt(TimeUnit UpdateAt) {
        this.UpdateAt = UpdateAt;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }
}
