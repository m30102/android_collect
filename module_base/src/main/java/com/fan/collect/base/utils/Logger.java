package com.fan.collect.base.utils;


import android.text.TextUtils;
import android.util.Log;
import java.text.MessageFormat;

public class Logger {

	private static final String TAG_DEFAULT = "Logger";
	// 私有化，只调用静态方法
	private Logger() {
	}
	
	// 栈信息第5行为调用Logger.d等的地方
	private static final int STACK_TRACE_INDEX_5 = 6;
	private static final String SUFFIX = ".java";
	
	// 是否允许显示Log
	private static boolean SHOW_LOG_FLAG = true;
	// 全局tag
	private static String mGlobalTag;
	
	
	// Log等级
	private static final int V = 0x1;
	private static final int D = 0x2;
	private static final int I = 0x3;
	private static final int W = 0x4;
	private static final int E = 0x5;
	
	/** 换行符 */
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**异常打印顶行和底行*/
	private static final String START_STR = LINE_SEPARATOR+"╔════════════════════════════════════════════════════════════"+LINE_SEPARATOR;
	private static final String END_STR =   LINE_SEPARATOR+"╚════════════════════════════════════════════════════════════"+LINE_SEPARATOR;
	
	
	/**
	 * 是否允许显示log
	 * @param isShowLog
	 */
	public static void init(boolean isShowLog) {
		SHOW_LOG_FLAG = isShowLog;
	}
	public static void init(boolean isShowLog, String tag) {
		SHOW_LOG_FLAG = isShowLog;
		mGlobalTag = tag;
	}

	public static void v(String msg) {
		printLog(V, null, msg);
	}

	public static void v(String tag, String msg) {
		printLog(V, tag, msg);
	}

	public static void d(String msg) {
		printLog(D, null, msg);

	}

	public static void d(String tag, String msg) {
		printLog(D, tag, msg);
	}

	public static void i(String msg) {
		printLog(I, null, msg);

	}

	public static void i(String tag, String msg) {
		printLog(I, tag, msg);
	}

	public static void w(String msg) {
		printLog(W, null, msg);

	}

	public static void w(String tag, String msg) {
		printLog(W, tag, msg);
	}

	public static void e(String msg) {
		printLog(E, null, msg);
	}

	public static void e(String tag, String msg) {
		printLog(E, tag, msg);
	}

	public static void Ex(Throwable e) {
		if (e == null) {
			return;
		}
		printEx(null, MessageFormat.format("{0}", getStackTrace(e)));
	}

	public static void Ex(String tag, Throwable e) {
		printEx(tag, MessageFormat.format("{0}", getStackTrace(e)));
	}

	public static void Ex(String tag, Throwable e, String logMessage) {
		String attach = "";
		if (null != logMessage && !logMessage.isEmpty()) {
			attach = MessageFormat.format("\r\nOtherInfo:{0}", logMessage);
		}
		printEx(null, MessageFormat.format("{0}{1}", getStackTrace(e) + "__" + attach));
	}

