package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please select: \n1. To use the console application. \n2. To use the graphic interface application. ");
        int option = scan.nextInt();
        switch (option) {
            case 1:
                InteractiveConsole interactiveConsole = new InteractiveConsole();
                break;
            case 2:
                System.out.println("---------------------------\nPlease check your task bar, it might me started in background\n---------------------------");
                JFrameInteractive frameInteractive = new JFrameInteractive("Graphic interface - John Agudelo Final Exercise");
                break;
            default:
                System.out.println("Sorry, option not allowed");
                break;
        }
    }
}
