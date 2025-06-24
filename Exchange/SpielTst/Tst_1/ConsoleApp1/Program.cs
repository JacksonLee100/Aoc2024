using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

//namespace DailyDistances
//{
    class Program
    {
        //string filePath = "C:\\sw\\MyScript\\SpielTst\\Tst_1\\ConsoleApp1\\input.txt";

        static void Main(string[] args)
        {
            string filePath = "C:\\sw\\MyScript\\SpielTst\\Tst_1\\ConsoleApp1\\input.txt";

            string[] lines = File.ReadAllLines(filePath);

            foreach (string line in lines)
            {

                line.Split('\n');
            }

            foreach (string ln in lines)
            {
                Console.WriteLine(ln);
            }

            int n = int.Parse(lines[0]);    // Number of segments
            int d = int.Parse("3");    // Number of days

            if (lines.Length < 2 + n)
            {
                Console.WriteLine("Missing segment data");
                return;
            }

            int[] segments = new int[n];
            for (int i = 0; i < n; i++)
            {
                segments[i] = int.Parse(lines[2 + i]);
            }

            if (d > n)
            {
                Console.WriteLine("Error: Days cannot exceed segments");
                return;
            }

            // Binary search for minimal maximum daily distance
            int low = segments.Max();
            int high = segments.Sum();
            int maxDist = high;

            while (low < high)
            {
                int mid = (low + high) / 2;
                if (CanSplit(segments, mid, d))
                {
                    high = mid;
                    maxDist = mid;
                }
                else
                {
                    low = mid + 1;
                }
            }

            List<int> dailyDistances = ReconstructDays(segments, d, maxDist);
            Console.WriteLine(string.Join(" ", dailyDistances));

        }

        // Check if segments can be split into <= d days with maxDist constraint
        static bool CanSplit(int[] segments, int maxDist, int d)
        {
            int days = 1;
            int current = 0;

            foreach (int seg in segments)
            {
                if (current + seg <= maxDist)
                {
                    current += seg;
                }
                else
                {
                    days++;
                    current = seg;
                    if (current > maxDist) return false;
                }
            }
            return days <= d;
        }

        // Reconstruct daily distances
        static List<int> ReconstructDays(int[] segments, int d, int maxDist)
        {
            List<int> dailyDistances = new List<int>();
            int index = 0;

            for (int day = 0; day < d; day++)
            {
                int current = 0;
                int segmentsLeft = d - day;  // Remaining days including current
                int maxSegments = segments.Length - index - (segmentsLeft - 1);

                while (index < segments.Length &&
                       maxSegments > 0 &&
                       current + segments[index] <= maxDist)
                {
                    current += segments[index];
                    index++;
                    maxSegments--;
                }
                dailyDistances.Add(current);
            }
            return dailyDistances;
        }
    }
//}
