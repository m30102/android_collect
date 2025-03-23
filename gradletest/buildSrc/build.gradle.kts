plugins {
    id ("groovy")//  使用 Groovy 语言开发必备；
    id("java-gradle-plugin")//用于帮助开发 Gradle 插件，会自动应用 Java Library 插件，并在 dependencies 中添加 implementation gradleApi()。
    id("org.jetbrains.kotlin.jvm") version "1.9.0"//使用 Kotlin 语言开发必备；
    id("maven-publish")
}
// https://zhuanlan.zhihu.com/p/515455819
//https://blog.csdn.net/qq_21159963/article/details/146002749
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
//    implementation(gradleApi())
//    implementation(localGroovy())
}

// id("java-gradle-plugin")
gradlePlugin{
    plugins {   //这里可以定义多个插件
        create("MyPlugin2") {      //这里是定义一个插件
            version = "3.0.0"                 //这是插件版本
            id = "org.my.plugin"      //这是插件id，其他项目引用插件时写这个id 和上面的version
            implementationClass = "com.xx.qq.Myplugin2"    //插件对应的代码
        }
    }
}


/*publishing {
    repositories {     //自定义好的插件推送到我自己的仓库
        maven {
            name = "my-nexus-releases"
            url = uri("http://110.110.110.100:8081/repository/maven-releases/")
            isAllowInsecureProtocol = true
             groupId = 'org.my.plugin'
            artifactId = 'plugin'
            version = '3.0.0' // 必须与 gradlePlugin {} 中配置的版本一致
            version = '3.0.0' // 必须与 gradlePlugin {} 中配置的版本一致
            credentials {
                username ="admin"
                password ="123456"
            }
        }
    }
}*/

repositories {
    maven { setUrl("https://jitpack.io") }
    google()
    mavenCentral()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
