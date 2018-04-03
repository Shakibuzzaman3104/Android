package com.example.shakib.firebase.Helpers;

public class Helper {

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean IsEmpty(String fs,String las,String age, String pass,String bg,String email)
    {
        if(fs.isEmpty() || las.isEmpty() || age.isEmpty() || pass.isEmpty() || bg.isEmpty() || email.isEmpty())
        {
            return true;
        }
        else
            return false;
    }

    public boolean IsEmpty(String Email,String Pass)
    {
        if(Email.isEmpty() || Pass.isEmpty())
        {
            return true;
        }
        else
            return false;
    }

}
