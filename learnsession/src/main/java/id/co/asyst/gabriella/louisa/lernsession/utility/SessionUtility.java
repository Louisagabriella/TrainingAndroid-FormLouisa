package id.co.asyst.gabriella.louisa.lernsession.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionUtility {

    final static String KEY_FILE = "training";
    final static String KEY_NAME = "name";
    final static String KEY_ADDRESS = "address";
    final static String KEY_GENDER = "gender";
    final static String KEY_EDUCATIONS = "educations";

    Context mContext;
    SharedPreferences sharedPreferences; //kertas
    SharedPreferences.Editor editor; //bolpoint

    public SessionUtility(Context context) {
        this.mContext = context;
        sharedPreferences = context.getSharedPreferences(KEY_FILE, 0); //nama penyimpanan file
        editor = sharedPreferences.edit(); //mengedit
    }

    public void saveName(String name, String address, String gender, String education) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_EDUCATIONS, education);
        editor.commit();//mengambil
    }

    public String loadName() {
        String name = sharedPreferences.getString(KEY_NAME, "");
        return name;//menulis
    }

    public String loadAddress() {
        String address = sharedPreferences.getString(KEY_ADDRESS, "");
        return address;//menulis
    }

    public String loadGender() {
        String gender = sharedPreferences.getString(KEY_GENDER, "");
        return gender;//menulis
    }

    public String loadEducation() {
        String educations = sharedPreferences.getString(KEY_EDUCATIONS, "");
        return educations;//menulis
    }
}
