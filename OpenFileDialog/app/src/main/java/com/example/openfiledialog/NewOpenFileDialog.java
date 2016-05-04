package com.example.openfiledialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by qiaoda.zqd on 2016/3/11.
 */


public class NewOpenFileDialog extends Dialog {



    public static String tag = "OpenFileDialog";
    static final public String sRoot = "/";
    static final public String sParent = "..";
    static final public String sFolder = ".";
    static final public String sEmpty = "";
    static final private String sOnErrorMsg = "No rights to access!";

    private OnCloseListener mOnCloseListener = null;
    Context mContext;

    public NewOpenFileDialog(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public NewOpenFileDialog(Context context, OnCancelListener cancelListener) {
        super(context, true, cancelListener);
        mContext = context;
        init();
    }

    private void init(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map<String, Integer> images = new HashMap<String, Integer>();
        // 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
        images.put(NewOpenFileDialog.sRoot, R.drawable.filedialog_root);	// 根目录图标
        images.put(NewOpenFileDialog.sParent, R.drawable.filedialog_folder_up);	//返回上一层的图标
        images.put(NewOpenFileDialog.sFolder, R.drawable.filedialog_folder);	//文件夹图标
        images.put("wav", R.drawable.filedialog_wavfile);	//wav文件图标
        images.put(NewOpenFileDialog.sEmpty, R.drawable.filedialog_file);
        setContentView(new FileSelectView(mContext, this, mOnCloseListener, null, images));

    }

    public void setOnCloseListener(OnCloseListener onCloseListener){
        this.mOnCloseListener = onCloseListener;
    }

    static class FileSelectView extends ListView implements OnItemClickListener{


        private OnCloseListener mOnCloseListener = null;
        private String path = sRoot;
        private List<Map<String, Object>> list = null;
        private Dialog dialog = null;

        private String suffix = null;

        private Map<String, Integer> imagemap = null;

        public FileSelectView(Context context, Dialog dialog, OnCloseListener callback, String suffix, Map<String, Integer> images) {
            super(context);
            this.imagemap = images;
            this.suffix = suffix==null?"":suffix.toLowerCase();
            this.mOnCloseListener = callback;
            this.dialog = dialog;
            this.setOnItemClickListener(this);
            refreshFileList();
        }

        private String getSuffix(String filename){
            int dix = filename.lastIndexOf('.');
            if(dix<0){
                return "";
            }
            else{
                return filename.substring(dix+1);
            }
        }

        private int getImageId(String s){
            if(imagemap == null){
                return 0;
            }
            else if(imagemap.containsKey(s)){
                return imagemap.get(s);
            }
            else if(imagemap.containsKey(sEmpty)){
                return imagemap.get(sEmpty);
            }
            else {
                return 0;
            }
        }

        private int refreshFileList()
        {
            // 刷新文件列表
            File[] files = null;
            try{
                files = new File(path).listFiles();
            }
            catch(Exception e){
                files = null;
            }
            if(files==null){
                // 访问出错
                Toast.makeText(getContext(), sOnErrorMsg,Toast.LENGTH_SHORT).show();
                return -1;
            }
            if(list != null){
                list.clear();
            }
            else{
                list = new ArrayList<Map<String, Object>>(files.length);
            }

            // 用来先保存文件夹和文件夹的两个列表
            ArrayList<Map<String, Object>> lfolders = new ArrayList<Map<String, Object>>();
            ArrayList<Map<String, Object>> lfiles = new ArrayList<Map<String, Object>>();

            if(!this.path.equals(sRoot)){
                // 添加根目录 和 上一层目录
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", sRoot);
                map.put("path", sRoot);
                map.put("img", getImageId(sRoot));
                list.add(map);

                map = new HashMap<String, Object>();
                map.put("name", sParent);
                map.put("path", path);
                map.put("img", getImageId(sParent));
                list.add(map);
            }

            for(File file: files)
            {
                if(file.isDirectory() && file.listFiles()!=null){
                    // 添加文件夹
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", file.getName());
                    map.put("path", file.getPath());
                    map.put("img", getImageId(sFolder));
                    lfolders.add(map);
                }
                else if(file.isFile()){
                    // 添加文件
                    String sf = getSuffix(file.getName()).toLowerCase();
                    if(suffix == null || suffix.length()==0 || (sf.length()>0 && suffix.indexOf("."+sf+";")>=0)){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("name", file.getName());
                        map.put("path", file.getPath());
                        map.put("img", getImageId(sf));
                        lfiles.add(map);
                    }
                }
            }

            list.addAll(lfolders); // 先添加文件夹，确保文件夹显示在上面
            list.addAll(lfiles);	//再添加文件


            SimpleAdapter adapter = new SimpleAdapter(getContext(), list, R.layout.filedialogitem, new String[]{"img", "name", "path"}, new int[]{R.id.filedialogitem_img, R.id.filedialogitem_name, R.id.filedialogitem_path});
            this.setAdapter(adapter);
            return files.length;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            // 条目选择
            String pt = (String) list.get(position).get("path");
            String fn = (String) list.get(position).get("name");
            if(fn.equals(sRoot) || fn.equals(sParent)){
                // 如果是更目录或者上一层
                File fl = new File(pt);
                String ppt = fl.getParent();
                if(ppt != null){
                    // 返回上一层
                    path = ppt;
                }
                else{
                    // 返回更目录
                    path = sRoot;
                }
            }
            else{
                File fl = new File(pt);
                if(fl.isFile()){
                    // 如果是文件
//                    ((Activity)getContext()).dismissDialog(this.dialogid); // 让文件夹对话框消失
                    this.dialog.dismiss();

                    // 设置回调的返回值
//                    Bundle bundle = new Bundle();
//                    bundle.putString("path", pt);
//                    bundle.putString("name", fn);
                    // 调用事先设置的回调函数
//                    this.callback.callback(bundle);

                    if (mOnCloseListener != null) {
                        mOnCloseListener.onSelected(pt);
                    }
                    return;
                }
                else if(fl.isDirectory()){
                    // 如果是文件夹
                    // 那么进入选中的文件夹
                    path = pt;
                }
            }
            this.refreshFileList();
        }
    }

    public interface OnCloseListener {
        //void onCancel();
        void onSelected(String selectedFile);
    }
}
