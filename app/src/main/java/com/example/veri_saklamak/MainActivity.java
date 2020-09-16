package com.example.veri_saklamak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
* Veri saklamak için veri tabanı kullanabilirim Tabi burada da birkaç tane soru işareti gelebilir
* Ancak küçük verileri saklamak için veri tabanı kullanmak gereksiz
* örneğin isim ve yaş gibi verileri saklamak için veri tabanı kullanmak yerine nesne oluşturabiliyorum
* SharedPreferences denir*/
public class MainActivity extends AppCompatActivity {
EditText et;
Button btn_save;
TextView tv_yas;
Button btn_sil;
    SharedPreferences sharedPreferences;

@Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // package ismini veriyorum ve Context.MODE_PRIVATE sadece benim uygulamamdan ulaşılsın anlamına geliyor
        sharedPreferences = this.getSharedPreferences("com.example.veri_saklamak", Context.MODE_PRIVATE);
        et = (EditText)findViewById(R.id.et_id);
        tv_yas = (TextView)findViewById(R.id.tv);
        btn_save = (Button)findViewById(R.id.btn_kayit);
        btn_sil = (Button)findViewById(R.id.btn_sil);

        //şimdi de button ile kaydedilen veriyi proje açıldığı zaman geri çağıracağım

        // burada verdiğim kayıtlı bir değer yok ise 0 değerini ver dedim
      int age =   sharedPreferences.getInt("yas",0);
    if(age == 0){
        tv_yas.setText("Yasınız :");
    }
    else{
        tv_yas.setText("Yaşınız :" + age);
    }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //eğer kullanıcı bana girdi olaarak hiçbirşey vermediyse
                if(et.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Lütfen yaşınızı giriniz",Toast.LENGTH_SHORT).show();
              }
                else{
                    int age = Integer.parseInt(et.getText().toString());
                    tv_yas.setText("User Age  : "+age);

                    // veri artık veri tabanına eklendi app kapansa da ulaşıyorum
                    // veriyi güncellemek için yeni birşey yapmama gerek yok bu kod aynı şekilde çalışacak
                    sharedPreferences.edit().putInt("yas",age).apply();

                }
            }
        });
    btn_sil.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int storedAge = sharedPreferences.getInt("yas",0);
            if(storedAge == 0){
                Toast.makeText(MainActivity.this,"Silinecek veri yok",Toast.LENGTH_SHORT).show();
            }
            else{
                    sharedPreferences.edit().remove("yas").apply();
                Toast.makeText(MainActivity.this, "Silme İşlemi gerçekleştirildi", Toast.LENGTH_SHORT).show();
            }
        }
    });

    }
}