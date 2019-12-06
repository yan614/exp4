package com.example.exp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private EditText name,number;
    private Button write,read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        number=findViewById(R.id.number);
        write=findViewById(R.id.write);
        read=findViewById(R.id.read);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out=null;
                try{
                    //使用openFileOutput()获得FileOutputStream对象，如果mode为MODE_PRIVATE，则文件不存在时创建文件，文件存在时删除文件内容；
                    // 如果mode为MODE_APPEND，则文件不存在时创建文件，文件存在时在最后追加。
                    // FileOutputStream 文件输出流
                    FileOutputStream fileOutputStream = openFileOutput("MyFileName",MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);//缓存输入流
                    String content="number:"+number.getText().toString()+"  name:"+name.getText().toString();
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out!=null)//需要out所做的工作已经做完了，判断是否需要关闭
                            out.close();
                    }
                }
                catch (Exception e){
                    //当try语句中出现异常是时，会执行catch中的语句
                    e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因。
                }

            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in=null;
                try{
                    FileInputStream fileInputStream=openFileInput("MyFileName");
                    in=new BufferedInputStream(fileInputStream);//缓冲字节输入流
                    int c;
                    //可变的字符序列
                    StringBuilder stringbuilder=new StringBuilder("");
                    try{
                        while((c=in.read())!=-1){//=-1代表数据读取完毕，将输入流的值赋给c
                            //追加
                            stringbuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stringbuilder.toString(),Toast.LENGTH_LONG).show();
                    }
                    finally{
                        if(in!=null)
                            in.close();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
