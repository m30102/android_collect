#include<iostream>
// c++ 结构可以有成员函数

int main(){
    
    struct student
    {
        int num;

        void Set(int temp){
            num = temp;
        }
        
    };
    student s;
    s.Set(10);
    std::cout << "num = " << s.num << std::endl;
    student *p = &s;
    p->Set(20);
    std::cout << "num = " << s.num << std::endl;
    (*p).Set(30);
    std::cout << "num = " << s.num << std::endl;
    return 0;
}

// g++ struct_test2.cpp -o struct_test2.exe && struct_test2.exe