package com.fan.collect.kt

class Interfaces {

}

class Waiter(name: String, age: Int) : Person(name, age), Study {
    override fun readBooks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doHomework() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class Au : Study {
    override fun readBooks() {

    }

    override fun doHomework() {

    }
}
public interface LifecycleOwner {
    public val lifecycle: String
}

fun main() {

    val s:Study = object:Study{

        override fun readBooks() {
            TODO("Not yet implemented")
        }

        override fun doHomework() {
            TODO("Not yet implemented")
        }

    }
    s.deflll()// Study.DefaultImpls.deflll(this);
   Runnable {

   }
    Study2{

    }
    object :Study2{
        override fun readBooks() {
        }
    }
    object :Study{
        override fun readBooks() {
        }

        override fun doHomework() {
        }
    }
}

fun testStudy(s:Study2){
    s.readBooks()
}

fun interface Study2{
    fun readBooks()
}
interface Study {
    fun readBooks()
    fun doHomework()
    fun deflll() {

    }
}
/*
    public interface Study {
        void readBooks();

        void doHomework();

        void deflll();

        @Metadata(mv = {1, 6, 0}, k = 3, xi = 48)
        public static final class DefaultImpls {
            public static void deflll(@NotNull Study this) {
                Intrinsics.checkNotNullParameter(this, "this");
            }
        }
    }
 */