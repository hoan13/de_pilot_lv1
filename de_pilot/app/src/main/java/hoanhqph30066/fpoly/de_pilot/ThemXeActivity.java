package hoanhqph30066.fpoly.de_pilot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hoanhqph30066.fpoly.de_pilot.DAO.XeDAO;
import hoanhqph30066.fpoly.de_pilot.Model.Xe;

public class ThemXeActivity extends AppCompatActivity {

    EditText tenxe, hangxe, namsx, giaxe;

    Button luu, thoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_xe);
        tenxe = findViewById(R.id.edTenXe);
        hangxe = findViewById(R.id.edHangxe);
        namsx = findViewById(R.id.edNamSx);
        giaxe = findViewById(R.id.edGiaTien);

        luu = findViewById(R.id.btn_luu);
        thoat = findViewById(R.id.btn_thoat);

        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String them_tenxe = tenxe.getText().toString();
                String them_hangxe = hangxe.getText().toString();
                String them_namsx = namsx.getText().toString();
                String them_giaxe = giaxe.getText().toString();

                if(them_tenxe.length()==0){
                    tenxe.requestFocus();
                    tenxe.setError("không được để trống tên xe");
                } else if (them_hangxe.length()==0) {
                    hangxe.requestFocus();
                    hangxe.setError("không được để trống hãng xe");
                }else if (them_namsx.length()==0) {
                    namsx.requestFocus();
                    namsx.setError("không được để trống năm sản xuất");
                }else if (Integer.parseInt(them_namsx) <1980 || Integer.parseInt(them_namsx) >2023){
                    namsx.requestFocus();
                    namsx.setError("Năm sản xuất nằm trong khoảng 1980 đến 2023");
                }else if (them_giaxe.length()==0) {
                    giaxe.requestFocus();
                    giaxe.setError("không được để trống giá xe");
                }else {
                    Xe themxe = new Xe();
                    themxe.setTen(them_tenxe);
                    themxe.setHangXe(them_hangxe);
                    themxe.setNamSX(Integer.parseInt(them_namsx));
                    themxe.setGiaBan(Double.parseDouble(them_giaxe));

                    XeDAO xeDAO = new XeDAO(getApplicationContext());
                    boolean check = xeDAO.ThemXe(themxe);
                    if (check){
                        Toast.makeText(ThemXeActivity.this, "lưu thành công", Toast.LENGTH_SHORT).show();
                        clear();
                    }else {
                        Toast.makeText(ThemXeActivity.this, "lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemXeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void clear(){
        tenxe.setText("");
        hangxe.setText("");
        namsx.setText("");
        giaxe.setText("");
    }
}