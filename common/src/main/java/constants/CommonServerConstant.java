package constants;

/**
 * Created by fan on 2016/12/26.
 *
 * Common中网络模块用到的常量
 */

public class CommonServerConstant {

    // 服务器的IP和端口
//    public static String SERVER_IP_PORT = "http://admin.houe.randioo.com";//正式环境
    public static String SERVER_IP_PORT = "http://140.207.2.182:1989";//测试环境
//
    // 服务器路径
    public static String SERVER_PATH = "/Admin/";//后台服务名称

    // 文件服务器地址
//    public static String FILE_SERVER_PATH = SERVER_IP_PORT + "/frame-file-server/";//文件服务

    //版本控制地址
    public static String VERSION_CONTROL_PATH = "";//开发环境 192.168.140.11
    public static final String VERSION_CONTROL_URL = "version.json";
    public static final String BANNER_VERSION_CONTROL_URL = "banner.json";
    public static final String FORCE_VERSION_UPDATE_TYPE = "1";//强制更新
    public static final String SELF_VERSION_UPDATE_TYPE = "2";//手动更新
    
    //服务访问设置
    public static boolean ISHTTPS = false;//是否是https服务
    public static final int DEFAULT_TIMEOUT = 30;//连接服务的超时时间（秒）

    //访问服务器成功的标志
    public static int SERVER_SUCCESS_RESULT_TYPE = 1;

    // Cookie失效的code
    public static String COOKIE_INVALID_CODE = "110007";
}
