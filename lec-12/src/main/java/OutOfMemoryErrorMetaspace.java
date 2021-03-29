import javassist.ClassPool;

public class OutOfMemoryErrorMetaspace {

    static ClassPool classPool = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000000; i++) {

            Class clazz = classPool.makeClass(
                    i + " outofmemory.OutOfMemoryErrorMetaspace ").toClass();
            System.out.println(clazz.getName());
        }
    }
}
