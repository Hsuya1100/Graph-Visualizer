/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;


import java.util.*;
import java.io.*;
import java.lang.*;
class Node{
    int x;
    int y;
    String name;
    Node(String name, int x, int y){
        this.name = name;
        this.x    = x;
        this.y    = y;
    }
    String getname(){
        return this.name;
    }
    int getx(){
        return this.x;
    }
    int gety(){
        return this.y;
    }
    void setx(int x){
        this.x=x;
    }
    
    void sety(int y){
        this.y=y;
    }
    @Override
    public String toString(){
        return(name+" "+x+" "+y);
    }
}
class Edge{

    String src;
        String des;
    int wt;
    Edge(String src, String des,int wt){
        this.src = src;
        this.des = des;
        this.wt  = wt;
    }
    int getwt(){
        return this.wt;
    }
    void setwt(int wt){
        this.wt = wt;
    }
    
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

//    @Override
//    public String toString(){
//        return(this.src.name+" "+this.des.name+" "+wt);
//    }
}
class Sorter implements Comparator<Node> 
{ 
    @Override
    public int compare(Node e1, Node e2){
        return e1.getname().compareTo(e2.getname());
    } 
}  

class Sorter2 implements Comparator<Edge> 
{ 
    @Override
    public int compare(Edge e1, Edge e2){
        if( e1.src.compareTo(e2.src) == 0){
            return e1.des.compareTo(e2.des);
        }
        else
            return e1.src.compareTo(e2.src);
    } 
}  
public class Graph {
    
    NewJFrame ob;
    public ArrayList<Node> nodes;
    public TreeMap<String, Node> nodes2;

    public TreeMap<String, Node> getNodes2() {
        return nodes2;
    }
    public ArrayList<Edge> edges;
    
    TreeMap<String,ArrayList< Edge> > adjlist;
    Graph(){
        nodes   = new ArrayList<Node>() ;
        nodes2  = new TreeMap<String, Node>();
        edges   = new ArrayList<Edge> ();
        adjlist = new TreeMap<String,ArrayList< Edge>> ();
        
    }

    void addob(NewJFrame ob){
        this.ob = ob;
    }
    void addNode(String name, int x,int y){
        Node no = new Node(name,x,y);
        nodes.add(no);
        nodes2.put(no.getname(),no);
        ob.pan.repaint();
        Collections.sort(nodes, new Sorter());
        System.out.println(no.toString());
        printnodes();
        
    }
    Node getnode(String name){
        Node r= null;
        for(Node n: nodes){
            if(n.name.equals(name))
            {
                r = n;
                break;
            }
        }
        Collections.sort(nodes, new Sorter());
        return r;
    }
    void rmnode(String name){
        
        for(int i=0 ; i<edges.size(); i++){
            if(edges.get(i).des.equals(name)  ){
                edges.remove(i);
            }
        }
        for(int i=0 ; i<edges.size(); i++){
            if(edges.get(i).src.equals(name)){
                edges.remove(i);
            }
        }
        for(int i=0 ; i<nodes.size(); i++){
            if(nodes.get(i).name.equals(name))
            {
                System.out.println("Removed Node "+nodes.get(i).toString());
                nodes.remove(i);
                nodes2.remove(name);
                break;
            }
        }
        Collections.sort(nodes, new Sorter());
        printnodes();
        printedges();
        ob.pan.repaint();
        nodes2.remove(name);
    }
    void editnode(String name,int x,int y){
        for(int i=0 ; i<nodes.size(); i++){
            if(nodes.get(i).name.equals(name))
            {
                nodes.get(i).setx(x);
                nodes.get(i).sety(y);
                System.out.println("Updated Node"+nodes.get(i).toString());
                break;
            }
        }
        Collections.sort(nodes, new Sorter());
        Collections.sort(edges, new Sorter2());
        ob.pan.repaint();
    }
    void printnodes(){
       for(Node n : nodes){
           System.out.println("-> "+n.toString());
       } 
    }
    void addEdge(String src, String des,int wt){
        int ctr=0;
        for(Node n:nodes){
            if(n.getname().equals(src) ||n.getname().equals(des) ){
                ctr++;
            }
        }
        Collections.sort(nodes, new Sorter());
        ob.pan.repaint();
        if(ctr != 2)
            return;
        Edge ed = new Edge(src,des,wt);
        edges.add(ed);
//        addAdj(ed);
        Collections.sort(edges, new Sorter2());
        System.out.println(src+" "+des+" "+wt);
        printAdj();
    }
    
