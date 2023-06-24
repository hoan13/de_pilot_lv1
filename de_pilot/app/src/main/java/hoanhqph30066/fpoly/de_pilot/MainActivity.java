package hoanhqph30066.fpoly.de_pilot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import hoanhqph30066.fpoly.de_pilot.Adapter.XeAdapter;
import hoanhqph30066.fpoly.de_pilot.DAO.XeDAO;
import hoanhqph30066.fpoly.de_pilot.Model.Xe;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton themxe;
    XeAdapter xeAdapter;
    XeDAO xeDAO;
    ArrayList<Xe> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rclView);
        themxe = findViewById(R.id.floatingButton);
        laylaidl();

        themxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThemXeActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        laylaidl();
    }

    public void laylaidl() {
        xeDAO = new XeDAO(getApplicationContext());
        list = xeDAO.getAllData();
        xeAdapter = new XeAdapter(MainActivity.this, list, xeDAO);
        recyclerView.setAdapter(xeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}