package tools.xiami;

import java.io.File;

/**
 * description： <br>
 * createTime: 2017/10/915:31 <br>
 *
 * @author zzh
 */
public class Xiami {
    public static void recurseDirectory(String filePath, IOperator operator) {

        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        if(!file.exists()) {
            System.exit(1);
        }
        if(file.isDirectory()) {
            String[] fileNames = file.list();
            for (int i = 0; i < fileNames.length; i++) {
                recurseDirectory(filePath + "\\" + fileNames[i],  operator);
            }
        } else {
            operator.operator(file);
        }
    }

    public static void main(String[] args) {
        final String path;
        if(args.length > 0) {
            path = args[0];
        } else {
            path = "";
        }
        recurseDirectory(path, (f) -> {
            String fileName = f.getName();
            if(fileName.indexOf("_") >= 0) {
                f.renameTo(new File(path + fileName.replaceAll("_", "-")));
            }
        });
    }
}
