package com.example.toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.Objects;

public class Main2Activity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("标题");
        // 使用主题修改Menu选项的问题
        toolbar.setPopupTheme(R.style.popup_theme);

        setSupportActionBar(toolbar);
        //设置是否有NavigationIcon（返回图标）
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // 设置Navigation的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Menu点击事件监听
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {

                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    // Menu选项点击处理
    private Toolbar.OnMenuItemClickListener onMenuItemClick=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String msg = "";
            switch (item.getItemId()) {
                case R.id.tv_1:
                    msg += "Click item1";
                    break;
                case R.id.tv_2:
                    msg += "Click item2";
                    break;
            }
            if (!msg.equals("")) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
}
