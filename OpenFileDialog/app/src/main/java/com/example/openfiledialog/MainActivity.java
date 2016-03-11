package com.example.openfiledialog;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity {

    static private int openfileDialogId = 0;

    EditText selectedPathEditText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedPathEditText = (EditText)findViewById(R.id.editText);

        final NewOpenFileDialog openFileDialog = new NewOpenFileDialog(this);
        openFileDialog.setOnCloseListener(new NewOpenFileDialog.OnCloseListener() {
            @Override
            public void onSelected(String selectedFile) {
                selectedPathEditText.setText(selectedFile);
            }
        });

        // 设置单击按钮时打开文件对话框
        findViewById(R.id.button_openfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
//                showDialog(openfileDialogId);
                openFileDialog.setTitle("Select a File...");
                openFileDialog.show();
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==openfileDialogId){
            Map<String, Integer> images = new HashMap<String, Integer>();
            // 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
            images.put(OpenFileDialog.sRoot, R.drawable.filedialog_root);	// 根目录图标
            images.put(OpenFileDialog.sParent, R.drawable.filedialog_folder_up);	//返回上一层的图标
            images.put(OpenFileDialog.sFolder, R.drawable.filedialog_folder);	//文件夹图标
            images.put("wav", R.drawable.filedialog_wavfile);	//wav文件图标
            images.put(OpenFileDialog.sEmpty, R.drawable.filedialog_root);
            Dialog dialog = OpenFileDialog.createDialog(id, this, "打开文件", new OpenFileDialog.OnCloseListener() {
                        @Override
                        public void onCancel() {
                        }
                        @Override
                        public void onOk(String selectedFile) {
                            selectedPathEditText.setText(selectedFile);
                        }
                    },
                    null,
                    images);
            return dialog;
        }
        return null;
    }
}
