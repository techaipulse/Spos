import java.util.Scanner;

public class Priority {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n;
        int[] p = new int[10];    // Process IDs
        int[] pp = new int[10];   // Priorities
        int[] bt = new int[10];   // Burst times
        int[] w = new int[10];    // Waiting times
        int[] t = new int[10];    // Turnaround times
        int totalWait = 0, totalTurnaround = 0; // Totals for average calculations

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        n = s.nextInt();

        // Input burst time and priority for each process
        for (int i = 0; i < n; i++) {
            System.out.print("\nProcess[" + (i + 1) + "] - Enter Burst Time: ");
            bt[i] = s.nextInt(); // Burst Time
            System.out.print("Process[" + (i + 1) + "] - Enter Priority: ");
            pp[i] = s.nextInt(); // Priority
            p[i] = i + 1;        // Process Number
        }

        // Sorting processes based on priority (higher priority first)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pp[i] < pp[j]) { // Higher number means higher priority
                    // Swap priorities
                    int temp = pp[i];
                    pp[i] = pp[j];
                    pp[j] = temp;

                    // Swap burst times
                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    // Swap process numbers
                    temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
        }

        // Calculate waiting time and turnaround time
        w[0] = 0; // First process has 0 waiting time
        t[0] = bt[0]; // Turnaround time for the first process is its burst time
        totalTurnaround += t[0]; // Accumulate turnaround time

        for (int i = 1; i < n; i++) {
            w[i] = w[i - 1] + bt[i - 1]; // Waiting time is cumulative burst time of previous processes
            t[i] = w[i] + bt[i]; // Turnaround time is waiting time plus its burst time

            // Accumulate total waiting and turnaround times for averages
            totalWait += w[i];
            totalTurnaround += t[i];
        }

        // Calculate averages
        float averageWait = (float) totalWait / n;
        float averageTurnaround = (float) totalTurnaround / n;

        // Display results
        System.out.print("\n\nProcess \tBurst Time \tWaiting Time \tTurnaround Time \tPriority\n");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + p[i] + "\t\t" + bt[i] + "\t\t" + w[i] + "\t\t" + t[i] + "\t\t\t" + pp[i] + "\n");
        }

        System.out.printf("\nAverage Waiting Time: %.2f", averageWait);
        System.out.printf("\nAverage Turnaround Time: %.2f\n", averageTurnaround);

        s.close(); // Close the scanner
    }
}
