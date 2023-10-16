package com.example.projetointerdisciplinar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Logger {
    String data;
    String id;
    String desc;
    String userEmail;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public Logger(String desc, String user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.data = dateFormat.format(new Date());
        this.id = UUID.randomUUID().toString();
        this.desc = desc;
        this.userEmail = user;
    }
    public void generateLog(String tipo){
        DatabaseReference myRef = database.getReference(tipo);
        DatabaseReference logRef = myRef.push();

        logRef.setValue(this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
