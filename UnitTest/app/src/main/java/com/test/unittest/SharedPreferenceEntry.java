package com.test.unittest;

/**
 * Created by qiaoda.zqd on 2016/10/25.
 */

import java.util.Calendar;

/**
 * Model class containing personal information that will be saved to SharedPreferences.
 */
public class SharedPreferenceEntry {

    // Name of the user.
    private final String mName;

    // Date of Birth of the user.
    private final Calendar mDateOfBirth;

    // Email address of the user.
    private final String mEmail;

    public SharedPreferenceEntry(String name, Calendar dateOfBirth, String email) {
        mName = name;
        mDateOfBirth = dateOfBirth;
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public Calendar getDateOfBirth() {
        return mDateOfBirth;
    }

    public String getEmail() {
        return mEmail;
    }
}
