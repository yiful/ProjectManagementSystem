package com.rjt.projectmanagementsystem.model;

import com.dropbox.client2.session.Session;

/**
 * Created by rashmi on 12/4/2017.
 */

public class Constants {

    public static final String OVERRIDEMSG = "File name with this name already exists.Do you want to replace this file?";
    final static public String DROPBOX_APP_KEY = "fxvygnld36zmnpj";
    final static  public String DROPBOX_APP_SECRET = "fo1w3grjajesmmc";
    public static boolean mLoggedIn = false;
    final static public Session.AccessType ACCESS_TYPE = Session.AccessType.DROPBOX;


    final static public String ACCOUNT_PREFS_NAME = "prefs";
    final static public String ACCESS_KEY_NAME = "ACCESS_KEY";
    final static public String ACCESS_SECRET_NAME = "ACCESS_SECRET";
}
