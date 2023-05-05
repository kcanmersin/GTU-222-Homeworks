import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyJTree {
    private DefaultMutableTreeNode root;

    /**
     * 
     * Builds a tree using data from the specified file.
     * 
     * @param fileName the name of the file to use for building the tree.
     */
    public MyJTree(String fileName) {
        try {
            root = buildTree(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        MyJTree tree = new MyJTree("tree.txt");
        tree.visualize();
    }

    /**
     * 
     * Creates a visualization of the current tree using Swing's JTree component.
     * Adds several buttons to the frame for various tree traversal and manipulation
     * operations.
     */
    public void visualize() {
        JTree tree = new JTree(root);
        JFrame frame = new JFrame("JTree Example");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button1 = new JButton("BFS");
        JButton button2 = new JButton("DFS");
        JButton button3 = new JButton("Post Order");
        JButton button4 = new JButton("Move");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter a string:");
                DefaultMutableTreeNode result = bfs(input);
                if (result != null) {
                    JOptionPane.showMessageDialog(frame, "Found node with label: " + result.getUserObject());
                } else {
                    JOptionPane.showMessageDialog(frame, "Node not found.");
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter a string:");
                DefaultMutableTreeNode result = dfs(input);
                if (result != null) {
                    JOptionPane.showMessageDialog(frame, "Found node with label: " + result.getUserObject());
                } else {
                    JOptionPane.showMessageDialog(frame, "Node not found.");
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter a string:");
                DefaultMutableTreeNode result = postOrderFind(input);
                if (result != null) {
                    JOptionPane.showMessageDialog(frame, "Found node with label: " + result.getUserObject());
                } else {
                    JOptionPane.showMessageDialog(frame, "Node not found.");
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] targets = new String[10];
                int numTargets = 0;
                String input;
                while (true) {
                    input = JOptionPane.showInputDialog(frame, "Enter a string: (X if first input done) ");
                    if (input.equalsIgnoreCase("x")) {
                        break;
                    }
                    targets[numTargets++] = input;
                    if (numTargets == targets.length) {
                        targets = Arrays.copyOf(targets, targets.length * 2);
                    }
                }
                targets = Arrays.copyOf(targets, numTargets);
                input = JOptionPane.showInputDialog(frame, "Enter a string:");
                move(targets, new String[] { input });
                frame.setVisible(false);
                frame.dispose();
                visualize();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Reads data from a file and returns it as a 2D String array.
     *
     * @param fileName the name of the file to read from
     * @return a 2D String array containing the data read from the file
     * @throws NullPointerException  if fileName is null
     * @throws FileNotFoundException if the file does not exist or cannot be read
     */
    public String[][] readFromFile(String fileName) throws FileNotFoundException {
        if (fileName == null) {
            throw new NullPointerException("File name cannot be null");
        }
        int rows = 0;
        String[][] data = new String[1][1];
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(";");
                if (rows >= data.length) {
                    data = Arrays.copyOf(data, data.length * 2);
                }

                data[rows++] = tokens;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found or cannot be read: " + fileName);
        }
        return data;
    }

    /**
     * Build tree using data from a text file.
     * 
     * @param fileName the name of the file to read data from
     * @return the root node of the built tree
     * @throws FileNotFoundException if the specified file is not found
     */
    public DefaultMutableTreeNode buildTree(String fileName) throws FileNotFoundException {
        String[][] data = readFromFile(fileName);
        if (data == null) {
            return null;
        }
        root = new DefaultMutableTreeNode("Tree Root");
        String token;
        for (String[] tokens : data) {
            if (tokens == null) {
                continue;
            }
            DefaultMutableTreeNode parent = root;
            for (int i = 0; i < tokens.length; i++) {
                token = tokens[i];
                if (token != null) {
                    DefaultMutableTreeNode node = isExist(parent, token);// if data exist new node is that node
                    if (node == null) {// if data does not exist add to the tree
                        node = new DefaultMutableTreeNode(token);
                        parent.add(node);
                    }
                    parent = node;
                }
            }
        }
        return root;
    }

    /**
     * @param parent
     * @param target data looking for is already added
     * @return
     */
    private DefaultMutableTreeNode isExist(DefaultMutableTreeNode parent, String target) {
        DefaultMutableTreeNode child;
        for (int i = 0; i < parent.getChildCount(); i++) {
            child = (DefaultMutableTreeNode) parent.getChildAt(i);
            if (child.getUserObject().equals(target)) {
                return child;
            }
        }
        return null;
    }

    /**
     * search tree for the target use deep first search algorithm
     * 
     * @param target
     */
    public DefaultMutableTreeNode dfs(String target) {
        System.out.println("Deep First Search worked...");
        return dfsHelper(root, target);
    }

    /**
     * 
     * Performs depth-first search on a tree represented as a
     * DefaultMutableTreeNode
     * Starts from the given root node and looks for the node with the given target
     * value
     * 
     * @param root   the root node of the tree to be searched
     * @param target the target value to be searched in the tree
     * @return the DefaultMutableTreeNode that contains the target value, or null if
     *         not found
     */
    public DefaultMutableTreeNode dfsHelper(DefaultMutableTreeNode root, String target) {
        if (root == null) {
            return null;
        }
        Stack<DefaultMutableTreeNode> stack = new Stack<>();
        stack.push(root);
        int step = 1;
        while (!stack.isEmpty()) {
            DefaultMutableTreeNode node = stack.pop();
            System.out.println("Step -> " + step++ + ": Visiting node " + node);
            if (node.getUserObject().equals(target)) {
                System.out.println("Found target node " + node + " at step " + --step);
                return node;
            }
            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                stack.push(child);
            }
        }
        System.out.println("Target node " + target + " not found.");
        return null;
    }

    /**
     * search tree for the target use breadth first search algorithm
     * 
     * @param target s
     * @return
     */
    public DefaultMutableTreeNode bfs(String target) {
        System.out.println("Breadth First Search worked...");
        return bfsHelper(root, target);
    }

    /**
     * 
     * Performs a breadth first search starting from the given root node and
     * searches for the node with the given target value
     * 
     * @param root   the root node of the tree to search
     * @param target the target value to search for
     * @return the node with the target value if found, otherwise returns null
     */
    public DefaultMutableTreeNode bfsHelper(DefaultMutableTreeNode root, String target) {
        Queue<DefaultMutableTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int step = 1;
        DefaultMutableTreeNode node;
        while (!queue.isEmpty()) {

            node = queue.poll();
            System.out.println("Step " + (step++) + " -> " + node);

            if (node.getUserObject().equals(target)) {
                System.out.println("Found target: " + target);
                return node;
            }

            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
                queue.add(childNode);
            }
        }

        System.out.println("Target not found: " + target);
        return null;
    }

    /**
     * 
     * Performs a post-order traversal of the tree to find a target node.
     * 
     * @param target the string value of the target node to be found.
     * @return the node with the target value if found, or null if not found.
     */
    public DefaultMutableTreeNode postOrderFind(String target) {
        System.out.println("PostOrder Traversal  Search worked...");

        return postOrderTraversalHelper(root, target);
    }

    /**
     * 
     * Helper function to perform post-order traversal of the tree to
     * find a target node
     * 
     * @param root   the root node of the tree.
     * @param target the string value of the target node to be found.
     * @return the node with the target value if found, or null if not found.
     */
    public DefaultMutableTreeNode postOrderTraversalHelper(DefaultMutableTreeNode root,
            String target) {
        if (root == null) {
            return null;
        }
        Stack<DefaultMutableTreeNode> stack = new Stack<>();
        Map<DefaultMutableTreeNode, Boolean> allChildFinished = new HashMap<>();
        stack.push(root);
        int step = 1;
        allChildFinished.put(root, false);

        while (!stack.isEmpty()) {
            DefaultMutableTreeNode node = stack.peek();

            if (allChildFinished.containsKey(node) && allChildFinished.get(node)) {
                stack.pop();
                System.out.println("Step " + step++ + ": Visiting node " + node);

                if (node.getUserObject().equals(target)) {
                    System.out.println("Found target node " + node + " at step " + --step);
                    return node;
                }

            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                    stack.push(child);
                }
                allChildFinished.put(node, true);
            }
        }

        System.out.println("Target node " + target + " not found.");
        return null;
    }

    /**
     * look for the node use given path
     * 
     * @param targets
     * @return
     */
    public DefaultMutableTreeNode searchNode(String[] path) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) root;
        for (String token : path) {
            node = search(node, token);
        }
        return node;
    }

    /**
     * look for target at child of a node
     * 
     * @param node
     * @param target
     * @return target child
     */
    public DefaultMutableTreeNode search(DefaultMutableTreeNode node, String target) {
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            if (child.getUserObject().equals(target)) {
                return child;
            }
        }
        return null;
    }

    /**
     * move one node to another place
     * 
     * @param from
     * @param to
     */
    public void move(String[] from, String[] to) {

        DefaultMutableTreeNode takeIt = searchNode(from);// find wanted node
        if (takeIt == null) {
            System.out.println("Cannot move because it does not exist in the tree!! ");
            return;
        }
        DefaultMutableTreeNode controlParent = (DefaultMutableTreeNode) takeIt.getParent();// holding the parent of
                                                                                           // takeIt in our hand to be
                                                                                           // able to control the number
                                                                                           // of children later.
        DefaultMutableTreeNode insertThere = searchNode(to);// find wanted node
        from[0] = to[0];// convert path to move correctly
        DefaultMutableTreeNode last = createNecessaryNode(from, insertThere);// create nececerynodes to add. last is the
                                                                             // place which we add the node
        DefaultMutableTreeNode temp = search(last, from[from.length - 1]);// use this temp to look for is there
                                                                          // owerwrite situation
        if (temp != null) {
            System.out.println("Overwrite");
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) temp.getParent();
            int index = parent.getIndex(temp);
            parent.remove(temp);
            parent.insert(takeIt, index);
        } else {

            last.add(takeIt);
        }
        if (controlParent.getChildCount() == 0) {// if child count is zero send to a function which remove nodes until
                                                 // child count is not zero
            removeParent(controlParent);
        }
    }

    /**
     * remove parent of a node if it has zero child
     * 
     * @param controlParent
     */
    private void removeParent(DefaultMutableTreeNode controlParent) {
        if (controlParent.getChildCount() == 0) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) controlParent.getParent();
            parent.remove(controlParent);
            removeParent(parent);
        }

    }

    /**
     * create nodes for add a node to another place correctly
     * 
     * @param targets
     * @param insertThere
     * @return
     */
    private DefaultMutableTreeNode createNecessaryNode(String[] targets, DefaultMutableTreeNode insertThere) {

        DefaultMutableTreeNode node = root;
        DefaultMutableTreeNode temp;
        for (int a = 0; a < targets.length - 1; a++) {
            temp = search(node, targets[a]);
            if (temp == null) {
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(targets[a]);
                node.add(newNode);
                node = newNode;
            } else {
                node = temp;
            }
        }
        return node;
    }

}
