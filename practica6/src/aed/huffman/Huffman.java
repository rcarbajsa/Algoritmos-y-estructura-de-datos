package aed.huffman;


import es.upm.aedlib.Position;
import es.upm.aedlib.tree.*;


/**
 * Defines metodos for Huffman encoding of text strings.
 */
public class Huffman {
    private LinkedBinaryTree<Character> huffmanTree;
    
    public Huffman(LinkedBinaryTree<Character> huffmanTree) {
      // NO CAMBIA ESTE METODO!!! Esta usado durante las pruebas
      this.huffmanTree = huffmanTree;
    }

    /**
     * Creates a Huffman tree (and stores it in the attribute huffmanTree).
     * The shape of the (binary) tree is
     * defined by the array of char-codes.
     */
    public Huffman(CharCode[] paths) {
		this.huffmanTree = new LinkedBinaryTree<Character>();
		huffmanTree.addRoot((char)32);
		for(int i =0; i<paths.length;i++){
			String cursor=paths[i].getCode();
			Character letra=paths[i].getCh();
			Position<Character> pos=huffmanTree.root();
			if(cursor.length()==1){
				Position<Character> raiz=huffmanTree.root();
				if(cursor.charAt(0)=='1'){
					huffmanTree.insertRight(raiz, letra);
				}else{
					huffmanTree.insertLeft(raiz, letra);
				}
			}else{
				for(int j=0;j<cursor.length();j++){
					if(cursor.charAt(j)=='1'){
						if(!huffmanTree.hasRight(pos)){
							huffmanTree.insertRight(pos, (char)32);
						}
						pos=huffmanTree.right(pos);
						if(j+1==cursor.length()){
							huffmanTree.set(pos, letra);
						}

					}
					else{
						if(!huffmanTree.hasLeft(pos)){
							huffmanTree.insertLeft(pos, (char)32);
						}
						pos=huffmanTree.left(pos);
						if(j+1==cursor.length()){
							huffmanTree.set(pos, letra);
						}
					}
				}
			}
		}
	}

    //////////////////////////////////////////////////////////////////////


    /**
     * Huffman encodes a text, returning a new text string
     * containing only characters '0' and '1'.
     */
    public String encode(String text) {
	// CAMBIA
	// llama el metodo findCharacterCode
    	
    	String encodeStr = "";
    	for(int i = 0; i<text.length();i++) {
    		String path = "";
    		encodeStr += findCharacterCode(text.charAt(i),huffmanTree,huffmanTree.root(),path);
    	}
    	return encodeStr;
    }

    // CAMBIA e UTILIZA si quiereis
    private String findCharacterCode(Character ch, 
			     BinaryTree<Character> tree,
			     Position<Character> pos,
			     String path) {
    	/*if(tree.isInternal(pos)) {
    		if(tree.left(pos).element().equals(ch)) {
    			path="0"+path;
    			return path;
    		}else if(tree.right(pos).element().equals(ch)) {
    			path="1"+path;
    			return path;
    		}else {
    			if(tree.right(pos).element().equals(ch)) {
    				return path + "1";
    			}
    			if(tree.left(pos).element().equals(ch)) {
    				return path + "0";
    			}
    			
    			if(tree.right(pos).element().equals((char)32)) {
    				pos=tree.right(pos);
    				path = "1" + findCharacterCode(ch, tree, pos, path);
    			}else if(tree.left(pos).element().equals((char)32)) {
    				pos=tree.left(pos);
    				path = "0" + findCharacterCode(ch, tree, pos, path);
    			}else {
    				pos=tree.root();
    	    		pos=tree.left(pos);
    	    		path = "0";
    	    		System.out.println(tree.left(pos).element());
    	    		System.out.println("ssll");
    	    		findCharacterCode(ch, tree, pos, path);
    			}
    			return path;
    		}
    	}else {
    		System.out.println("oo");
    		pos=tree.root();
    		pos=tree.left(pos);
    		findCharacterCode(ch, tree, pos, path);
    	}
    	return path;*/
    	String line = "";
    	for (Position<Character> v : tree.children(pos)) {
			if (v.element().equals(ch)) {
				line += v.element();
				v = tree.parent(v);
				while (!tree.isRoot(v)) {
					line = v.element() + line;
					v = tree.parent(v);
				}
				line = tree.root().element() + line;
				
			} else if (tree.isInternal(v)) {
				findCharacterCode(ch, tree, v, path);
			}
			
		}
    	return line;
    }


    //////////////////////////////////////////////////////////////////////

    /**
     * Given the Huffman encoded text argument (a string of only
     * '0' and '1's), returns the corresponding normal text.
     */
    public String decode(String encodedText) {
    	String res ="";
    	Position<Character> cursor = huffmanTree.root();
    	for(int i = 0;i<=encodedText.length(); i++) {
    		if(cursor.element() == (char)32 ) {
    			System.out.println("dSEF");
    			System.out.println(cursor.element());
    			if(huffmanTree.isInternal(cursor)) {
	    			if(encodedText.charAt(i) == '0') {
	        			cursor=huffmanTree.left(cursor);
	        		}else if(encodedText.charAt(i) == '1') {
	        			cursor=huffmanTree.right(cursor);
	        		}
    			}
    		} else {
    			res += cursor.element();
    			cursor = huffmanTree.root(); 
    		}
    	}
    	return res;
    }
}



