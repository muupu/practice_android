package com.example.openfiledialog;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    static private int openfileDialogId = 0;

    EditText selectedPathEditText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedPathEditText = (EditText)findViewById(R.id.editText);

        final OpenFileDialog openFileDialog = new OpenFileDialog(this);
        openFileDialog.setOnCloseListener(new OpenFileDialog.OnCloseListener() {
            @Override
            public void onSelected(String selectedFile) {
                selectedPathEditText.setText(selectedFile);
            }
        });

        // 设置单击按钮时打开文件对话框
        findViewById(R.id.button_openfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                openFileDialog.setTitle("Select a File...");
                openFileDialog.show();
            }
        });
    }
}
