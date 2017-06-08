import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by r3v3nan7 on 07.06.17.
 */
public class Main {
    public static final String LINUX_COMMAND_LINE = "xdg-open";
    public static final String LINUX_COMMAND_UPDATE = "sudo apt-get update";
    public static final String LINUX_COMMAND_UPGRADE = "sudo apt-get -y upgrade";
    public static final String LINUX_AUTH_COMMAND = "sudo -v";


    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter your root password: ");
        String rootPass = reader.readLine();

        String[] cmd = {"/bin/bash","-c","echo " + rootPass + " | sudo -S " + LINUX_COMMAND_UPDATE};
        Process p = Runtime.getRuntime().exec(cmd);

        BufferedReader input = getBufferedReader(p);

        System.out.println("Do you want to take updates ? (Y/N)");
        String userChoice = reader.readLine();

        switch (userChoice){
            case "Y":
                takeUpdates(rootPass, reader);
                break;
            case "N":
                return;
        }
        input.close();



    }

    private static BufferedReader getBufferedReader(Process p) throws IOException {
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        return input;
    }

    private static void takeUpdates(String rootPass, BufferedReader reader) throws IOException, InterruptedException {
        String[] cmd = {"/bin/bash","-c","echo " + rootPass + " | sudo -S " + LINUX_COMMAND_UPGRADE};
        Process pb = Runtime.getRuntime().exec(cmd);


        BufferedReader input = getBufferedReader(pb);



    }


}
