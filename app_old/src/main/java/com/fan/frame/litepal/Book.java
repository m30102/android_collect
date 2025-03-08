package com.fan.frame.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * id字段的值始终为当前记录的行号（下标从1开始），即使我们在实体类中定义了int或者long类型的id字段，
 * 在添加数据时人为的设置id的值为100等其他值，查询数据库发现该id字段的值设置是无效的，她始终等于该条记录所在的行id，即第几条记录
 */
public class Book extends LitePalSupport {

    private int id;

    private String author;

    private double price;

    private int pages;

    private String name;

    private String press;

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
