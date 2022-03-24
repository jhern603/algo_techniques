package Assignment2;

public class Assignment2 {
    public Graph input;

    Assignment2(){
        input = new Graph("src/main/java/Assignment2/5by5Graph.txt");
        FrameDisplay frame = new FrameDisplay();
        System.out.println(input);

        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Assignment2();
    }
}
