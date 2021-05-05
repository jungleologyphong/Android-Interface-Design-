package nhatphong.ps11601.ps11601_phongdtn_asm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import nhatphong.ps11601.ps11601_phongdtn_asm.Fragment.Fragment_Chi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Fragment.Fragment_GioiThieu;
import nhatphong.ps11601.ps11601_phongdtn_asm.Fragment.Fragment_ThongKe;
import nhatphong.ps11601.ps11601_phongdtn_asm.Fragment.Fragment_ThongKeChart;
import nhatphong.ps11601.ps11601_phongdtn_asm.Fragment.Fragment_Thu;
import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiChiDAO.LoaiChiDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiThuDAO.LoaiThuDao;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolBar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_ac);
        toolBar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle = setupDrawerToogle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        UpdateTotalMoney();
        if(savedInstanceState == null){
            navigationView.setCheckedItem(R.id.itmThonngKe);
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout, new Fragment_ThongKe()).commit();
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Class fragmentClass = null;
                switch (item.getItemId()) {
                    case R.id.itmKhoanThu :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout, new Fragment_Thu()).commit();
                        break;
                    case R.id.itmKhoanChi :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout, new Fragment_Chi()).commit();
                        break;
                    case R.id.itmThonngKe :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout, new Fragment_ThongKe()).commit();
                        break;
                    case R.id.itmThongKeWithChart :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout, new Fragment_ThongKeChart()).commit();
                        break;
                    case R.id.itmGioiThieu :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout,new Fragment_GioiThieu()).commit();
                        break;
                    case R.id.itmUser :
                        Intent i = new Intent(MainActivity.this,MainLogin.class);
                        startActivity(i);
                        break;
                    case R.id.itmThoat :
                        finish();
                        break;
                    default :
                        fragmentClass = Fragment_Thu.class;
                }
                item.setChecked(true);
                setTitle(item.getTitle());
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToogle(){
        return new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolBar,R.string.Open,R.string.Close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//    public void UpdateTotalMoney(){
//        TextView total = findViewById(R.id.totalMoney);
//        LoaiChiDAO loaiChiDAO = new LoaiChiDAO(this);
//        LoaiThuDao loaiThuDao = new LoaiThuDao(this);
//        double totalMoney = loaiChiDAO.totalChi() - loaiThuDao.totalThu();
//        total.setText(String.valueOf(totalMoney));
//    }
}
