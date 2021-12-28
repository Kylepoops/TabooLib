package taboolib.common.env;

import sun.misc.Unsafe;
import taboolib.common.TabooLibCommon;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

/**
 * @author sky
 * @since 2020-04-12 22:39
 */
public class ClassAppender {

    static MethodHandles.Lookup lookup;
    static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            Field lookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            Object lookupBase = unsafe.staticFieldBase(lookupField);
            long lookupOffset = unsafe.staticFieldOffset(lookupField);
            lookup = (MethodHandles.Lookup) unsafe.getObject(lookupBase, lookupOffset);
        } catch (Throwable ignore) {
        }
    }

    ClassAppender() {
    }

    public static void addPath(Path path) {
        try {
            File file = new File(path.toUri().getPath());
            ClassLoader loader = TabooLibCommon.class.getClassLoader();
            if (loader.getClass().getName().equals("net.minecraft.launchwrapper.LaunchClassLoader")) {
                MethodHandle methodHandle = lookup.findVirtual(URLClassLoader.class, "addURL", MethodType.methodType(void.class, java.net.URL.class));
                methodHandle.invoke(loader, file.toURI().toURL());
            } else {
                Field ucpField;
                try {
                    ucpField = URLClassLoader.class.getDeclaredField("ucp");
                } catch (NoSuchFieldError | NoSuchFieldException e1) {
                    try {
                        ucpField = loader.getClass().getDeclaredField("ucp");
                    } catch (NoSuchFieldError | NoSuchFieldException e2) {
                        ucpField = loader.getClass().getSuperclass().getDeclaredField("ucp");
                    }
                }
                long ucpOffset = unsafe.objectFieldOffset(ucpField);
                Object ucp = unsafe.getObject(loader, ucpOffset);
                MethodHandle methodHandle = lookup.findVirtual(ucp.getClass(), "addURL", MethodType.methodType(void.class, URL.class));
                methodHandle.invoke(ucp, file.toURI().toURL());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static boolean isExists(String path) {
        try {
            Class.forName(path, false, TabooLibCommon.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }
}
