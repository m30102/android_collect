package com.fan.collect.module.androidversion.storage

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import com.blankj.utilcode.util.FileIOUtils
import com.fan.collect.module.androidversion.R
import com.fan.collect.module.androidversion.databinding.ActivityMainBinding
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

/**
 * https://github.com/android/storage
 * https://zhuanlan.zhihu.com/p/402819748
 * https://open.oppomobile.com/wiki/doc#id=10724
 * https://zhuanlan.zhihu.com/p/354632087
 * https://blog.csdn.net/u013040819/article/details/104909259
 */

class StorageActivity1 : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        XXPermissions.with(this).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE).request { permissions, all ->
            if (all) {
                Log.d(TAG,"granted all")
            }
        }
        // /data/user/0/com.fan.collect.module.androidversion/files
        Log.d(TAG,"内部路径"+filesDir.absolutePath)
        initView()
    }

    private fun initView() {
        binding.readNormal1.setOnClickListener {
//            readFile("/sdcard/share_test_assets.txt")// EACCES (Permission denied)
            readFile("/sdcard/share_test.png")// 成功 可以直接bitmapfactory
//            readImg("/sdcard/share_test_assets.txt")// EACCES (Permission denied)
//            copyFile("/sdcard/share_test.txt","/sdcard/share_test2.txt") // EACCES (Permission denied)
//            copyAssetsFileToSdcard(this,"share_test_assets.txt","/sdcard/share_test_assets.txt")// EPERM (Operation not permitted)
        }
        binding.readNormal2.setOnClickListener {
//            readFile("/sdcard/Documents/share_test.txt")// EACCES (Permission denied)
//            copyFile("/sdcard/Documents/share_test.txt","/sdcard/Documents/share_test2.txt") // EACCES (Permission denied)
            copyAssetsFileToSdcard(this,"share_test_assets.txt","/sdcard/Download/share_test_assets1.txt")// success 一次，手动删除第二次失败
//            readFile("/sdcard/Documents/share_test_assets1.txt")// 读取本应用上面代码创建的成功，其他创建的此文件失败
//            copyFile("/sdcard/Documents/share_test_assets1.txt","/sdcard/Documents/share_test_assets2.txt") //  success 一次， copy其他应用创建的则 EACCES (Permission denied)
        }
        binding.createNormal.setOnClickListener {
            createNormal()
        }
        binding.createImg.setOnClickListener {
            createImage()
        }
        binding.queryImg.setOnClickListener {
            queryImg()
        }
        binding.deleteImg.setOnClickListener {
            val row = contentResolver.delete(queryUri!!, null, null)
            if(row >0){
                Log.e(TAG,"删除成功 "+row)
            }
        }
        binding.modifyImg.setOnClickListener{
            updateImg()
        }


    }

    private fun readImg(s: String) {
        val file = File("/sdcard/share_test2.jpg")
        Log.e(TAG,"img file:"+file.exists())
        val decodeFile = BitmapFactory.decodeFile("/sdcard/share_test2.jpg")
        binding.img.setImageBitmap(decodeFile)
    }

    private fun copyAssetsFileToSdcard(context: Context, assetsFileName: String, desPath: String): Boolean {
        val file = File(desPath)
        Log.e(TAG,"当前已存在？"+file.exists())
        if (file.exists()) {
            Log.e(TAG,"当前已存在")
            return true
        }
        //将内容写入到文件中
        var `in`: InputStream? = null
        var out: FileOutputStream? = null
        try {
            `in` = context.assets.open(assetsFileName)
            out = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var byteCount = 0
            while (`in`.read(buffer).also { byteCount = it } != -1) {
                out.write(buffer, 0, byteCount)
            }
            out.flush()
            Log.e(TAG,"复制成功")
            return true
        } catch (e: Exception) {
            Log.e(TAG, "异常 $e")
            e.printStackTrace()
        } finally {
            try {
                `in`?.close()
                out?.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        Log.e(TAG,"复制失败")
        return false
    }

    private fun readFile(filePath: String) {
        val file = File(filePath)
        Log.e(TAG,"file:"+file.exists())
//        val readText = FileIOUtils.readFile2String(file)
//        Log.e(TAG,"readText:"+readText)
        val fileReader = FileReader(file)
        val readText = fileReader.readText()
        Log.e(TAG,"readText:"+readText)
    }
    private fun copyFile(src:String, des:String){
        val srcFile = File(src)
        Log.e(TAG,"srcFile exists:"+srcFile.exists())
        val copy = com.blankj.utilcode.util.FileUtils.copy(src, des)
        Log.e(TAG,"copy:"+copy)
    }

    private fun updateImg() {

        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME,"zzz.png")
        val update = contentResolver.update(queryUri!!, contentValues, null, null)
        if(update >0){
            Log.e(TAG,"修改成功 "+update)
        }
    }

    var queryUri: Uri? = null

    private fun queryImg() {

        val externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Images.Media.DISPLAY_NAME+ "=?"
        val arrayOfImg = arrayOf("Zee2.png")
        //1 查询哪张表的URI 指定的列 where  ?的值  排序方式
        val cursor = contentResolver.query(externalContentUri, null, selection, arrayOfImg, null)
        if(cursor != null && cursor.moveToFirst()){
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
            queryUri = ContentUris.withAppendedId(externalContentUri, id)
            Log.e(TAG,"查询成功 "+queryUri)// content://media/external/images/media/41
            cursor.close()
        }

    }

    private fun createImage() {

        val imageName = "Zee2.png"
        val contentResolver = contentResolver
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.ImageColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES)
        contentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME,imageName)
        contentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE,"image/png")
        contentValues.put(MediaStore.Downloads.TITLE,imageName)
        val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test)
        val outputStream = contentResolver.openOutputStream(imageUri!!)
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
        outputStream?.close()

    }

    private fun createNormal() {

        // sdcard根目录 在android10上需要申请requestLegacyExternalStorage可用file操作,但android11不行
//        val file  =  File("/sdcard/demo.txt")
        // Download  Documents目录 在android11上无需申请requestLegacyExternalStorage也可用file任意操作,但是只能当前应用操作，当前应用卸载了重装也不能操作了。
        // Music只能放.mp3文件(假的也行)

//        val file  =  File("/sdcard/Music/demo.java")//

       val file  =  File("/sdcard/zxc5.txt")//
//        if(!file.exists()){
        val createNewFileSuc = file.createNewFile()
        Log.e(TAG,"createNewFileSuc:"+createNewFileSuc)
//        }

        val outputStream = FileOutputStream(file)
        outputStream.write("asdasdasdcc".toByteArray())
        outputStream.close()

        val fileReader = FileReader(file)
        val readText = fileReader.readText()
        Log.e(TAG,"readText:"+readText)


        // in android11
       /* val contentUri = MediaStore.Files.getContentUri("external")
        val contentValues = ContentValues()
        val path = Environment.DIRECTORY_DOWNLOADS+"/zx.txt"
        contentValues.apply {
            put(MediaStore.Downloads.RELATIVE_PATH,path)
            put(MediaStore.Downloads.DISPLAY_NAME,"zx.txt")
            put(MediaStore.Downloads.TITLE,"zx.txt")
        }
        val uri = contentResolver.insert(contentUri, contentValues)
        Log.e(TAG, "uri:$uri")
        val outputStream = contentResolver.openOutputStream(uri!!)
        val bos = BufferedOutputStream(outputStream)
        val content = "asd"
        bos.write(content.toByteArray())
        bos.close()*/


    }
}