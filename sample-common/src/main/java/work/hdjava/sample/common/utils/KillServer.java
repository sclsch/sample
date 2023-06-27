package work.hdjava.sample.common.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KillServer {
    private Set<Integer> ports;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("请输入要杀掉的windows进程的端口号，如果有多个，则以逗号相隔");
        System.out.println("Please input kill port");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        scanner.close();
        String[] split = input.split(",");
        Set<Integer> ports = new HashSet<>();
        for (String spid : split) {
            try{
                int pid = Integer.parseInt(spid);
                ports.add(pid);
            }catch(Exception e){
                System.out.println("错误的端口号，请输入一个或者多个端口，以英文逗号隔开");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        }

        KillServer kill = new KillServer();
        kill.ports = ports;
        System.out.println("need kill " + ports.size() + " num");
        for (Integer pid : ports) {
            kill.start(pid);
        }
        System.out.println("清理完毕，程序即将退出");
        System.out.println("SUCCESS");
        Thread.sleep(5000);
        System.exit(0);

    }

    public void start(int port){
        Runtime runtime = Runtime.getRuntime();
        try {
            //查找进程号
            Process p = runtime.exec("cmd /c netstat -ano | findstr \""+port+"\"");
            InputStream inputStream = p.getInputStream();
            List<String> read = read(inputStream, "UTF-8");
            if(read.size() == 0){
                System.out.println("找不到该端口的进程");
                try {
                    Thread.sleep(6000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                for (String string : read) {
                    System.out.println(string);
                }
                System.out.println("找到"+read.size()+"个进程，正在准备清理");
                kill(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证此行是否为指定的端口，因为 findstr命令会是把包含的找出来，例如查找80端口，但是会把8099查找出来
     * @param str
     * @return
     */
    private boolean validPort(String str){
        Pattern pattern = Pattern.compile("^ *[a-zA-Z]+ +\\S+");
        Matcher matcher = pattern.matcher(str);

        matcher.find();
        String find = matcher.group();
        int spstart = find.lastIndexOf(":");
        find = find.substring(spstart + 1);

        int port = 0;
        try {
            port = Integer.parseInt(find);
        } catch (NumberFormatException e) {
            System.out.println("查找到错误的端口:" + find);
            return false;
        }
       return true;
    }

    /**
     * 更换为一个Set，去掉重复的pid值
     * @param data
     */
    public void kill(List<String> data){
        Set<Integer> pids = new HashSet<>();
        for (String line : data) {
            int offset = line.lastIndexOf(" ");
            String spid = line.substring(offset);
            spid = spid.replaceAll(" ", "");
            int pid = 0;
            try {
                pid = Integer.parseInt(spid);
            } catch (NumberFormatException e) {
                System.out.println("获取的进程号错误:" + spid);
            }
            pids.add(pid);
        }
        killWithPid(pids);
    }

    /**
     * 一次性杀除所有的端口
     * @param pids
     */
    public void killWithPid(Set<Integer> pids){
        for (Integer pid : pids) {
            try {
                Process process = Runtime.getRuntime().exec("taskkill /F /pid "+pid+"");
                InputStream inputStream = process.getInputStream();
                String txt = readTxt(inputStream, "GBK");
                System.out.println(txt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private List<String> read(InputStream in,String charset) throws IOException{
        List<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
        String line;
        while((line = reader.readLine()) != null){
            boolean validPort = validPort(line);
            if(validPort){
                data.add(line);
            }
        }
        reader.close();
        return data;
    }
    public String readTxt(InputStream in,String charset) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
        StringBuffer sb = new StringBuffer();
        String line;
        while((line = reader.readLine()) != null){
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
    public static void comfirmSingleProcess(String prefix) {

        if(prefix == null) {

            throw new NullPointerException("The prefix is null,please check it!!");
        }

        // 声明文件读取流
        BufferedReader out = null;
        BufferedReader br = null;
        try {

            // 创建系统进程
            ProcessBuilder pb = new ProcessBuilder("tasklist");
            Process p = pb.start();
            // 读取进程信息
            out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
            br = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));

            // 创建集合 存放 进程+pid
            List<String> list = new ArrayList<>();
            // 读取
            String ostr;
            while ((ostr = out.readLine()) != null){
                // 将读取的进程信息存入集合
                list.add(ostr);
            }

            // 遍历所有进程
            for (int i = 3; i < list.size(); i++) {
                // 必须写死,截取长度,因为是固定的
                String process = list.get(i).substring(0, 25).trim(); // 进程名
                String pid = list.get(i).substring(25, 35).trim();    // 进程号
                // 匹配指定的进程名,若匹配到,则立即杀死
                if(process.startsWith(prefix)){
                    Runtime.getRuntime().exec("taskkill /F /PID "+pid);
                }
            }

            // 若有错误信息 即打印日志
            String estr = br.readLine();
            if (estr != null) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关流
            try {
                if(out != null) { out.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(br != null) { br.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
