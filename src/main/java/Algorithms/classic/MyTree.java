package Algorithms.classic;

public class MyTree
{

    class Node
    {
        private Node left;
        private Node right;
        private int data;
        
        public Node(int data){
            this.data = data;
        }
        
        void addNode(Node n)
        {
            if(n.data < data)
            {
                if(left == null)
                {
                    left = n;
                }
                else
                {
                    left.addNode(n);
                }
            }
            else
            {
                if(right == null)
                {
                    right = n;
                }
                else
                {
                    right.addNode(n);
                }
            }
        }
        
        void zhongxu()
        {
            if(left != null)
            {
                left.zhongxu();
            }
            System.out.println(data);
            if(right != null)
            {
                right.zhongxu();
            }
        }
    }
    
    private Node m_root;
    
    public MyTree()
    {
        this.m_root = null;
    }
    
    public void addNode(int i)
    {
        if(m_root == null)
        {
            m_root = new Node(i);
        }else
        {
            Node n = new Node(i);
            m_root.addNode(n);
        }
    }
    
    public void zhongxu()
    {
        m_root.zhongxu();
    }
    
    public static void main(String[] args)
    {
        MyTree myTree = new MyTree();
        myTree.addNode(10);
        myTree.addNode(4);
        myTree.addNode(0);
        myTree.addNode(1);
        myTree.addNode(2);
        myTree.addNode(3);
        myTree.addNode(4);
        myTree.addNode(5);
        myTree.addNode(8);
        myTree.addNode(6);
        myTree.addNode(7);
        myTree.addNode(9);
//        myTree.zhongxu();
        String a="abc";
        String b="abc";
        System.out.println("a==b?"+(a==b));
    }
}
