#include<stdio.h>
#include<iostream>
#include "../../include/head.h"
/**
c++ c混编需要定义extern "C"
    #ifdef __cplusplus
    extern "C" {

    #ifdef __cplusplus
    }
    #endif
*/

int main()
{   
    
    int a = 20;
    int b = 12;
    printf("a = %d, b = %d\n", a, b);
    printf("a + b = %d\n", add(a, b));
    printf("a - b = %d\n", subtract(a, b));
    printf("a * b = %d\n", multiply(a, b));
    printf("a / b = %f\n", divide(a, b));
    
    std::cout << "Hello, CMake!!!" << std::endl;
    system("pause");
    return 0;
}
