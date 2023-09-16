package Dominio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Estructura de datos
 * @author marc.garcia.ribes
 */
public class TreeNode<Movimiento>{

	public Movimiento data;
	public TreeNode<Movimiento> parent;
	public List<TreeNode<Movimiento>> children;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	//private List<TreeNode<Movimiento>> elementsIndex;

	public TreeNode(Movimiento data) {
		this.data = data;
		this.children = new LinkedList<TreeNode<Movimiento>>();
		//this.elementsIndex = new LinkedList<TreeNode<Movimiento>>();
		//this.elementsIndex.add(this);
	}

	public TreeNode<Movimiento> addChild(Movimiento child) {
		TreeNode<Movimiento> childNode = new TreeNode<Movimiento>(child);
		childNode.parent = this;
		this.children.add(childNode);
		//this.registerChildForSearch(childNode);
		return childNode;
	}
        
        public TreeNode<Movimiento> removeNode(TreeNode<Movimiento> node) {
		if (node.isRoot()) node = null;
                else{
                    List<TreeNode<Movimiento>> parentsChildren = node.parent.children;
                    parentsChildren.remove(node);
                   
//                    List<TreeNode<Movimiento>> parentsIndexs = node.parent.elementsIndex;
//                    parentsIndexs.remove(node);
                    
                    node = node.parent;
                }
		return node;
	}
        

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

//	private void registerChildForSearch(TreeNode<Movimiento> node) {
//		elementsIndex.add(node);
//		if (parent != null)
//			parent.registerChildForSearch(node);
//	}

//	public TreeNode<Movimiento> findTreeNode(Comparable<Movimiento> cmp) {
//		for (TreeNode<Movimiento> element : this.elementsIndex) {
//			Movimiento elData = element.data;
//			if (cmp.compareTo(elData) == 0)
//				return element;
//		}
//		return null;
//	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

//	@Override
//	public Iterator<TreeNode<Movimiento>> iterator() {
//		TreeNodeIter<Movimiento> iter = new TreeNodeIter<Movimiento>(this);
//		return iter;
//	}

}