/**
 * ShortestRoute.java
 *
 * Implements a heuristic for TSP called Nearest Neighbor Heuristic
 * Maintains a linkedlist, and adds the point that is closest the
 * current point, and is unvisited
 * Second heuristic is insertPointAtSmallestIncrease, where it increases
 * a point where it has the least increase in the route
 * @author Stephanie Wu
 * @version 1.0
 * @since 3/26/2020
 */

import java.awt.Color;
import java.util.*;

public class ShortestRoute
{
	private ListNode first;

	public ShortestRoute()
	{
		first = null;
	}

	public double length ()
	{
		double len = 0.0;
		ListNode temp;
		for(temp = first ; temp.getNext() != null ; temp = temp.getNext()) {
			len += getDist((Point)temp.getValue(), (Point)temp.getNext().getValue());
		}
		len += getDist((Point)first.getValue(), (Point)temp.getValue());
		return len;
	}

	public double getDist(Point p1, Point p2)
	{
		double dx = p2.getX() - p1.getX(), dy = p2.getY() - p1.getY();
		return Math.pow(dx * dx + dy * dy, 0.5);
	}

	public double getDist2(Point p1, Point p2) {
		double dx = p2.getX() - p1.getX(), dy = p2.getY() - p1.getY();
		return dx * dx + dy * dy;
	}

	/*
	Method to implement the Nearest Neighbor Heuristic
	*/
	public void insertPointAtNearestNeighbor(Point p)
	{
		if(first == null) {
			first = new ListNode(p);
		}
		else if(first.getNext() == null) {
			first.setNext(new ListNode(p));
		}
		else {
			double minDist = 1000000000;
			ListNode best = first;
			for(ListNode temp = first ; temp != null ; temp = temp.getNext()) {
				if(getDist2(p, (Point)temp.getValue()) < minDist) {
					minDist = getDist2(p, (Point)temp.getValue());
					best = temp;
				}
			}
			ListNode node = new ListNode(p, best.getNext());
			best.setNext(node);
		}
	}

	/*
	Method to implement the Smallest Increase Heuristic
	*/
	public void insertPointAtSmallestIncrease(Point curr) {
		if(first == null) {
			first = new ListNode(curr);
		}
		else if(first.getNext() == null) {
			first.setNext(new ListNode(curr));
		}
		else {
			double minInc = 1e9;
			ListNode best = first, next = first.getNext(), prev = first;
			while(next != null) {
				double inc = getDist((Point)curr, (Point)prev.getValue()) +
				getDist(curr, (Point)next.getValue()) - getDist((Point)prev.getValue(), (Point)next.getValue());
				if(inc < minInc) {
					minInc = inc;
					best = prev;
				}
				prev = next;
				next = next.getNext();
			}

			double inc = getDist(curr, (Point)prev.getValue()) +
			getDist(curr, (Point)first.getValue()) - getDist((Point)prev.getValue(), (Point)first.getValue());
			if(inc < minInc) {
				prev.setNext(new ListNode(curr));
			}
			else {
				best.setNext(new ListNode(curr, best.getNext()));
			}
		}
	}

	public int size() {
		int sz = 0;
		ListNode temp = first;
		while (temp != null)
		{
			temp = temp.getNext();
			sz++;
		}
		return sz;
	}

	public void draw()
	{
		int count = 0;
		ListNode node = first;
		ListNode last;
		Point start = (Point)first.getValue();
		StdDraw.filledCircle(start.getX(), start.getY(), 4);
		Point a = start, b = start;
		while(node.getNext() != null)
		{
			last = node;
			node = node.getNext();
			a = (Point)last.getValue();
			b = (Point)node.getValue();
			StdDraw.setPenColor(new Color(255,0,0));
			StdDraw.filledCircle(b.getX(), b.getY(), 4);
			StdDraw.setPenColor(new Color(0,0,255));
			StdDraw.line(a.getX(), a.getY(), b.getX(), b.getY());
		}
		StdDraw.line(start.getX(), start.getY(), b.getX(), b.getY());
	}

    public String toString()
    {
		int count = 0;
		ListNode node = first;
		String result = new String("");
		while(node != null)
		{
			result += String.format("%4d: %s%n",count,(Point)node.getValue());
			node = node.getNext();
			count++;
		}
		return result;
    }
}