	private static void printEx(String tagName, String message) {
		if (!SHOW_LOG_FLAG) {
			 return;
		}
		String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagName, message);
		String tag = contents[0];
		String msg = contents[1];
		String headString = contents[2];
		Log.e(tag,"ERROR:");
		Log.e(tag, START_STR);
		printDefault(E, tag, headString+msg);
		Log.e(tag, END_STR);
	}

	private static void printLog(int type, String tagName, String message) {

		if (!SHOW_LOG_FLAG) {
			 return;
		}
		String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagName, message);
		String tag = contents[0];
		String msg = contents[1];
		String headString = contents[2];
		printDefault(type, tag, headString + msg);
	}

	

	private static String[] wrapperContent(int stackTraceIndex, String tagStr, String msg) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement targetElement = stackTrace[stackTraceIndex];// com.example.androidtest2.MainActivity$1.onClick(MainActivity.java:26)
		String className = targetElement.getClassName();// com.example.androidtest2.MainActivity$1
		String[] classNameInfo = className.split("\\.");// [com, example,
														// androidtest2,
														// MainActivity$1]
		if (classNameInfo.length > 0) {
			className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
		}
		if (className.contains("$")) {
			className = className.split("\\$")[0] + SUFFIX;
		}
		String methodName = targetElement.getMethodName();// onClick
		int lineNumber = targetElement.getLineNumber();// 26
		if (lineNumber < 0) {
			lineNumber = 0;
		}
		String tag;

		if (!TextUtils.isEmpty(tagStr)) {
			tag = tagStr;
		} else if (!TextUtils.isEmpty(mGlobalTag)) {
			tag = mGlobalTag;
		} else {
			tag = TAG_DEFAULT;
		}
		String headString = "[ (" + className + ":" + lineNumber + ")#" + methodName + " ] ";
		//headString : [ (MainActivity.java:28)#onCreate ]
		return new String[] { tag, msg, headString };
	}

	private static final int MAX_LENGTH = 10000;

	private static void printDefault(int type, String tag, String msg) {

		int index = 0;
		int length = msg.length();
		int countOfSub = length / MAX_LENGTH;
		if (countOfSub > 0) {
			for (int i = 0; i < countOfSub; i++) {
				String sub = msg.substring(index, index + MAX_LENGTH);
				printSub(type, tag, sub);
				index += MAX_LENGTH;
			}
			printSub(type, tag, msg.substring(index, length));
		} else {
			printSub(type, tag, msg);
		}
	}

	/***
	 *
	 * @param t 异常对象
	 * @return  异常栈信息字符串
	 */
	public static String getStackTrace(Throwable t) {
		if (null == t) {
			return "Exception Is Null";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(t.getClass().getName());
		sb.append(" : ");
		sb.append(t.getMessage());
		sb.append("\r\n");
		StackTraceElement[] messages = t.getStackTrace();

		int length = messages.length;
		for (int i = 0; i < length; i++) {
			sb.append(String.valueOf(i));
			sb.append(" ");
			sb.append(messages[i].getClassName());
			sb.append(" - line ");
			sb.append(messages[i].getLineNumber());
			sb.append("\r\n");
		}
		return sb.toString();
	}

	private static void printSub(int type, String tag, String msg) {
		switch (type) {
		case V:
			Log.v(tag, msg);
			break;
		case D:
			Log.d(tag, msg);
			break;
		case I:
			Log.i(tag, msg);
			break;
		case W:
			Log.w(tag, msg);
			break;
		case E:
			Log.e(tag, msg);
			break;
		}
	}
/*
	public static void writeBusinessLog(String content){
		writeBusinessLogOrException(content,null);
	}
	///storage/emulated/0/Android/data/com.tencent.tmf.apollo/files/log/2021-09-09_log.txt content:发生异常
	public static final String log_info = Utils.getApp().getExternalFilesDir("").getAbsolutePath()+"/log/";

	public static void writeBusinessLogOrException(String content, Throwable t){
		try {
			File file = new File(log_info);
			if(file.exists() && file.isDirectory()){
				// 目录大于100M则清空
				if(com.blankj.utilcode.util.FileUtils.getLength(file) > 100 * 1024 *1024 && file.listFiles() !=null){
					com.blankj.utilcode.util.FileUtils.delete(file);
				}else{
					//                CcbLogger.v("当前目录大小:"+FileUtils.getLength(file));
				}
			}
			String filePath = log_info + TimeUtils.millis2String(System.currentTimeMillis(),"yyyy-MM-dd")+"_log.txt";
			v("记录日志,"+filePath+" content:"+content);
			if(t !=null ){
				content += "_" + getStackTrace(t);
			}
			writeToFile(filePath,content);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	private static void writeToFile(String filePath, String content){
		content="\r\n"+ TimeUtils.millis2String(System.currentTimeMillis())+":"+content;
		File file = new File(filePath);
		if(file.exists() && file.isFile()){
			// 文件大于1M则删除
			if(file.length() >= 1024 * 1024){
				file.delete();
				e("文件大小超限, 删除:"+filePath);
			}
		}
		FileIOUtils.writeFileFromString(file,content,true);
	}
*/


}
