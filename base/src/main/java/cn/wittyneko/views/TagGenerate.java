package cn.wittyneko.views;

/**
 * Created by Brady on 2016/7/7.
 */
public class TagGenerate {

    //生成获取ViewTag
    public static class Tag{
        public static int tag = -1;
        public static int systemTag = Integer.MIN_VALUE;
        public static final int VIEW_HOLDER = TagGenerate.Tag.obtainSystemTag();
        public static final int POSITION = TagGenerate.Tag.obtainSystemTag();
        public static final int minTag = obtainSystemTag();

        public synchronized static int obtainTag() {
            return tag--;
        }

        public synchronized static int obtainSystemTag() {
            return systemTag++;
        }

        public static boolean isUsed(int tag){
            if (tag <= Tag.tag && tag >= systemTag)
                return false;
            else
                return true;
        }
    }
}