    Edge getedge(String src,String des){
        Edge r= null;
        for(Edge e: edges){
            if(e.src.equals(src) && e.des.equals(des))
            {
                r = e;
                break;
            }
        }
        Collections.sort(nodes, new Sorter());
        Collections.sort(edges, new Sorter2());
        return r;
    }
    void rmedge(String src,String des){
        int l=0;
        for(int i=0 ; i<edges.size(); i++){
            if(edges.get(i).src.equals(src) && edges.get(i).des.equals(des))
            {
                edges.remove(i);
                break;
            }     
        }
        Collections.sort(nodes, new Sorter());
        Collections.sort(edges, new Sorter2());
        ob.pan.repaint();
        printnodes();
        printedges();
        printAdj();
    }
    void editedge(String src,String des,int wt){
        for(int i=0 ; i<edges.size(); i++){
            if(edges.get(i).src.equals(src) && edges.get(i).des.equals(des))
            {
                edges.get(i).setwt(wt);
                break;
            }
        }
        Collections.sort(nodes, new Sorter());
        Collections.sort(edges, new Sorter2());
        ob.pan.repaint();
        printAdj();
    }
    void printedges(){
       for(Edge n : edges){
           System.out.println(n.src+" "+n.des+" "+n.wt);
       } 
    }
//    void filladj(){
//        for(Edge e:edges){
//            addAdj();
//        }
//    }
    void addAdj(Edge edge){
        if(!adjlist.containsKey(edge.getSrc() ) ){
		        ArrayList<Edge> l = new ArrayList<Edge>();
		        l.add(edge);
		        adjlist.put(edge.getSrc(),l);
	    }
	    else{
	        ArrayList<Edge> l = adjlist.get(edge.getSrc()) ;
	        l.add(edge);
	        adjlist.put(edge.getSrc(),l);
	    }
    }
    public TreeMap<String, ArrayList<Edge>> getAdjlist() {
        return adjlist;
    }
    void printAdj(){
        for (Map.Entry mapElement : adjlist.entrySet()) { 
			String key        = (String)mapElement.getKey(); 
			ArrayList<Edge> e = ((ArrayList<Edge>)mapElement.getValue()); 
			Iterator litr     = e.listIterator();
                        System.out.print(key+" = ");
			while( litr.hasNext() ){
                            Edge m = (Edge)litr.next();
                            System.out.print(m.des+" , ");
			}
			System.out.println();
		}
    }

    public void setAdjlist(TreeMap<String, ArrayList<Edge>> adjlist) {
        this.adjlist = adjlist;
    }
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
    
//    Edge getedges(String src){
//        Node r= null;
//        for(Node n: nodes){
//            if(n.name.equals(name))
//               r = n;
//                break;
//        }
//        return r;
//    }

