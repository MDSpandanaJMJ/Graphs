import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by marne on 10/22/2016.
 */
class StackX{
    private final int size = 20;
    private int[] st;
    private int top;

    public StackX(){
        st = new int[size];
        top = -1;
    }

    public void push(int j){
        st[++top] = j;
    }

    public int pop(){
        return st[top--];
    }

    public int peek(){
        return st[top];
    }

    public boolean isEmpty(){
        return (top==-1);
    }

}
 class Vertex {

    public char lablel;
    public boolean wasVisited;

    public Vertex(char label){
        this.lablel = label;
        this.wasVisited = false;
    }

}//end of class vertex

    public class Graph{

        private final int MAX_VERTS = 5;
        private Vertex vertexList[]; // array to hold vertex objects
        private int adjMat[][]; //matrix to hold edge information
        private int nVerts; // numnrt of vertices added to the matrix
        StackX stack = new StackX();

        public Graph(){
            vertexList = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            for(int i=0;i<MAX_VERTS;i++){
                for(int j=0;j<MAX_VERTS;j++){
                    adjMat[i][j] = 0;
                }
            }//adjMat initialized
        }//end of Constructor

        public void addVertex(char lab){

            Vertex v = new Vertex(lab);
            vertexList[nVerts++] = v;

        }// adding a vertex to the array of vertices

        public void addEdge(int start, int end) {       //source and destination of the edge as parameters

            adjMat[start][end]=1;
            adjMat[end][start]= 1;

        }//reverse order too since it is an undirected graph

        public void displayVertices(int v){
            System.out.println(vertexList[v].lablel);

        }

        public int getAdjUnvisitedVertex(int v){
            for(int i=0;i<nVerts;i++){
                if(adjMat[v][i]==1 && (vertexList[i].wasVisited == false)){
                    return i;
                }
            }
            return -1;
        }//end of getAdjVertices

        public void dfs(){
            vertexList[0].wasVisited = true;
            displayVertices(0);
            stack.push(0);

            while(!stack.isEmpty()){
                int v = getAdjUnvisitedVertex(stack.peek());
                if(v == -1)
                    stack.pop();
                else{
                    vertexList[v].wasVisited = true;
                    displayVertices(v);
                    stack.push(v);
                }
            }  //end of while when stack is empty


            for(int i=0;i<nVerts;i++){
                vertexList[i].wasVisited = false;
            }//end dfs
        }

        public static void main(String[] args) throws IOException{

            Graph graph = new Graph();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the vertex lable");

            graph.addVertex('A');
            graph.addVertex('B');
            graph.addVertex('C');
            graph.addVertex('D');
            graph.addVertex('E');
//            graph.addVertex('F');
//            graph.addVertex('G');
//            graph.addVertex('H');
//            graph.addVertex('I');
//            graph.addVertex('J');


            //System.out.println("Enter the edge information");
            graph.addEdge(0,1);
            graph.addEdge(1,2);
            graph.addEdge(0,3);
            graph.addEdge(3,4);

//            graph.addEdge(1,2);
//            graph.addEdge(2,3);
//            graph.addEdge(4,5);
//            graph.addEdge(6,8);
//            graph.addEdge(7,6);
//            graph.addEdge(2,8);
//            graph.addEdge(0,9);
//            graph.addEdge(0,4);
//            graph.addEdge(5,2);


            System.out.println("Displaying the vertex list");
            for(int i=0;i< graph.nVerts;i++){
                graph.displayVertices(i);
            }

            System.out.println("Vists :");
            graph.dfs();


        }
}//end of graph class
