package ebook.thinking.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/21.
 */
public final class Directory
{
    public static File[] local(File dir, final String regex)
    {
        return dir.listFiles(new FilenameFilter()
        {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name)
            {
                return pattern.matcher(name).matches();
            }
        });
    }

    public static File[] local(String path, final String regex)
    {
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File>
    {
        public List<File> files = new ArrayList<>();

        public List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator()
        {
            return files.iterator();
        }

        void addAll(TreeInfo other)
        {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public String toString()
        {
            return "dirs : " + PPrint.promat(dirs) + "\n\nfiles : " + PPrint.promat(files);
        }
    }

    public static TreeInfo walk(String start, String regex)
    {
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start, String regex)
    {
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(String start)
    {
        return recurseDirs(new File(start), ".*");
    }

    public static TreeInfo walk(File start)
    {
        return recurseDirs(start, ".*");
    }

    static TreeInfo recurseDirs(File startDir, String regex)
    {
        TreeInfo result = new TreeInfo();
        for (File file : startDir.listFiles()) {
            if(file.isDirectory())
            {
                result.dirs.add(file);
                recurseDirs(file, regex);
            }
            else if(file.getName().matches(regex))
            {
                result.files.add(file);
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println(walk("."));
        }
        else
        {
            for (String s : args) {
                System.out.println(walk(s));
            }
        }
    }
}
