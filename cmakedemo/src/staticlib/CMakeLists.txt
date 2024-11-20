# https://subingwen.cn/cmake/CMake-primer/#3-%E9%A2%84%E5%AE%9A%E4%B9%89%E5%AE%8F

# C:\work\ide\android_sdk\cmake\3.22.1
# C:\work\ide\android_sdk\cmake\3.22.1\bin\cmake   cmake .. -G "MinGW Makefiles"  mingw的mingw32-make.exe改为make.exe
cmake_minimum_required(VERSION 3.22.1)# 指定使用的 cmake 的最低版本
project(CALC)#定义工程名称
set(CMAKE_CXX_STANDARD 11)



file(GLOB SRC_LIST  ${CMAKE_CURRENT_SOURCE_DIR}/*.cpp)
include_directories(${PROJECT_SOURCE_DIR})
MESSAGE(WARNING ${SRC_LIST})#

link_directories(${PROJECT_SOURCE_DIR})
link_libraries(calc)

set(EXECUTABLE_OUTPUT_PATH ../)
add_executable(appmk ${SRC_LIST})


