package com.ps.eachgold.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 存储各种文件
 */

public class SPutils {
    /**
     * 保存在手机的文件名
     */
    private static final String FILE_NAME="eachGoldData";

    /**
     * 保存文件
     */
    public static void put(Context context, String key, Object object){
        SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(object instanceof String){
            editor.putString(key, (String) object);
        }else if(object instanceof Integer){
            editor.putInt(key, (Integer) object);
        }else if(object instanceof Boolean){
            editor.putBoolean(key, (Boolean) object);
        }else if(object instanceof Float){
            editor.putFloat(key, (Float) object);
        }else if(object instanceof Long){
            editor.putLong(key, (Long) object);
        }else{
            editor.putString(key,object.toString());
        }
        editor.commit();
    }

    /**
     * 查找文件
     */
    public static Object get(Context context, String key, Object defaultValus){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if(defaultValus instanceof String){
            return sp.getString(key, (String) defaultValus);
        }else if(defaultValus instanceof Integer){
            return sp.getInt(key, (Integer) defaultValus);
        }else if(defaultValus instanceof Boolean){
            return sp.getBoolean(key, (Boolean) defaultValus);
        }else if(defaultValus instanceof Float){
            return sp.getFloat(key, (Float) defaultValus);
        }else if(defaultValus instanceof Long){
            return sp.getLong(key, (Long) defaultValus);
        }else{
            return sp.getString(key, (String) defaultValus);
        }
    }

    /**
     * 删除某个值
     */
    public static void delete(Context context, String key){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove(key);
        editor.commit();

    }

    /**
     * 删除除手机号之外的值
     */
    public static void deleteButPhone(Context context){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("login");//登录判断
        editor.remove("info");//登录判断
        editor.remove("phone");//手机
        editor.remove("sessionid");//图片域名
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context context){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.commit();
    }

}
