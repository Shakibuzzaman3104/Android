package com.example.shakib.firebase.Model;

import java.util.List;


public class Users
    {
        private String Age,BG,Email,First_Name,Last_Name;

       public Users()
        {

        }

        public Users(String age, String BG, String email, String first_Name, String last_Name) {
            Age = age;
            this.BG = BG;
            Email = email;
            First_Name = first_Name;
            Last_Name = last_Name;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String age) {
            Age = age;
        }

        public String getBG() {
            return BG;
        }

        public void setBG(String BG) {
            this.BG = BG;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getFirst_Name() {
            return First_Name;
        }

        public void setFirst_Name(String first_Name) {
            First_Name = first_Name;
        }

        public String getLast_Name() {
            return Last_Name;
        }

        public void setLast_Name(String last_Name) {
            Last_Name = last_Name;
        }
    }


