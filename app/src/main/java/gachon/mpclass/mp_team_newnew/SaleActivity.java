package gachon.mpclass.mp_team_newnew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import gachon.mpclass.mp_team_newnew.api.MyGeoCoder;
import gachon.mpclass.mp_team_newnew.form.PostingForm;
import gachon.mpclass.mp_team_newnew.form.TodaySaleForm;

public class SaleActivity extends AppCompatActivity {
    ImageButton btn_inform;
    ImageButton btn_address;

    private String str;
    private String address ="";

    static List<TodaySaleForm> saleFormList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);


        // 본인 주소 찾기 버튼 (find my location)
        btn_address = (ImageButton) findViewById(R.id.btn_address);
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyGeoCoder myGeoCoder = new MyGeoCoder(SaleActivity.this);
                str = myGeoCoder.getAddress(); // 데이터베이스의 address (실제)
                String[] addresses = str.split(" ");
                address = addresses[3] + " " + addresses[4]; // 데이터베이스의 address_around (자른 값)

                Toast.makeText(getApplicationContext(), "현재 나의 위치 : " + str, Toast.LENGTH_LONG).show();
                // Log.d("rev", address);
            }
        });

        // information 추가하기 버튼
        btn_inform = (ImageButton) findViewById(R.id.btn_inform);
        btn_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // information 입력하는 activity로 이동 ( SalePostingActivity )
                Intent myintent = new Intent(getApplicationContext(), SalePostingActivity.class);

                myintent.putExtra("address", str);
                myintent.putExtra("address_around", address);

                startActivityForResult(myintent, 1);
            }
        });
    }

    // SalePostingActivity에서 정보입력 완료 후 다시 돌아왔을 때 실행 : listview 뿌려줌
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                //어댑터
                ListView listView = findViewById(R.id.listview);
                SaleAdapter adapter = new SaleAdapter(this, R.layout.sale_item, saleFormList);

                // db에서 정보 가져와서 adapter에 추가해야됨

                listView.setAdapter(adapter);

                Toast.makeText(getApplicationContext(), " 동네특가 등록 성공! ", Toast.LENGTH_LONG).show();
            }
        }
    }
}