package com.spk.drawerAdmin;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.spk.main.DestinasiWisataAdmin;
import com.spk.main.Konfigurasi;
import com.spk.main.Login;
import com.spk.main.R;
import com.spk.main.TentangAplikasi;
import com.spk.utils.PreferencesHelper;


public class MainActivityAdmin extends ActionBarActivity
        implements NavigationDrawerCallbacksAdmin {

    private NavigationDrawerFragmentAdmin mNavigationDrawerFragment;
    private Toolbar mToolbar;
    Fragment objFragment;
    FragmentManager fragmentManager;

    PreferencesHelper ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        ph = new PreferencesHelper(this);

        mNavigationDrawerFragment = (NavigationDrawerFragmentAdmin)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        mNavigationDrawerFragment.setUserData("Admin", "Admin", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        objFragment = null;

        switch (position) {
            case 0:
                setTitle("Destinasi Wisata");
                objFragment = new DestinasiWisataAdmin();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, objFragment)
                        .commit();
                break;
            case 1:
                setTitle("Konfigurasi Bobot");
                objFragment = new Konfigurasi();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, objFragment)
                        .commit();
                break;
            case 2:
                setTitle("Tentang Aplikasi");
                objFragment = new TentangAplikasi();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, objFragment)
                        .commit();
                break;
            case 3:
                setTitle("Keluar");
                Intent i = new Intent(MainActivityAdmin.this, Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                ph.savePreferences("status_login", "0");
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
