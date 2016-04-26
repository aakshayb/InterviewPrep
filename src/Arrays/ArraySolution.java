package Arrays;

import com.sun.org.glassfish.gmbal.Description;

import java.util.*;

/**
 * Created by Akshay on 4/26/2016.
 */
public class ArraySolution
{
  public static void main(String[] args)
  {
    ArraySolution solution = new ArraySolution();
    List<Integer> spiral = solution.spiralOrder(Arrays.asList(Arrays.asList(1, 2, 3),Arrays.asList(4,5,6), Arrays.asList(7,8,9)));
    List spiralMatrix = solution.generateMatrix(3);
    int minSteps = solution.coverPoints(Arrays.asList(0,1,1), Arrays.asList(0,1,2));
    List<Integer> kthRow = solution.getRow(3);
    List pascalTriangle = solution.generate(4);
    String larges = solution.largestNumber(Arrays.asList(3, 30, 34, 5, 9));
    ArrayList<ArrayList<Integer>> list = new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(1,2)), new ArrayList<>(Arrays.asList(3,4)) ));
    solution.rotate(list);
    List<Integer> wave = solution.wave(new ArrayList<>(Arrays.asList(1,2,3,4)));
    int number = solution.repeatedNumber(Arrays.asList(3,4,1,4,1));

    List interval = solution.insert(new ArrayList<>(Arrays.asList(new Interval(1,3), new Interval(6,9))), new Interval(2,5) );
    List intervalMerged = solution.merge(new ArrayList<>(Arrays.asList(new Interval(1,3), new Interval(2,6), new Interval(8,10), new Interval(15,18))));

    int firstPositive = solution.firstMissingPositive(new ArrayList<>(Arrays.asList(-8,-7,-6))); //return 1

  }

  @Description("Spiral Order Matrix I")
  public List<Integer> spiralOrder(List<List<Integer>> a) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
    int n = a.get(0).size();
    int m = a.size();

    if(m == 0)
      return a.get(0);

    int r=0, c=0, layer=0;
    int dir = 1;

    for(int i=0; i < m*n ; i++)
    {
      ret.add(a.get(r).get(c));
      switch(dir)
      {
        case 1:
          if(c == n-1-layer)
          {
            dir = 2;
            r++;
          }
          else
          {
            c++;
          }
          break;
        case 2:
          if(r == m-1-layer)
          {
            dir=3;
            c--;
          }
          else
          {
            r++;
          }
          break;
        case 3:
          if(c == layer  )
          {
            dir = 4;
            r--;
          }
          else
          {
            c--;
          }
          break;
        case 4:
          if( r == layer+1)
          {
            dir = 1;
            layer++;
            c++;
          }
          else
          {
            r--;
          }
          break;
      }
    }
    return ret;
  }

  @Description(("Spiral Order Matrix II - Generate the matrix"))
  public ArrayList<ArrayList<Integer>> generateMatrix(int a) {
    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    int n = a;
    int m = a;
    for(int i=0; i<n; i++)
    {
      ArrayList<Integer> list = new ArrayList<>();
      for(int j=0; j<m; j++)
      {
        list.add(0);
      }
      ret.add(list);
    }


    int r=0, c=0, layer=0;
    int dir = 1;
    int num =1;

    for(int i=0; i < m*n ; i++)
    {
      ret.get(r).set(c, num);
      num++;
      switch(dir)
      {
        case 1:
          if(c == n-1-layer)
          {
            dir = 2;
            r++;
          }
          else
          {
            c++;
          }
          break;
        case 2:
          if(r == m-1-layer)
          {
            dir=3;
            c--;
          }
          else
          {
            r++;
          }
          break;
        case 3:
          if(c == layer  )
          {
            dir = 4;
            r--;
          }
          else
          {
            c--;
          }
          break;
        case 4:
          if( r == layer+1)
          {
            dir = 1;
            layer++;
            c++;
          }
          else
          {
            r--;
          }
          break;
      }
    }
    return ret;


  }

  @Description("Min Steps in Infinite Grid" +
      "You are given a sequence of points and the order in which you need to cover the points. " +
      "Give the minimum number of steps in which you can achieve it. You start from the first point.")
  public int coverPoints(List<Integer> X, List<Integer> Y) {
    int num = 0;
    for(int i=1; i < X.size(); i++)
    {
      num += Math.max(Math.abs(X.get(i) - X.get(i-1)) , Math.abs(Y.get(i) - Y.get(i-1)));
    }
    return num;
  }

  @Description("Kth Row of Pascal's Triangle" +
      "Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1")
  public ArrayList<Integer> getRow(int a)
  {
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<Integer> prev = new ArrayList<>();
    prev.add(1);
    if (a == 0)
      return prev;
    for (int i = 1; i <= a; i++)
    {
      list = new ArrayList<>();
      for (int j = 0; j < prev.size() + 1; j++)
      {
        if (j == 0)
          list.add(prev.get(j));
        else if (j == prev.size())
          list.add(prev.get(j - 1));
        else
          list.add(prev.get(j) + prev.get(j - 1));
      }
      prev = list;
    }
    return list;
  }

  @Description("Generate n-rowed pascal triangle")
  public ArrayList<ArrayList<Integer>> generate(int a) {
    ArrayList<ArrayList<Integer>> retList = new ArrayList<>();
    for(int i=0; i <a; i++)
    {
      ArrayList<Integer> current = new ArrayList<>();
      if(i==0)
      {
        current.add(1);
        retList.add(current);
        continue;
      }
      ArrayList<Integer> previous = retList.get(i-1);

      for(int j=0; j<=i; j++)
      {
        int sum =0;
        if(j==0)
          sum += previous.get(j);
        else if(j==i)
          sum += previous.get(j-1);
        else
          sum += previous.get(j) + previous.get(j-1);
        current.add(sum);
      }
      retList.add(current);

    }
    return retList;
  }

  @Description("Largest Number- Given a list of non negative integers, arrange them such that they form the largest number")
  public String largestNumber(final List<Integer> a) {
    Node[] nodeArr = new Node[a.size()];
    int i =0;
    for(int each : a)
    {
      nodeArr[i++] = new Node(each);
    }
    Arrays.sort(nodeArr);
    StringBuilder sb = new StringBuilder();
    for(Node each : nodeArr)
    {
      if(each.number == 0 && sb.length() !=0)
        continue;
      sb.append(each.number);
    }
    return sb.toString();
  }
  class Node implements Comparable<Node>
  {
    int number;
    Node(int n)
    {
      number = n;
    }
    @Override
    public int compareTo(Node o)
    {
      String first = String.valueOf(this.number) + String.valueOf(o.number);
      String second = String.valueOf(o.number) + String.valueOf(this.number);
      return second.compareTo(first);
    }
  }

  @Description("Rotate Matrix - given an n x n 2D matrix. Rotate the image by 90 degrees (clockwise)")
  public void rotate(ArrayList<ArrayList<Integer>> a) {
    if(a == null || a.size() ==0)
      return;
    int n = a.size();
    int m = a.get(0).size();
    if(m == 1)
      return;
    for(int i=0; i < n/2 ; i++)
    {
      for(int j=i; j< m-i-1; j++ )
      {
        int temp = a.get(n-j-1).get(m-1-i);

        //Set bottom-right
        a.get(n-j-1).set(m-1-i, a.get(i).get(m-j-1)  );

        //set top-right
        a.get(i).set(m-j-1, a.get(j).get(i));

        //set top-left
        a.get(j).set(i, a.get(m-i-1).get(j));

        //set bottom-left
        a.get(m-i-1).set(j,temp);
      }
    }



  }

  @Description("Wave Array - Given an array of integers, sort the array into a wave like array and return it, \n" +
      "In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 ")
  public ArrayList<Integer> wave(ArrayList<Integer> a) {
    quickSort(a);
    ArrayList<Integer> ret = new ArrayList<>();
    for(int i=1; i< a.size(); i=i+2)
    {
      ret.add(a.get(i));
      ret.add(a.get(i-1));
    }
    if(ret.size() != a.size())
      ret.add(a.get(a.size()-1));
    return ret;
  }
  private void quickSort(List<Integer> a)
  {
    if(a.size() <= 1)
      return;
    int pivot = a.get(0);

    int left =1;
    int right = a.size()-1;
    while(left <= right)
    {
      if( a.get(left) <= pivot)
      {
        left++;
      }
      else if(a.get(right) > pivot)
      {
        right--;
      }
      else if(a.get(left) > a.get(right))
      {
        int temp = a.get(left);
        a.set(left, a.get(right));
        a.set(right, temp);
      }
    }
    a.set(0, a.get(right));
    a.set(right, pivot);

    quickSort(a.subList(0, right));
    quickSort(a.subList(right+1, a.size()));
  }

  @Description("Duplicate in Array - Given array of n+1 integers find duplicate in linear time using less than O(n) space")
  public int repeatedNumber(final List<Integer> A) {
    Collections.sort(A);

    for (int i = 0; i < A.size() - 1; i++) {
      if (A.get(i).intValue() == A.get(i + 1).intValue())
        return A.get(i);
    }

    return -1;
  }

  @Description("Merge Intervals - Given set of overlaping intervals,insert a new interval")
  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

    int start, end;
    int startIndex, endIndex;
    int i;

    start = newInterval.start;
    end = newInterval.end;
    i = 0;

    startIndex = endIndex = -1;

    for (Interval in : intervals) {

      if (start >= in.start && start <= in.end)
        startIndex = i;

      if (end >= in.start && end <= in.end)
        endIndex = i;

      i++;
    }

    if (startIndex == -1 && endIndex == -1) {

      startIndex = 0;

      for (i = 0; i < intervals.size(); i++) {
        if (start > intervals.get(i).end) {
          startIndex = i + 1;
        }
      }

      endIndex = intervals.size() - 1;

      for (i = intervals.size() - 1; i >= 0 ; i--) {
        if (end < intervals.get(i).start) {
          endIndex = i - 1;
        }
      }

      for (i = startIndex; i <= endIndex; i++) {
        intervals.remove(startIndex);
      }

      intervals.add(startIndex, newInterval);

      return intervals;
    }

    if (startIndex == -1) {
      for (i = intervals.size() - 1; i >= 0 ; i--) {
        if (start <= intervals.get(i).start)
          startIndex = i;
      }
    }

    if (endIndex == -1) {
      for (i = 0; i < intervals.size(); i++) {
        if (end >= intervals.get(i).end)
          endIndex = i;
      }
    }

    start = Math.min(intervals.get(startIndex).start, start);
    end = Math.max(intervals.get(endIndex).end, end);

    intervals.get(startIndex).start = start;
    intervals.get(startIndex).end = end;

    for (i = startIndex + 1; i <= endIndex; i++) {
      intervals.remove(startIndex + 1);
    }

    return intervals;

  }
  static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
  }

  @Description("Merge overlapping intervals")
  public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    Collections.sort(intervals, (o1, o2) -> {
      int compare = Integer.compare(o1.start, o2.start);
      if(compare != 0)
        return compare;
      compare = Integer.compare(o1.end,o2.end);
      return compare;
    });
    Iterator<Interval> iter = intervals.iterator();
    ArrayList<Interval> list = new ArrayList<Interval>();
    Interval previous = null;

    while(iter.hasNext())
    {
      if(previous == null)
      {
        previous = iter.next();
        continue;
      }
      Interval current = iter.next();
      if(current.start <= previous.end)
      {
        previous = new Interval(previous.start, Math.max(current.end, previous.end));
      }
      else
      {
        list.add(previous);
        previous = current;
      }
    }
    list.add(previous);
    return list;
  }

  @Description("First missing positive number")
  public int firstMissingPositive(ArrayList<Integer> a) {
    int index =1;
    PriorityQueue<Integer> pq = new PriorityQueue<>( (o1, o2) ->{
      return Integer.compare(o1,o2) ;
    });
    for(int i=0; i< a.size(); i++)
    {
      if(a.get(i)>=1)
        pq.add(a.get(i));
    }
    if(pq.size() ==0)
      return 1;
    int n = pq.size();
    for(int i=0; i< n; i++)
    {
      int removed = pq.remove();
      if(index!= removed)
        return index;
      index++;
    }
    return index;
  }

}
