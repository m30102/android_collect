package com.fan.collect.kt;


public class JavaCallKt {

    public static void main(String[] args) {
        staticCall();
        KtClass ktClass = new KtClass();
        ktClass.funckt();
        System.out.println("C9:71:17:76:B8:71:DC:1D:73:17:B4:F1:23:EA:ED:68".replaceAll(":",""));
        // C9:71:17:76:B8:71:DC:1D:73:17:B4:F1:23:EA:ED:68
    }

    private static void staticCall() {
        HelperKt.doSomething();
        Util.func2();
        ConstantsMt.INSTANCE.func1();
        String a = Util.asd;
        String b = ConstantsMt.img_dir;
        String c = HelperKt.key_loginInfo;
    }
}
