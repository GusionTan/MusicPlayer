package com.example.test12;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChooseAreaFragment extends Activity{
    private File[] songFiles;
    private ArrayAdapter<String> adapter;   //音乐列表
    List<String> datalist = new ArrayList<>();
    String song_path = "/storage/emulated/0/Music/谭T调.mp3";
    final MediaPlayer mp = new MediaPlayer();
    private String currentposition;
    private ListView listView;
    private Button backbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area_fragment);
        backbtn = (Button) findViewById(R.id.back_button);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseAreaFragment.this,MusicActivity.class);
                currentposition = "0";
                intent.putExtra("cposition",currentposition);
                intent.putExtra("spath",song_path);
                startActivity(intent);
            }
        });

        File sdpath = Environment.getExternalStorageDirectory(); //获得手机SD卡路径
        File path = new File(sdpath + "//Music//");      //获得SD卡的mp3文件夹
        //返回以.mp3结尾的文件 (自定义文件过滤)
        songFiles = path.listFiles(new Tanr(".mp3"));
        if(songFiles != null){
            for(File file:songFiles){
                String str = file.getAbsolutePath();
                String str1 = str.substring(str.indexOf("谭")+1,str.indexOf(".mp3"));
                datalist.add(str1);//获取文件的绝对路径
            }
        }else{
            String s1 = "没有扫描到歌曲";
            datalist.add(s1);
        }

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(ChooseAreaFragment.this,
                android.R.layout.simple_list_item_single_choice,
                datalist);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                song_path = "/storage/emulated/0/Music/谭" + ((TextView) view).getText().toString() + ".mp3";
                currentposition = String.valueOf(position);
                Intent intent =new Intent(ChooseAreaFragment.this,MusicActivity.class);
                intent.putExtra("cposition",currentposition);
                intent.putExtra("spath",song_path);
                startActivity(intent);

            }
        });
    }

}