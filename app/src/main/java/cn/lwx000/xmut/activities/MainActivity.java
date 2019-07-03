package cn.lwx000.xmut.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import cn.lwx000.xmut.R;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navigateTo(R.id.action_global_homeFragment);
                    return true;
                case R.id.navigation_chart:
                    navigateTo(R.id.action_global_chartFragment);
                    return true;
                case R.id.navigation_video:
                    navigateTo(R.id.action_global_videoFragment);
                    return true;
                case R.id.navigation_mine:
                    navigateTo(R.id.action_global_mineFragment);
                    return true;
            }
            return false;
        }
    };

    private void navigateTo(int id) {
        Navigation.findNavController(findViewById(R.id.main_nav_host_fragment)).navigate(id);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
