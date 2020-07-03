import java.util.*;
import java.io.*;

public class HalifaxMap {
	HashMap<Integer,int[]> coordinates = new HashMap<Integer,int[]>();
	HashMap<Integer,ArrayList<Integer>> adjList = new HashMap<Integer,ArrayList<Integer>>();
	
	
	Boolean newIntersection(int x , int y ){
		Boolean ack=false;
		try {
			if(contains(x,y)==true) {
				return false;
			}
			coordinates.put(coordinates.size(), new int[]{x,y});
			ack=true;
		}catch(Exception e){
			ack=false;
			return ack;
		}
		return ack;
	}
	
	Boolean defineRoad(int x1,int y1,int x2,int y2) {
		Boolean added=false;
		try {
			if(!contains(x1,y1)) {
				coordinates.put(coordinates.size(),new int[] {x1,y1});
			}
			if(!contains(x2,y2)) {
				coordinates.put(coordinates.size(),new int[] {x2,y2});
			}
			int key1=getKeyFromCoordinates(x1, y1);
			int key2=getKeyFromCoordinates(x2, y2);
			ArrayList<Integer> temp;
			if(adjList.containsKey(key1)) {
				if(adjList.get(key1).contains(key2)) {
					return false;
				}
				temp=adjList.get(key1);
				temp.add(key2);
				adjList.replace(key1,temp);
			}else {
				temp=new ArrayList<Integer>();
				temp.add(key2);
				adjList.put(key1,temp);
			}
			added=true;
			return true;
		}catch(Exception e) {
			added=false;
			return added;
		}
	}
	
	void navigate(int x1,int y1,int x2,int y2)
	{
		int node1=getKeyFromCoordinates(x1, y1);
		int node2=getKeyFromCoordinates(x2, y2);
		
		if(node1==-1 ||node2==-1) {
			System.out.println("no path");
			return;
		}
		int totalNodes=coordinates.size();
		double[] dist=new double[totalNodes];
		
		for(int j=0;j<totalNodes;j++) {
			if(j==node1) {
				dist[j]=0;
			}else {
				dist[j]=Double.POSITIVE_INFINITY;
			}
		}
		
	
		ArrayList<Integer> unvisited=new ArrayList<Integer>();
		for(int i=0;i<coordinates.size();i++){
			unvisited.add(i);
		}
		int[] previous=new int[coordinates.size()];
		
		while(unvisited.size()!=0)
		{
			int node=-1;
			Double max=Double.POSITIVE_INFINITY;
			for(int i=0;i<coordinates.size();i++) {
				if(dist[i]<max && unvisited.contains(i)) {
					node=i;
					max=dist[i];
				}
			}
			if(node==-1 && unvisited.contains(node2)) {
				System.out.println("no path");
				return;
			}
			if(node==-1 && !unvisited.contains(node2)) {
				break;
			}
			if(adjList.containsKey(node))
			{
				for(int i=0;i<adjList.get(node).size();i++) {
					int adjNode=adjList.get(node).get(i);
					if(unvisited.contains(adjNode)) {
						Double newDist=dist[node]+(double)distance(node,adjNode);
						if(newDist<dist[adjNode]) {
							dist[adjNode]=newDist;
							previous[adjNode]=node;
						}
					}
				}	
			}
			
			unvisited.remove((Integer)node);
		}
		Stack<Integer> trace=new Stack<>();
		if(dist[node2]==Double.POSITIVE_INFINITY) {
			System.out.println("no path");
			return;
		}
		else {
			int i=node2;
			while(i!=node1) {
				trace.push(i);
				i=previous[i];
			}
			trace.push(node1);
		
			while(!trace.isEmpty()) {
				i=trace.pop();
				System.out.println(coordinates.get(i)[0]+"\t"+coordinates.get(i)[1]);
			}
		}
	}
	
	int getKeyFromCoordinates(int x,int y) {
		int key=-1;
		try {
			int[] temp;
			for(int i=0;i<coordinates.size();i++) {
				temp = coordinates.get(i);
				if(x==temp[0] && y==temp[1]) {
					key=i;
					return key;
				}
			}
		}catch(Exception E) {
			key=-1;
			return key;
		}
		return key;
	}
	
	Boolean contains(int x,int y)
	{
		try {
			int[] temp;
			for(int i=0;i<coordinates.size();i++) {
				temp = coordinates.get(i);
				if(x==temp[0] && y==temp[1]) {
					return true;
				}
			}
		}catch(Exception E) {
			return false;
		}
		return false;
	}
	
	
	
	int distance(int node1,int node2) {
		try {
			int[] temp1=coordinates.get(node1);
			int[] temp2=coordinates.get(node2);
			return (int)Math.round((Math.sqrt(((temp2[0]-temp1[0])*(temp2[0]-temp1[0]))+((temp2[1]-temp1[1])*(temp2[1]-temp1[1])))));
		}catch(Exception E) {
			return 0;
		}
	}
	
	
	
	void print()
	{
		for(int i=0;i<coordinates.size();i++) {
			if(adjList.containsKey(i))
			{
				ArrayList<Integer> temp=adjList.get(i);
				System.out.print(i+":  ");
				for(int j=0;j<temp.size();j++)
					System.out.print(temp.get(j)+" ");
				System.out.println();
			}
		}
		for(int i=0;i<coordinates.size();i++) {
			int temp[]=coordinates.get(i);
			System.out.println(i+ ":  "+temp[0]+" "+temp[1]);
		}
	}
}
