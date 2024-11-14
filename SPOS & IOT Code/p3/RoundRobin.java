import java.util.Scanner;

public class RoundRobin {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int num, quantum, totalWaitingTime = 0, totalTurnaroundTime = 0;
        System.out.print("Enter number of processes (MAX 10): ");
        num = s.nextInt();

        int[] btime = new int[num], rtime = new int[num], wtime = new int[num];
        System.out.print("Enter burst time for each process:\n");
        for (int i = 0; i < num; i++) {
            System.out.print("P[" + (i + 1) + "]: ");
            btime[i] = s.nextInt();
            rtime[i] = btime[i];
        }

        System.out.print("\nEnter quantum: ");
        quantum = s.nextInt();

        int remainingProcesses = num, time = 0, i = 0;
        while (remainingProcesses != 0) {
            if (rtime[i] > quantum) {
                rtime[i] -= quantum;
                time += quantum;
            } else if (rtime[i] > 0) {
                time += rtime[i];
                rtime[i] = 0;
                wtime[i] = time - btime[i];
                totalWaitingTime += wtime[i];
                totalTurnaroundTime += time;
                remainingProcesses--;
            }
            i = (i + 1) % num;
        }

        float avgWaitingTime = (float) totalWaitingTime / num;
        float avgTurnaroundTime = (float) totalTurnaroundTime / num;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        s.close();
    }
}
