# https://subingwen.cn/cmake/CMake-primer/#3-%E9%A2%84%E5%AE%9A%E4%B9%89%E5%AE%8F

# C:\work\ide\android_sdk\cmake\3.22.1
# C:\work\ide\android_sdk\cmake\3.22.1\bin\cmake   cd到build目录  cmake .. -G "MinGW Makefiles"  mingw的mingw32-make.exe改为make.exe
# android NDK选择
#https://developer.android.google.cn/studio/projects/add-native-code?hl=zh-cn
cmake_minimum_required(VERSION 3.22.1)# 指定使用的 cmake 的最低版本
project(CALC)#定义工程名称
set(CMAKE_CXX_STANDARD 11)




# set(SRC_LIST add.c div.c main.c mult.c sub.c) 用file代替
include_directories(${PROJECT_SOURCE_DIR}/include)# 必须带上头文件
file(GLOB SRC_LIST  ${CMAKE_CURRENT_SOURCE_DIR}/src/*.c ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)
# file(GLOB SRC_LIST  ${CMAKE_CURRENT_SOURCE_DIR}/src/*.c*)
MESSAGE(WARNING ${SRC_LIST})# 打印信息

set(EXECUTABLE_OUTPUT_PATH ../)# 可执行文件生成路径 ../ 基于makefile文件的位置
#add_executable(可执行程序名 源文件名称) 源文件名可以是一个也可以是多个，如有多个可用空格或;间隔
add_executable(app ${SRC_LIST})

set(LIBRARY_OUTPUT_PATH ../)
add_library(calc STATIC ${SRC_LIST}) # 静态库
# add_library(calcs SHARED ${SRC_LIST}) # 动态库

# 自定义 DEBUG 宏
add_definitions(-DDEBUG)