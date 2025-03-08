package com.fan.frame.resource;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class SkinFactory implements LayoutInflater.Factory2 {
    // 需要换肤的view
    private static final String[] prxfixlist = {
        "android.widget.",
        "android.view.",
        "android.webkit."

    };
    class SkinView{
        View view;
        // 需要换肤的属性的集合
        List<SkinItem> skinItems;
        public SkinView(View view, List<SkinItem> skinItem) {
            this.view = view;
            this.skinItems = skinItem;
        }
        // 对view进行换肤
        public void apply(){
            for (SkinItem skinItem : skinItems) {
                // 判断此条属性是否是background
                if(skinItem.getName().equals("background")){
                    if(skinItem.getTypeName().equals("color")){
                        view.setBackgroundColor(SkinManager.getInstance().getColor(skinItem.resId));
                    }else if(skinItem.getTypeName().equals("drawable")||skinItem.getTypeName().equals("mipmap")){
                        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN){
                            view.setBackground(SkinManager.getInstance().getDrawable(skinItem.getResId()));
                        }else{
                            view.setBackgroundDrawable(SkinManager.getInstance().getDrawable(skinItem.getResId()));
                        }
                    }
                }else if(skinItem.getName().equals("src")){
                    if(skinItem.getTypeName().equals("drawable")||skinItem.getTypeName().equals("mipmap")){
//                        ((ImageView)view).setImageResource(SkinManager.getInstance().getDrawableId(skinItem.getResId()));
                        ((ImageView)view).setImageDrawable(SkinManager.getInstance().getDrawable(skinItem.getResId()));
                    }
                }else if(skinItem.getName().equals("textColor")){
                    ((TextView)view).setTextColor(SkinManager.getInstance().getColor(skinItem.getResId()));
                }

            }
        }
    }

    public void apply(){
        for (SkinView skinView : viewList) {

            skinView.apply();
        }
    }
    private List<SkinView> viewList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.e("test---","name:"+name);
        View view  = null;
        if(name.contains(".")){
            // 带包名的自定义view
            view = onCreateView(name,context,attrs);
        }else{
            // 不带包名
            for (String s : prxfixlist) {
                String viewname = s+name;
                view  = onCreateView(viewname,context,attrs);
                if(view !=null)break;
            }
        }
        // 收集需要换肤的控件,判断有无xx属性
        if(view != null){
            parseView(view,name,attrs);
        }
        return view;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = null;
        try {
            Class<?> aClass = context.getClassLoader().loadClass(name);
            Constructor<? extends View> constructor = (Constructor<? extends View>) aClass.getDeclaredConstructor(Context.class, AttributeSet.class);
            view = constructor.newInstance(context, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    class SkinItem{
        // 属性名 textColor text background
        String name;
        // 属性值的类型 color mipmap
        String typeName;
        // 属性的值的名字 colorPrimary
        String entryName;
        // 属性的资源ID
        int resId;
        public SkinItem(String name, String typeName, String entryName, int resId) {
            this.name = name;
            this.typeName = typeName;
            this.entryName = entryName;
            this.resId = resId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getEntryName() {
            return entryName;
        }

        public void setEntryName(String entryName) {
            this.entryName = entryName;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }
    }

        /**
         <TextView
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:textColor="@color/colorPrimary"
             android:text="这是文字"
             android:background="@color/colorAccent"
         ></TextView>
        */
    private void parseView(View view, String name, AttributeSet attrs) {
        List<SkinItem> skinItems = new ArrayList<>();
        for(int i=0;i <attrs.getAttributeCount();i++){
            String attributeName = attrs.getAttributeName(i);
            if(attributeName.contains("background") || attributeName.contains("textColor")){
                // 属性的值的资源ID  android:background="@color/colorAccent" @2131034155
                //                 android:textColor="@color/colorPrimary" @2131034156
                String attributeValue = attrs.getAttributeValue(i);
                // int形的资源id
                int resId = Integer.parseInt(attributeValue.substring(1));
                // 获取到属性的值的名字 colorAccent colorPrimary
                String resourceEntryName = view.getResources().getResourceEntryName(resId);
                String resourceTypeName = view.getResources().getResourceTypeName(resId);
                /**
                 * public static final class color {
                 *      public static final int colorAccent = 2131034155;
                 *      public static final int colorPrimary = 2131034156;
                 * }
                 */
                //attributeValue:@2131034155 resId:2131034155 resourceEntryName:colorAccent resourceTypeName:color
                //attributeValue:@2131034156 resId:2131034156 resourceEntryName:colorPrimary resourceTypeName:color
                Log.e("test---","attributeValue:"+attributeValue+" resId:"+resId+" resourceEntryName:"+resourceEntryName+" resourceTypeName:"+resourceTypeName);

                SkinItem skinItem = new SkinItem(attributeName,resourceTypeName,resourceEntryName,resId);
                skinItems.add(skinItem);

            }
        }
        if(skinItems.size() >0){
            SkinView skinView = new SkinView(view,skinItems);
            viewList.add(skinView);
        }

    }



}
