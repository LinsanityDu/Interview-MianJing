/*第二题在一个循环聊表中插入一个新节点，返回新的头节点（面试完了自己跑的时候发现了一个bug*/

就是一个sorted cycle list，插入后返回新head，head的value是最小的

Given a node from a cyclic linked list which has been sorted, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be any single node in the list.

3Cases:
1. prev→val ≤ x ≤ current→val:
Insert between prev and current.
2. x is the maximum or minimum value in the list:
Insert before the head. (ie, the head has the smallest value and its prev→val > head→val.
3. Traverses back to the starting point:
Insert before the starting point.

void insert(Node *& aNode, int x) {
  if (!aNode) {
    aNode = new Node(x);
    aNode->next = aNode;
    return;
  }

  Node *p = aNode;
  Node *prev = NULL;
  do {
    prev = p;
    p = p->next;
    if (x <= p->data && x >= prev->data) break;   // For case 1)
    if ((prev->data > p->data) && (x < p->data || x > prev->data)) break; // For case 2)
  } while (p != aNode);   // when back to starting point, then stop. For case 3)

  Node *newNode = new Node(x);
  newNode->next = p;
  prev->next = newNode;
}

public static Node insert(Node node, int x)
	{
		if(node == null)//empty list
		{
			Node newNode = new Node(x);
			newNode.next = newNode;
			return newNode;
		}
		
		Node start = node;
		
		while(node.next != start)
		{
			if(node.val = x)
			{
				Node newNode = new Node(x);
				newNode.next = node.next;
				node.next = newNode;
				return node;
			}
			else
			{
				node = node.next;
			}
		}
		
		//if list has one value, min, max
		Node newNode = new Node(x);
		newNode.next = start;
		node.next = newNode;
		return node;
		
	}


Hints:
It is best to list all kinds of cases first before you jump into coding. Then, it is much easier to reduce the number of cases your code need to handle by combining some of them into a more generic case. Try to also list down all possible edge cases if you have time. You might discover a bug before you even start coding!

Solution:
Basically, you would have a loop that traverse the cyclic sorted list and find the point where you insert the value (Let’s assume the value being inserted called x). You would only need to consider the following three cases:

1. prev→val ≤ x ≤ current→val:
Insert between prev and current.
2. x is the maximum or minimum value in the list:
Insert before the head. (ie, the head has the smallest value and its prev→val > head→val.
3. Traverses back to the starting point:
Insert before the starting point.

Most people have no problem getting case 1) working, while case 2) is easy to miss or being handled incorrectly. Case 3), on the other hand is more subtle and is not immediately clear what kind of test cases would hit this condition. It seemed that case 1) and 2) should take care of all kinds of cases and case 3) is not needed. Think again… How can you be sure of that? Could you come up with one case where it hits case 3)?

Q: What if the list has only one value?
A: Handled by case 1). Handled by case 3).
Q: What if the list is passed in as NULL?
A: Then handle this special case by creating a new node pointing back to itself and return.
Q: What if the list contains all duplicates?
A: Then it has been handled by case 3).
Below is the code. You could combine both negation of case 1) and case 2) in the while loop’s condition, but I prefer to use break statements here to illustrate the above idea clearer.


void insert(Node *& aNode, int x) {
  if (!aNode) {
    aNode = new Node(x);
    aNode->next = aNode;
    return;
  }
 
  Node *p = aNode;
  Node *prev = NULL;
  do {
    prev = p;
    p = p->next;
    if (x <= p->data && x >= prev->data) break;   // For case 1)
    if ((prev->data > p->data) && (x < p->data || x > prev->data)) break; // For case 2)
  } while (p != aNode);   // when back to starting point, then stop. For case 3)
 
  Node *newNode = new Node(x);
  newNode->next = p;
  prev->next = newNode;
}


// Another Solution
void sortedInsert(ListNode* & head_ref, ListNode* new_node) {
    ListNode* current = head_ref;
   
    if (!current) {
        new_node->next = new_node;
        head_ref = new_node;
    } else if (current->val >= new_node->val) {
        // If value is smaller than head's value then
        // we need to change next of last node
        while(current->next != head_ref)
            current = current->next;
        current->next = new_node;
        new_node->next = head_ref;
        head_ref = new_node;
       
    } else {
        // Locate the node before the point of insertion
        while (current->next!= head_ref && current->next->val < new_node->val)
            current = current->next;
       
        new_node->next = current->next;
        current->next = new_node;
    }
}