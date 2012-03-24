package org.enjoythere;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {

	private static final String ENJOYTHERE_PREFS = "org.enjoythere.ENJOYTHERE_PREFS";

	private Editor editor;
	private SharedPreferences prefs;

	private static Preferences instance = new Preferences();

	public static Preferences Instance() {
		return instance;
	}

	public void Initialize(Context context) {
		prefs = context.getSharedPreferences(ENJOYTHERE_PREFS,
				Activity.MODE_PRIVATE);
		Cancel();
	}

	private Preferences() {

	}

	public void SetCredentials(String login, String password) {
		editor.putString("login", login);
		editor.putString("password", password);
	}
	public String GetLogin() {
		return prefs.getString("login", null);
	}

	public String GetPassword() {
		return prefs.getString("password", null);
	}

	public void Save() {
		editor.commit();
	}

	public void Cancel() {
		editor = prefs.edit();
	}

}
