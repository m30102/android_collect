package com.xx.qq;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class Myplugin2 implements Plugin <Project>{

    @Override
    public void apply(Project project) {
        System.out.println("in Myplugin2 ");
        System.out.println("project.gradle.startParameter.taskNames");
        System.out.println("in Myplugin2 ");
       /* project.task("").doLast(t->{
        });*/
//        project.getTasks().register("",t->{});
        project.task("taskMyplugin2",task -> {
            task.setGroup("aaaa");
            task.doLast(t->{
                System.out.println("intaskMyplugin2");
            });
        });

    }
}
