package com.bluemobi.mavenTest.thread;

import java.util.LinkedList;
import java.util.Queue;

import com.bluemobi.mavenTest.algorithm.lru.tree.BinaryTree;
import com.bluemobi.mavenTest.algorithm.lru.tree.TreeNode;

public class PrintTreeTask implements Runnable {
	
	public BinaryTree bt = null;
	
	ThreadLocal<TreeNode> treeNode = new ThreadLocal<TreeNode>();
	
	public PrintTreeTask(){
	}
	public PrintTreeTask(BinaryTree bt){
		this.bt = bt;
	}

	@Override
	public void run() {
		
		try {
			while(true){
				this.printTree(bt.getRoot());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void printTree(TreeNode tree) throws InterruptedException{
			if(tree==null){
				return;
			}
			System.out.println(tree.getData());
			Thread.sleep(1000);
			this.notify();
			this.wait();
			printTree(tree.getLeftChild());
			printTree(tree.getRightChild());
	}
	
	public synchronized void printTree2(TreeNode tree) throws InterruptedException{
		
		// 创建一个队列用来存放节点
		Queue<TreeNode> queue=new LinkedList<TreeNode>(); 
        // 当前行打印的最右节点
        TreeNode last = null;
        // 下一行打印的最右节点
        TreeNode nlast = null;
        
        last = tree;
        
        // 先将根放入队列中
		queue.add(tree);
		// 队列不为空时，就一直循环poll直到队列为空
		while(queue.size()>0){
			// 推出节点
			TreeNode nowTree = queue.poll();
			
			// 如果当前节点有左节点，将左节点压入队列中
            if (nowTree.getLeftChild()!=null) {
                queue.add(nowTree.getLeftChild());
                nlast = nowTree.getLeftChild();
            }
            // 如果当前节点有右节点，将左节点压入队列中
            if (nowTree.getRightChild()!=null) {
                queue.add(nowTree.getRightChild());
                nlast = nowTree.getRightChild();
            }
            System.out.print("	" + nowTree.getData());
            
            // 当当前打印节点为当前行最右节点时换行
            if (last.equals(nowTree)) {
                System.out.println();
                last = nlast;
            }
		}
		
}
	

	public static void main(String[] args) throws InterruptedException {
		
		BinaryTree bt = new BinaryTree();  
	    bt.createBinTree(bt.getRoot());  
	   
		PrintTreeTask task = new PrintTreeTask(bt);
		task.printTree2(bt.getRoot());
		
		/*Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		
		t1.start();
		t2.start();*/
		
	}

}