    void drawLine(int i, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void drawPolygon(int[] xxx, int[] yyy, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

class Dijkstra{
    private ArrayList<String>settledNodes;
    private Set<String>unSettledNodes;
    private HashMap<String, Edge> previous;
    private TreeMap<String, ArrayList<Edge>> adjlist;
    private HashMap<String, Double> distance;
    private ArrayList<String> visited;
//    private PriorityQueue<String> pq ;
    private String source="";
    private String destination="";
    private Graph g;
    private String ans;

public Dijkstra(Graph graph,String source,String destination) {
                this.ans ="";
		this.g           = graph;
		this.source      = source;
		this.destination = destination;
		previous         = new HashMap<String, Edge>();
                distance         = new HashMap<String, Double>();
		visited          = new ArrayList<String>();
		for ( Node e: g.getNodes()){
			distance.put(e.getname(), Integer.MAX_VALUE*1.0);
		}
		distance.put(source, 0.0);
        filladj();
		adjlist          = g.getAdjlist();
        dikshtra();
    }
	void dikshtra(){
	  for (int i = 0; i < g.getNodes().size(); ++i){
	    String minVertex = findMin();
	    if(minVertex.equals(" "))
	        break;
	    visited.add(minVertex);
	    ArrayList<Edge> traverse = adjlist.get(minVertex);
	    for(Edge e : traverse){
	      String child  = e.getDes();
	      String parent = minVertex;
	      double weight = e.getwt();
	      double disParent = distance.get(parent);
	      double disChild  = distance.get(child);
	      if(disParent != (Integer.MAX_VALUE*1.0) && disChild > disParent + weight){
	 	  		distance.put(child, disParent*1.0 + weight*1.0);
	 	        previous.put(child,e);
	      }
	    }
	  }
	}
	String findMin(){
		double minDistance=(Integer.MAX_VALUE*1.0);
		String node =" ";
		for (Map.Entry mapElement : adjlist.entrySet()) { 
			String e = (String)mapElement.getKey(); 
			if(!visited.contains(e) &&distance.containsKey(e) &&distance.get(e) < minDistance){
			  minDistance=distance.get(e);
			  node = e;  
			} 
		}
		return node; 
	}
	void print(String destination){
       if(destination.equals(source))
	        ans="same";
       else if(previous.containsKey(destination) == false)
            ans="no";
        else
            printPath(destination);
	}
	void printPath(String vertex){
		if(previous.containsKey(vertex) == false)
			return ;
		printPath(previous.get(vertex).getSrc());
		if(previous.containsKey(vertex)){
                    Edge edge   = (Edge)previous.get(vertex);
                    System.out.println(edge.getSrc()+" "+edge.getDes());
                        ans=ans+edge.getSrc()+" ";
	    }
	}
        
void filladj(){
    g.getAdjlist().clear();
    for(Edge edge : g.getEdges()){
        if(!g.adjlist.containsKey(edge.getSrc() ) ){
            ArrayList<Edge> l = new ArrayList<Edge>();
            l.add(edge);
            g.adjlist.put(edge.getSrc(),l);
        }
        else{
            ArrayList<Edge> l = g.adjlist.get(edge.getSrc()) ;
	    l.add(edge);
	    g.adjlist.put(edge.getSrc(),l);
        }
    }
}

    public ArrayList<String> getSettledNodes() {
        return settledNodes;
    }

    public void setSettledNodes(ArrayList<String> settledNodes) {
        this.settledNodes = settledNodes;
    }

    public Set<String> getUnSettledNodes() {
        return unSettledNodes;
    }

    public void setUnSettledNodes(Set<String> unSettledNodes) {
        this.unSettledNodes = unSettledNodes;
    }

    public HashMap<String, Edge> getPrevious() {
        return previous;
    }

    public void setPrevious(HashMap<String, Edge> previous) {
        this.previous = previous;
    }

    public TreeMap<String, ArrayList<Edge>> getAdjlist() {
        return adjlist;
    }

    public void setAdjlist(TreeMap<String, ArrayList<Edge>> adjlist) {
        this.adjlist = adjlist;
    }

    public HashMap<String, Double> getDistance() {
        return distance;
    }

    public void setDistance(HashMap<String, Double> distance) {
        this.distance = distance;
    }

    public ArrayList<String> getVisited() {
        return visited;
    }

    public void setVisited(ArrayList<String> visited) {
        this.visited = visited;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Graph getG() {
        return g;
    }

    public void setG(Graph g) {
        this.g = g;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
class Test{
      File f;
      String inp[];
      String basePath;
    Test(String importPath,int a) throws IOException{ 
        f= new File(importPath);

    }
    Test(String exportPath) throws IOException{ 
        f= new File(exportPath);
         write();
    }
    String[] read() throws FileNotFoundException{
        Scanner sc = new Scanner(f);
        String s="";
        int f=0;
        while (sc.hasNextLine()) {
            if(f==0)
                s=s+sc.nextLine();
            else
                s=s+" "+sc.nextLine();
            f++;
        }
//        s.replaceAll("\\n"," ");
        System.out.println("STINRG:_"+s+"_");
        s.trim();
        inp = s.split(" ");
//        System.out.println("SECON SPLIT "+inp);
        return(inp);
    }
    
    
    void write() throws FileNotFoundException{
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            inp = s.split(" ");
            break;
        }
//        feed(inp);
    }
    }
