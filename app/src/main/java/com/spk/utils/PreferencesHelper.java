package com.spk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesHelper {

	private SharedPreferences sharedPreferences;
	private Editor editor;
	private Context context;

	public PreferencesHelper(Context context) {
		this.context = context;
	}

	public String getPreferences(String key) {
		sharedPreferences = context.getSharedPreferences(key, 0);
		return sharedPreferences.getString(key, "");
	}

	public void savePreferences(String key, String value) {
		sharedPreferences = context.getSharedPreferences(key, 0);
		editor = sharedPreferences.edit();

		editor.putString(key, value);
		editor.commit();
	}

	public void saveBooleanPreferences(String key, boolean value) {
		sharedPreferences = context.getSharedPreferences(key, 0);
		editor.clear();
		editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getBooleanPreferences(String key) {
		sharedPreferences = context.getSharedPreferences(key, 0);

		boolean result = sharedPreferences.getBoolean(key, true);
		System.out.println("result bool =" + result);
		return result;
	}

}
