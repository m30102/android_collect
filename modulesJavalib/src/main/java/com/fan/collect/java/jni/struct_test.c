#include<stdio.h>

void ToUpper(char *);// 字符串转大写的函数
void * pf (char*);// pf不带括号是返回一个指针的函数

void * pf (char* param){
    // void * pf (char*); 的实现
}

void ToUpper(char *s){
    printf("ToUpper %s\n",s);
}
void ToLower(char *s){
    printf("ToLower %s\n",s);
}

int main(void)
{

    void (*pf) (char*);
    struct student
    {
        char name[20];
        int age;
        char sex;
        void (*pf) (char*);// 函数指针，有返回类型和参数类型.  返回类型 (*函数名) (参数列表)
        
    };
    printf("\n");

    pf = ToLower;
    (*pf)("qwe");// pf是指向函数的指针，所以*运算后就是这个函数，然后传参调用 
    pf("qwe");// pf是指向函数的指针,但是可以互换函数名,所以直接等价于ToUpper（qwe）   

    pf = ToUpper;
    (*pf)("qwe");
    pf("qwe");

    struct student stu={"zhangsan",18,'m',ToUpper};// 函数名表示函数地址，直接赋值
    struct  student *s1 = &stu;
    printf("\n1 name=%s,age=%d,sex=%c\n",stu.name,stu.age,stu.sex);// 直接访问结构成员
    printf("\n2 name=%s,age=%d,sex=%c\n",s1->name,s1->age,s1->sex);// 指针方式访问结构成员
    printf("\n3 name=%s,age=%d,sex=%c\n",(*s1).name,(*s1).age,(*s1).sex);// 指针 *运算 后直接访问结构成员
    (*stu.pf)("123");
    stu.pf("123");
    s1->pf("123");
   
  

    return 0;
}