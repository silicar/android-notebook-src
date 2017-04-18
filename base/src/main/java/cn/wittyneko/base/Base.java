package cn.wittyneko.base;

import android.app.Application;
import android.content.Context;

import java.lang.reflect.Method;


/**
 * 初始化
 * Created by wittyneko .
 * @since 2017/4/18
 */
public final class Base {
    
    private static boolean debug;
    private static Application app;

    private Base() {
    }

    public static void init(Application application) {
        if (app == null) {
            app = application;
        }
    }

    public static Application app() {
        if (app == null) {
            try {
                // 在IDE进行布局预览时使用
                Class<?> renderActionClass = Class.forName("com.android.layoutlib.bridge.impl.RenderAction");
                Method method = renderActionClass.getDeclaredMethod("getCurrentContext");
                Context context = (Context) method.invoke(null);
                app = new MockApplication(context);
            } catch (Throwable ignored) {
                throw new RuntimeException("please invoke Base.init(app) on Application#onCreate()"
                        + " and register your Application in manifest.");
            }
        }
        return app;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static class Builder {

        Base mUtils;

        private Builder() {
            mUtils = new Base();
        }

        public Builder setDebug(boolean debug) {
            mUtils.debug = debug;
            return this;
        }
    }

    private static class MockApplication extends Application {
        public MockApplication(Context baseContext) {
            this.attachBaseContext(baseContext);
        }
    }
}
